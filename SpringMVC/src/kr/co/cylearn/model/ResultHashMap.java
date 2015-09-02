package kr.co.cylearn.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

@SuppressWarnings({ "serial", "rawtypes" })
public class ResultHashMap extends HashMap{

	private static Logger log = Logger.getLogger(ResultHashMap.class);

	public String getResult(){
		return getString("result");
	}

	@SuppressWarnings("unchecked")
	public void setResultOk(){
		put("result", "OK");
	}

	@SuppressWarnings("unchecked")
	public void setResultOk(String msg){
		put("result", "OK");
		put("msg", msg);
	}

	@SuppressWarnings("unchecked")
	public void setResultFail(String msg){
		put("result", "FAIL");
		//put("errorCode", code);
		//put("msg", ErrorUtil.getErrorModel(code));
		put("msg", msg);
	}

	/**
	 * <pre>
	 * ��Ʈ������ ĳ���� �Ͽ� ��ȯ�Ѵ�.
	 * </pre>
	 * @param key
	 * @return
	 */
	public String getString(String key)
	{
		if(key == null)
			return "";

		if(this.containsKey(key))
		{
			//log.info("[DEBUG] : " + this.get(key).getClass().getName());

			if(this.get(key) == null)
				return "";

			return this.get(key).toString();
		}

		return "";
	}

	/**
	 * <pre>
	 * Integer�� ĳ�����Ͽ� ��ȯ�Ѵ�.
	 * </pre>
	 * @param key
	 * @return
	 */
	public int getInt(String key)
	{
		if(key == null)
			return 0;

		if(this.containsKey(key))
		{
			if(this.get(key) == null)
				return 0;

			// get object type
			if(this.get(key).getClass().getName().equals("java.math.BigDecimal"))
			{
				// BigDecimal �� ��� int�� �纯ȯ�Ѵ�...
				BigDecimal tmp = (BigDecimal)this.get(key);
				return tmp.intValue();
			}
			try {
				return Integer.parseInt(this.get(key).toString());
			} catch( Exception e ) {
				log.error(e);
				return 0;
			}
		}

		return 0;
	}

	/**
	 * <pre>
	 * Float���� ĳ�����Ͽ� ��ȯ�Ѵ�.
	 * </pre>
	 * @param key
	 * @return
	 */
	public float getFloat(String key)
	{
		if(key == null)
			return 0.00f;

		if(this.containsKey(key))
		{
			if(this.get(key) == null)
				return 0.00f;

			// BigDecimal���� Float���� ��ȯ�Ѵ�.
			return Float.valueOf(this.get(key).toString());
		}

		return 0.00f;
	}

	/**
	 * <pre>
	 * Double�� ĳ�����Ͽ� ��ȯ�Ѵ�.
	 * </pre>
	 * @param key
	 * @return
	 */
	public double getDouble(String key)
	{
		if(key == null)
			return 0.00f;

		if(this.containsKey(key))
		{
			if(this.get(key) == null)
				return 0.00f;

			// BigDecimal���� Float���� ��ȯ�Ѵ�.
			return Double.valueOf(this.get(key).toString());
		}

		return 0.00f;
	}

	/**
	 * <pre>
	 * ResultHashMap�� ĳ�����Ͽ� ��ȯ�Ѵ�.
	 * </pre>
	 * @param key
	 * @return
	 */
	public ResultHashMap getHashMap(String key)
	{
		if(key == null)
			return null;

		if(this.containsKey(key))
		{
			if(this.get(key) == null)
				return null;

			return (ResultHashMap)this.get(key);
		}

		return null;
	}

	/**
	 * <pre>
	 * List�ε� ResultHashMap�� ĳ���� �Ͽ� ��ȯ�Ѵ�.
	 * </pre>
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResultHashMap> getList(String key)
	{
		if(key == null)
			return null;

		if(this.containsKey(key))
		{
			if(this.get(key) == null)
				return null;

			return (List<ResultHashMap>)this.get(key);
		}

		return null;
	}

}
