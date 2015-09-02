package kr.co.cylearn.common.db;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "select", args = { Statement.class }),
		@Signature(type = StatementHandler.class, method = "query", args = {
				Statement.class, ResultHandler.class }) })
public class MybatisLogInterceptor implements Interceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		StatementHandler handler = (StatementHandler) invocation.getTarget();

		BoundSql boundSql = handler.getBoundSql();

		// �������� �����´�(�� ���¿����� ������ ���� �� �κп� ?�� �ִ�)
		String sql = boundSql.getSql();

		// ��������� ���εǴ� �Ķ���͸� ���Ѵ�
		Object param = handler.getParameterHandler().getParameterObject();

		if (param == null) { // �Ķ���Ͱ� �ƹ��͵� ���� ���
			sql = sql.replaceFirst("\\?", "''");
		} else { // �ش� �Ķ������ Ŭ������ Integer, Long, Float, Double Ŭ������ ���
			if (param instanceof Integer || param instanceof Long
					|| param instanceof Float || param instanceof Double) {
				sql = sql.replaceFirst("\\?", param.toString());
			} else if (param instanceof String) { // �ش� �Ķ������ Ŭ������ String �� ���(��
													// ���� �յڿ� '(Ȭ����ǥ)�� �ٿ����ؼ� ����
													// ó��
				sql = sql.replaceFirst("\\?", "'" + param + "'");
			} else if (param instanceof Map) { // �ش� �Ķ���Ͱ� Map �� ���

				/*
				 * ������ ?�� ���εǴ� ���� ������ ������ ����ִ� ParameterMapping ��ü�� �� List ��ü��
				 * return�� �ȴ�. �̶� List ��ü�� 0��° ������ �ִ� ParameterMapping ��ü�� ������
				 * ù��° ?�� ������ �ȴ� �̷� ������ ������ ?�� ParameterMapping ��ü���� Mapping �Ѵ�
				 */
				List<ParameterMapping> paramMapping = boundSql
						.getParameterMappings();

				for (ParameterMapping mapping : paramMapping) {
					String propValue = mapping.getProperty(); // �Ķ���ͷ� �ѱ� Map��
																// key ���� ������
																// �ȴ�
					Object value = ((Map) param).get(propValue); // �Ѱܹ��� key ����
																	// �̿��� ���� ����
																	// ������
					if (value instanceof String) { // SQL�� ? ��ſ� ���� ���� �ִ´�. �̶�
													// String �� ���� '�� �ٿ��� �ϱⶫ��
													// ���� ó��
						sql = sql.replaceFirst("\\?", "'" + value + "'");
					} else {
						sql = sql.replaceFirst("\\?", value.toString());
					}

				}
			} else { // �ش� �Ķ���Ͱ� ����� ���� Ŭ������ ���

				/*
				 * ������ ?�� ���εǴ� ���� ������ List ��ü�� return�� �ȴ�. �̶� List ��ü�� 0��° ������
				 * �ִ� ParameterMapping ��ü�� ������ ù��° ?�� ������ �ȴ� �̷� ������ ������ ?��
				 * ParameterMapping ��ü���� Mapping �Ѵ�
				 */
				List<ParameterMapping> paramMapping = boundSql
						.getParameterMappings();

				Class<? extends Object> paramClass = param.getClass();
				// logger.debug("paramClass.getName() : {}",
				// paramClass.getName());
				for (ParameterMapping mapping : paramMapping) {
					String propValue = mapping.getProperty(); // �ش� �Ķ���ͷ� �Ѱܹ���
																// ����� ���� Ŭ����
																// ��ü�� ���������
					Field field = paramClass.getDeclaredField(propValue); // ����
																			// �������
																			// Field
																			// ��ü
																			// ����
					field.setAccessible(true); // ��������� �����ڰ� private�� ���
												// reflection�� �̿��Ͽ� ���� �ش� ���������
												// ���� �������� ���� ������ ����
					Class<?> javaType = mapping.getJavaType(); // �ش� �Ķ���ͷ� �Ѱܹ���
																// ����� ���� Ŭ����
																// ��ü�� ��������� Ÿ��

					if (String.class == javaType) { // SQL�� ? ��ſ� ���� ���� �ִ´�. �̶�
													// String �� ���� '�� �ٿ��� �ϱⶫ��
													// ���� ó��
						sql = sql.replaceFirst("\\?", "'" + field.get(param)
								+ "'");
					} else {
						sql = sql.replaceFirst("\\?", field.get(param)
								.toString());
					}

				}
			}

		}

		logger.debug("=====================================================================");
		logger.debug("sql : {}", sql);
		logger.debug("=====================================================================");

		return invocation.proceed(); // ���� ����
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}