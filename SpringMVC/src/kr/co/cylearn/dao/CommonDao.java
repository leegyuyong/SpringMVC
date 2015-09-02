package kr.co.cylearn.dao;

import java.util.List;
import java.util.Map;

import kr.co.cylearn.common.base.BaseDao;
import kr.co.cylearn.model.ResultHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class CommonDao extends BaseDao {

	/**
	 * <pre>
	 * 설명 : 다건 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param namespace+"."+id
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectCommonList(String statementNm, Map<String, Object> params) {
		List<Map<String, Object>> result = selectList(statementNm, params);
		return result;
	}

	/**
	 * <pre>
	 * 설명 : 다건 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 13. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 13.
	 * @author : jdh
	 * @param statementNm
	 * @param params
	 * @return List<ResultHashMap>
	 */
	public List<ResultHashMap> selectCommonListForResultHashMap(String statementNm, Map<String, Object> params) {
		List<ResultHashMap> result = selectList(statementNm, params);
		return result;
	}

	/**
	 * <pre>
	 * 설명 : 단건 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 5. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 5.
	 * @author : jdh
	 * @param namespace+"."+id
	 * @param params
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectCommonOne(String statementNm, Map<String, Object> params) {
		Map<String, Object> result = selectOne(statementNm, params);
		return result;
	}


	/**
	 * <pre>
	 * 설명 : 단건 조회(ResultHashMap)
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 13. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 13.
	 * @author : jdh
	 * @param statementNm
	 * @param params
	 * @return ResultHashMap
	 */
	public ResultHashMap selectCommonOneForResultHashMap(String statementNm, Map<String, Object> params) {
		ResultHashMap result = selectOne(statementNm, params);
		return result;
	}

	/**
	 *
	 * <pre>
	 * 설명 : String값 한개
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 16. ilsunni          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 16.
	 * @author : ilsunni
	 * @param statementNm
	 * @param params
	 * @return
	 */
	public String selectCommonString(String statementNm, Map<String, Object> params) {
		return String.valueOf(selectOne(statementNm, params));
	}

	/**
	 * <pre>
	 * 설명 : Int 값 한개 (카운트/MAX SEQ 등)
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 6. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 6.
	 * @author : jdh
	 * @param statementNm
	 * @param params
	 * @return
	 */
	public int selectCommonInt(String statementNm, Map<String, Object> params) {
		return (Integer)selectOne(statementNm, params);
	}


	/**
	 * <pre>
	 * 설명 : 등록
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 15.
	 * @author : jdh
	 * @param namespace+"."+id
	 * @param params
	 * @return int
	 */
	public int insertCommon(String statementNm, Map<String, Object> params) {
		int result = insert(statementNm, params);
		return result;
	}

	/**
	 * <pre>
	 * 설명 : 수정
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 15.
	 * @author : jdh
	 * @param namespace+"."+id
	 * @param params
	 * @return
	 */
	public int updateCommon(String statementNm, Map<String, Object> params) {
		int result = update(statementNm, params);
		return result;
	}

	/**
	 * <pre>
	 * 설명 : 삭제
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 15.
	 * @author : jdh
	 * @param namespace+"."+id
	 * @param params
	 * @return
	 */
	public int deleteCommon(String statementNm, Map<String, Object> params) {
		int result = delete(statementNm, params);
		return result;
	}

}
