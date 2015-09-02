package kr.co.cylearn.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import kr.co.cylearn.common.base.BaseController;
import kr.co.cylearn.common.base.BaseService;
import kr.co.cylearn.common.manager.ConfigManager;
import kr.co.cylearn.common.util.FileUtil;
import kr.co.cylearn.common.util.PageUtil;
import kr.co.cylearn.common.util.StringUtil;
import kr.co.cylearn.model.FileForm;
import kr.co.cylearn.model.ResultHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardService extends BaseService {

	private final static Logger log = Logger.getLogger(BoardService.class);

	private final static int PAGE_SIZE_BOARD = 10;

	private final static int PAGE_BLOCK_SIZE_BOARD = 10;

	@Autowired
	BdsService bdsService;

	/**
	 * <pre>
	 * ���� : �Խ��� ����Ʈ ��ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultHashMap getList(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		//�Խ���(BDS) ����
		resultModel = bdsService.getOne(map);
		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		}

		//�Խ��� �Ǽ�
		int rowCount = commonDao.selectCommonInt(BOARD_SERVICE + ".selectRowCount", map);

		int pageNo = StringUtil.defaultInteger((String)map.get("pageNo")) == 0 ? 1 : StringUtil.defaultInteger((String)map.get("pageNo"));

		PageUtil pageUtil = new PageUtil(rowCount, PAGE_SIZE_BOARD, PAGE_BLOCK_SIZE_BOARD, pageNo);

		map.put("startRow", pageUtil.getStartRow());
		map.put("endRow", pageUtil.getEndRow());

		//�Խ��� ����Ʈ
		List<Map<String, Object>> list = commonDao.selectCommonList(BOARD_SERVICE + ".selectList", map);


		resultModel.put("list", list);
		resultModel.put("pageUtil", pageUtil);
		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ����ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultHashMap getOne(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		//�Խ���(BDS) ����
		resultModel = bdsService.getOne(map);
		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		}

		//�Խù���ȸ
		Map<String, Object> oneMap = commonDao.selectCommonOne(BOARD_SERVICE + ".selectOne", map);

		if (oneMap == null ){
			resultModel.setResultFail("�ش� ������ �����ϴ�.");
			return resultModel;
		}

		//�Խù����ϸ���Ʈ
		List<Map<String, Object>> listMap = commonDao.selectCommonList(BOARD_SERVICE + ".selectOneFileList", map);

		String gadmin = (String)oneMap.get("GADMIN");
		String content = (String)oneMap.get("CONTENT");

		if (("".equals(gadmin) || "ZZ".equals(gadmin) || "N".equals(gadmin)) || (content.indexOf("<HTML>") == -1 && content.indexOf("<P>") == -1)) {
			oneMap.put("CONTENT_HTML", StringUtil.htmlSpecialChar(content));
		} else {
			oneMap.put("CONTENT_HTML", content);
		}

		//��ȸ�� ����
		if (map.get("userid") == null || !oneMap.get("USERID").equals(map.get("userid"))){
			commonDao.update(BOARD_SERVICE + ".updateCnt", map);
		}

		resultModel.put("map", oneMap);
		resultModel.put("fileList", listMap);
		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ���
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public ResultHashMap create(Map<String, Object> map, FileForm fileForm) {

		ResultHashMap resultModel = new ResultHashMap();

		//�Խ��� ���
		int resultInt = commonDao.insertCommon(BOARD_SERVICE + ".insert", map);

		if (resultInt <= 0 ){
			resultModel.setResultFail("���忡 �����Ͽ����ϴ�.");
			return resultModel;
		}

		//���� �Խ��� ���(���ε�)
		resultModel = createBoardFile(map, fileForm, BOARD_SERVICE);
		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		}

		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : �Խ��� �亯 ���
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 25. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 25.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public ResultHashMap createReply(Map<String, Object> map, FileForm fileForm) {

		ResultHashMap resultModel = new ResultHashMap();

		int resultInt = commonDao.updateCommon(BOARD_SERVICE + ".updatePosition", map);

		//�Խ��� ���
		resultInt = commonDao.insertCommon(BOARD_SERVICE + ".insertReply", map);

		if (resultInt <= 0 ){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultModel.setResultFail("���忡 �����Ͽ����ϴ�.");
			return resultModel;
		}

		//���� �Խ��� ���(���ε�)
		resultModel = createBoardFile(map, fileForm, BOARD_SERVICE);
		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		}

		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public ResultHashMap modify(Map<String, Object> map, FileForm fileForm) {

		ResultHashMap resultModel = new ResultHashMap();

		int resultInt = commonDao.updateCommon(BOARD_SERVICE + ".update", map);

		if (resultInt <= 0 ){
			resultModel.setResultFail("������ �����Ͽ����ϴ�.");
			return resultModel;
		}

		//���� �Խ��� ����(����/���ε�)
		resultModel = modifyBoardFile(map, fileForm, BOARD_SERVICE);
		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		}

		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : �Խù� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 25. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 25.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public ResultHashMap remove(Map<String, Object> map, FileForm fileForm) {

		ResultHashMap resultModel = new ResultHashMap();

		int resultInt = commonDao.deleteCommon(BOARD_SERVICE + ".delete", map);

		if (resultInt <= 0 ){
			resultModel.setResultFail("������ �����Ͽ����ϴ�.");
			return resultModel;
		}

		//���� �Խ��� ����(����)
		resultModel = removeBoardFile(map, fileForm, BOARD_SERVICE);
		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		}

		resultModel.put("resultType", "SUCCESS");

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ���� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map - tabseq, seq, userid �ʼ�
	 * @param fileForm
	 * @param serviceClassName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultHashMap createBoardFile(Map<String, Object> map, FileForm fileForm, String serviceClassName) {

		//���� ���ε�
		ResultHashMap resultModel = uploadFile(map, fileForm, BOARD_SERVICE);

		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		} else {
			// ���� �Խ��� ���
			List<ResultHashMap> list = resultModel.getList("list");

			for (int i = 0, size = list.size(); i < size; i++) {
				commonDao.insertCommon(BOARD_SERVICE + ".insertBoardFile", list.get(i));
			}
		}

		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ���� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @param serviceClassName
	 * @return
	 */
	public ResultHashMap modifyBoardFile(Map<String, Object> map, FileForm fileForm, String serviceClassName) {

		ResultHashMap resultModel = new ResultHashMap();

		//���� ����
		resultModel = deleteFile(map, fileForm, serviceClassName);

		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		} else {
			// ���� �Խ��� ����
			List<ResultHashMap> list = resultModel.getList("list");

			for (int i = 0, size = list.size(); i < size; i++) {
				commonDao.deleteCommon(BOARD_SERVICE + ".deleteBoardFile", list.get(i));
			}
		}

		//���� ���ε�
		resultModel = uploadFile(map, fileForm, serviceClassName);

		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		} else {
			// ���� �Խ��� ���
			List<ResultHashMap> list = resultModel.getList("list");

			for (int i = 0, size = list.size(); i < size; i++) {
				commonDao.insertCommon(BOARD_SERVICE + ".insertBoardFile", list.get(i));
			}
		}

		resultModel.setResultOk();

		return resultModel;
	}

	public ResultHashMap removeBoardFile(Map<String, Object> map, FileForm fileForm, String serviceClassName) {

		ResultHashMap resultModel = new ResultHashMap();

		//���� ����
		resultModel = deleteFile(map, fileForm, serviceClassName);

		if (resultModel == null || BaseController.RESULT_FAIL.equals(resultModel.getResult())) {
			return resultModel;
		} else {
			// ���� �Խ��� ����
			List<ResultHashMap> list = resultModel.getList("list");

			for (int i = 0, size = list.size(); i < size; i++) {
				commonDao.deleteCommon(BOARD_SERVICE + ".deleteBoardFile", list.get(i));
			}
		}

		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : ���� ���ε�
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map - tabseq, seq, userid �ʼ�
	 * @param fileForm - files �ʼ�
	 * @param serviceClassName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultHashMap uploadFile(Map<String, Object> map, FileForm fileForm, String serviceClassName) {
		ResultHashMap resultModel = new ResultHashMap();

		List<ResultHashMap> list = new ArrayList<ResultHashMap>();
		ResultHashMap listMap = null;

		String serviceName = StringUtil.getServiceName(serviceClassName);

		try {
			if (fileForm.getFiles() != null && fileForm.getFiles().size() > 0){

				//���丮 ��� ��������
				String dir = ConfigManager.getInstance().getUploadDir(serviceName);

				int tabseq = Integer.parseInt(map.get("tabseq").toString());
				int seq = Integer.parseInt(map.get("seq").toString());
				String userid = (String)map.get("userid");

				List<MultipartFile> files = fileForm.getFiles();

				for (int i = 0, fSize = files.size() ; i < fSize ; i++) {
					MultipartFile multipartFile = files.get(i);

					if (multipartFile.isEmpty() == false){

						String realFileName = multipartFile.getOriginalFilename();

						String saveFileName = fileForm.getSaveFileName(serviceName, realFileName, i+1, userid);

						log.debug("realFileName : "+realFileName);
						log.debug("saveFileName : "+saveFileName);
						log.debug("dir : "+dir);

						File file = new File(dir + saveFileName);
						multipartFile.transferTo(file);

						listMap = new ResultHashMap();
						listMap.put("tabseq", tabseq);
						listMap.put("seq", seq);
						listMap.put("realfile", realFileName);
						listMap.put("savefile", saveFileName);
						listMap.put("userid", userid);
						listMap.put("FILE_SIZE", multipartFile.getSize()); 	//���������� ����
						list.add(listMap);
					}
				}
			}
		} catch (Exception e) {
			log.error("file upload error",e);
			resultModel.setResultFail("���� ���忡 �����Ͽ����ϴ�.");
			return resultModel;
		}

		resultModel.put("list", list);
		resultModel.setResultOk();

		return resultModel;
	}

	/**
	 * <pre>
	 * ���� : ���� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param map - tabseq, seq �ʼ�
	 * @param fileForm - removeFileseq �ʼ�
	 * @param serviceClassName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultHashMap deleteFile(Map<String, Object> map, FileForm fileForm, String serviceClassName) {
		ResultHashMap resultModel = new ResultHashMap();

		List<ResultHashMap> list = new ArrayList<ResultHashMap>();
		ResultHashMap listMap = null;

		String serviceName = StringUtil.getServiceName(serviceClassName);

		try {
			if (fileForm.getRemoveFileseq() != null && fileForm.getRemoveFileseq().size() > 0){

				//���丮 ��� ��������
				String dir = ConfigManager.getInstance().getUploadDir(serviceName);

				int tabseq = Integer.parseInt(map.get("tabseq").toString());
				int seq = Integer.parseInt(map.get("seq").toString());

				List<String> removeFileseqList = fileForm.getRemoveFileseq();
				for (int i = 0, fSize = removeFileseqList.size() ; i < fSize ; i++) {
					String removeFileseq = removeFileseqList.get(i);
					log.debug("removeFileList["+i+"]:"+ removeFileseq);

					StringTokenizer st = new StringTokenizer(removeFileseq, "|");

					String fileseq = st.nextToken();
					String savefile = st.nextToken();

					//���� ����
					FileUtil.doDeleteFile(dir + savefile);

					listMap = new ResultHashMap();
					listMap.put("tabseq", tabseq);
					listMap.put("seq", seq);
					listMap.put("fileseq", fileseq);
					list.add(listMap);
				}
			}
		} catch (Exception e) {
			log.error("file delete error",e);
			resultModel.setResultFail("���� ������ �����Ͽ����ϴ�.");
			return resultModel;
		}

		resultModel.put("list", list);
		resultModel.setResultOk();

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	public ResultHashMap getListMyQa(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		//�Խ��� �Ǽ�
		int rowCount = commonDao.selectCommonInt(BOARD_SERVICE + ".selectRowCountMyQa", map);

		int pageNo = StringUtil.defaultInteger((String)map.get("pageNo")) == 0 ? 1 : StringUtil.defaultInteger((String)map.get("pageNo"));

		PageUtil pageUtil = new PageUtil(rowCount, PAGE_SIZE_BOARD, PAGE_BLOCK_SIZE_BOARD, pageNo);

		map.put("startRow", pageUtil.getStartRow());
		map.put("endRow", pageUtil.getEndRow());

		//�Խ��� ����Ʈ
		List<Map<String, Object>> list = commonDao.selectCommonList(BOARD_SERVICE + ".selectListMyQa", map);

		resultModel.put("list", list);
		resultModel.put("pageNo", pageNo);
		resultModel.put("rowCount", rowCount);
		resultModel.put("pagingStr", pageUtil.printPage("goPageList"));
		resultModel.setResultOk();

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	public ResultHashMap getOneMyQa(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		//�Խù���ȸ
		Map<String, Object> oneMap = commonDao.selectCommonOne(BOARD_SERVICE + ".selectOneMyQa", map);

		if (oneMap == null ){
			resultModel.setResultFail("�ش� ������ �����ϴ�.");
			return resultModel;
		}

		//�Խù����ϸ���Ʈ
		List<Map<String, Object>> listMap = commonDao.selectCommonList(BOARD_SERVICE + ".selectOneFileList", map);

		String gadmin = (String)oneMap.get("GADMIN");
		String content = (String)oneMap.get("CONTENT");

		if (("".equals(gadmin) || "ZZ".equals(gadmin) || "N".equals(gadmin)) || (content.indexOf("<HTML>") == -1 && content.indexOf("<P>") == -1)) {
			oneMap.put("CONTENT_HTML", StringUtil.htmlSpecialChar(content));
		} else {
			oneMap.put("CONTENT_HTML", content);
		}

		//��ȸ�� ����
		if (map.get("userid") == null || !oneMap.get("USERID").equals(map.get("userid"))){
			commonDao.update(BOARD_SERVICE + ".updateCnt", map);
		}

		resultModel.put("map", oneMap);
		resultModel.put("fileList", listMap);
		resultModel.setResultOk();

		return resultModel;
	}
}
