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
	 * ���� : �ٰ� ��ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          ���� �ۼ�
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
	 * ���� : �ٰ� ��ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 13. jdh          ���� �ۼ�
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
	 * ���� : �ܰ� ��ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 5. jdh          ���� �ۼ�
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
	 * ���� : �ܰ� ��ȸ(ResultHashMap)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 13. jdh          ���� �ۼ�
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
	 * ���� : String�� �Ѱ�
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 16. ilsunni          ���� �ۼ�
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
	 * ���� : Int �� �Ѱ� (ī��Ʈ/MAX SEQ ��)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 6. jdh          ���� �ۼ�
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
	 * ���� : ���
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          ���� �ۼ�
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
	 * ���� : ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          ���� �ۼ�
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
	 * ���� : ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          ���� �ۼ�
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
