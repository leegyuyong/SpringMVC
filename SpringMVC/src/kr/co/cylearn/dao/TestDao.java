package kr.co.cylearn.dao;

import java.util.List;
import java.util.Map;

import kr.co.cylearn.common.base.BaseDao;

import org.springframework.stereotype.Component;

@Component
public class TestDao extends BaseDao {
	private static final String NAMESPACE = TestDao.class.getName();

	/**
	 * <pre>
	 * ���� : �׽�Ʈ ��� ��ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectTestList(Map<String, Object> params) {
		List<Map<String, Object>> result = selectList(NAMESPACE + ".selectTestList", params);
		return result;
	}

	/**
	 * <pre>
	 * ���� : �׽�Ʈ �� ��ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 5. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 5.
	 * @author : jdh
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectTestOne(Map<String, Object> params) {
		Map<String, Object> result = selectOne(NAMESPACE + ".selectTestOne", params);
		return result;
	}
}
