package kr.co.cylearn.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import kr.co.cylearn.common.manager.ConfigManager;
import kr.co.cylearn.common.util.StringUtil;
import kr.co.cylearn.dao.CommonDao;
import kr.co.cylearn.dao.TestDao;
import kr.co.cylearn.model.FileForm;
import kr.co.cylearn.model.ResultHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SampleService {
	private final static Logger log = Logger.getLogger(SampleService.class);

	private int pageSize = 10;

	@Autowired
	TestDao testDao;

	@Autowired
	CommonDao commonDao;

	/**
	 * TestDao 네임스페이스
	 */
	private static final String NAMESPACE_TESTDAO = TestDao.class.getName();

	@SuppressWarnings("unchecked")
	public ResultHashMap getList(int tabseq, Map<String, Object> map) {

		map.put("tabseq", tabseq);

		ResultHashMap resultModel = new ResultHashMap();

		List<Map<String, Object>> list = commonDao.selectCommonList(NAMESPACE_TESTDAO + ".selectList", map);

		if (list == null ){
			resultModel.put("resultType", "ERROR");
			resultModel.put("errorCode", "A02");

			return resultModel;
		}

		resultModel.put("list", list);
		resultModel.put("resultType", "SUCCESS");

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	public int getTotalCount(int tabseq, Map<String, Object> map) {

		map.put("tabseq", tabseq);

		int count = commonDao.selectCommonInt(NAMESPACE_TESTDAO + ".selectTotalCount", map);

		return count;
	}

	@SuppressWarnings("unchecked")
	public ResultHashMap getBoardOne(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		//게시물조회
		Map<String, Object> oneMap = commonDao.selectCommonOne(NAMESPACE_TESTDAO + ".selectBoardOne", map);

		//게시물파일리스트
		List<Map<String, Object>> listMap = commonDao.selectCommonList(NAMESPACE_TESTDAO + ".selectBoardOneFileList", map);

		if (oneMap == null ){
			resultModel.put("resultType", "ERROR");
			resultModel.put("errorCode", "A02");

			return resultModel;
		}

		String gadmin = (String)oneMap.get("GADMIN");
		String content = (String)oneMap.get("CONTENT");

		if (("".equals(gadmin) || "ZZ".equals(gadmin) || "N".equals(gadmin)) || (content.indexOf("<HTML>") == -1 && content.indexOf("<P>") == -1)) {
			oneMap.put("CONTENT", StringUtil.htmlSpecialChar(content));
		}

		resultModel.put("one", oneMap);
		resultModel.put("list", listMap);
		resultModel.put("resultType", "SUCCESS");

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	public ResultHashMap addBoard(Map<String, Object> map, FileForm fileForm) {

		ResultHashMap resultModel = new ResultHashMap();

		int resultInt = commonDao.insertCommon(NAMESPACE_TESTDAO + ".insertBoard", map);

		/*if (resultInt > 0){
			addBoardFile(map, fileForm);
		}*/

		if (resultInt <= 0 ){
			resultModel.put("resultType", "ERROR");
			resultModel.put("errorCode", "A02");

			return resultModel;
		}

		resultModel.put("resultType", "SUCCESS");

		return resultModel;
	}

	public ResultHashMap addBoardFile(Map<String, Object> map, FileForm fileForm) {

		ResultHashMap resultModel = new ResultHashMap();

		if (fileForm != null && fileForm.getFiles().size() > 0){

			String dir = ConfigManager.getInstance().get("dir.upload.default");


			List<MultipartFile> files = fileForm.getFiles();

			for (int i = 0, fSize = files.size() ; i < fSize ; i++) {
				MultipartFile multipartFile = files.get(i);
				String realFileName = multipartFile.getOriginalFilename();

				//TODO board와 userid는 추후 변경
				String saveFileName = fileForm.getSaveFileName("board", realFileName, i+1, "userid");

				String path = dir + realFileName;

				log.debug("realFileName : "+realFileName);
				log.debug("saveFileName : "+saveFileName);
				log.debug("dir : "+dir);

				File file = new File(path);

				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				map.put("REALFILE",realFileName);
				map.put("SAVEFILE",saveFileName);

				commonDao.insertCommon(NAMESPACE_TESTDAO + ".insertBoardFile", map);

			}
		}

		resultModel.put("resultType", "SUCCESS");

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	public ResultHashMap UpdateBoard(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		int resultInt = commonDao.updateCommon(NAMESPACE_TESTDAO + ".updateBoard", map);

		if (resultInt <= 0 ){
			resultModel.put("resultType", "ERROR");
			resultModel.put("errorCode", "A02");

			return resultModel;
		}

		resultModel.put("resultType", "SUCCESS");

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	public ResultHashMap deleteBoard(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		int resultInt = commonDao.deleteCommon(NAMESPACE_TESTDAO + ".deleteBoard", map);

		if (resultInt <= 0 ){
			resultModel.put("resultType", "ERROR");
			resultModel.put("errorCode", "A02");

			return resultModel;
		}

		resultModel.put("resultType", "SUCCESS");

		return resultModel;
	}
}
