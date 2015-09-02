package kr.co.cylearn.common.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDaoForEmail {
	private static Logger log = LoggerFactory.getLogger(BaseDaoForEmail.class);

	@Resource(name = "sqlSessionTemplateForEmail")
	protected SqlSessionTemplate sqlSessionTemplate;

	// -- selectOne
	public <T> T selectOne(String statement) {
		return sqlSessionTemplate.<T>selectOne(statement);
	}

	public <T> T selectOne(final String statement, final Object parameter) {
		return sqlSessionTemplate.<T>selectOne(statement, parameter);
	}

	// -- selectMap
	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return sqlSessionTemplate.<K, V>selectMap(statement, mapKey);
	}

	public <K, V> Map<K, V> selectMap(final String statement,
			final Object parameter, final String mapKey) {
		return sqlSessionTemplate
				.<K, V>selectMap(statement, parameter, mapKey);
	}

	public <K, V> Map<K, V> selectMap(final String statement,
			final Object parameter, final String mapKey,
			final RowBounds rowBounds) {
		return sqlSessionTemplate.<K, V>selectMap(statement, parameter,
				mapKey, rowBounds);
	}

	// --selectList
	public <E> List<E> selectList(String statement) {
		return sqlSessionTemplate.<E>selectList(statement);
	}

	public <E> List<E> selectList(final String statement, final Object parameter) {
		return sqlSessionTemplate.<E>selectList(statement, parameter);
	}

	public <E> List<E> selectList(final String statement,
			final Object parameter, final RowBounds rowBounds) {
		return sqlSessionTemplate.<E>selectList(statement, parameter,
				rowBounds);
	}

	// -- select
	public void select(String statement, ResultHandler handler) {
		sqlSessionTemplate.select(statement, handler);
	}

	public void select(final String statement, final Object parameter,
			final ResultHandler handler) {
		sqlSessionTemplate.select(statement, parameter, handler);
	}

	public void select(final String statement, final Object parameter,
			final RowBounds rowBounds, final ResultHandler handler) {
		sqlSessionTemplate.select(statement, parameter, rowBounds, handler);
	}

	// -- insert
	public int insert(String statement) {
		return sqlSessionTemplate.insert(statement);
	}

	public int insert(final String statement, final Object parameter) {
		return sqlSessionTemplate.insert(statement, parameter);
	}

	// -- update
	public int update(String statement) {
		return sqlSessionTemplate.update(statement);
	}

	public int update(final String statement, final Object parameter) {
		return sqlSessionTemplate.update(statement, parameter);
	}

	// -- delete
	public int delete(String statement) {
		return sqlSessionTemplate.delete(statement);
	}

	public int delete(final String statement, final Object parameter) {
		return sqlSessionTemplate.delete(statement, parameter);
	}

}
