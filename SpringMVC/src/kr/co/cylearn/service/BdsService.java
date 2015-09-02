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
	 * 설명 : 게시판 정보
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 6. jdh          최초 작성
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

		//게시판 정보
		Map<String, Object> bdsMap = commonDao.selectCommonOne(BDS_SERVICE + ".selectOne", map);

		if (bdsMap == null ){
			resultModel.setResultFail("해당 게시판이 존재하지 않습니다.");
			return resultModel;
		}

		resultModel.put("bds", bdsMap);
		resultModel.setResultOk();

		return resultModel;
	}

}
