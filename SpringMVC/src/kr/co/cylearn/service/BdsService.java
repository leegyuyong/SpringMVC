package kr.co.cylearn.service;

import java.util.Map;

import kr.co.cylearn.common.base.BaseService;
import kr.co.cylearn.model.ResultHashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BdsService extends BaseService {

	private final static Logger log = Logger.getLogger(BdsService.class);

	/**
	 * <pre>
	 * ���� : �Խ��� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 6. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 6.
	 * @author : jdh
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultHashMap getOne(Map<String, Object> map) {

		ResultHashMap resultModel = new ResultHashMap();

		//�Խ��� ����
		Map<String, Object> bdsMap = commonDao.selectCommonOne(BDS_SERVICE + ".selectOne", map);

		if (bdsMap == null ){
			resultModel.setResultFail("�ش� �Խ����� �������� �ʽ��ϴ�.");
			return resultModel;
		}

		resultModel.put("bds", bdsMap);
		resultModel.setResultOk();

		return resultModel;
	}

}
