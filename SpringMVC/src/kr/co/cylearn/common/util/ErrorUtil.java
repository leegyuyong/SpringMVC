package kr.co.cylearn.common.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import kr.co.cylearn.common.manager.ErrorManager;

import org.apache.log4j.Logger;


public class ErrorUtil {

	private static final Logger log = Logger.getLogger(ErrorUtil.class);

	/**
	 * HashMap으로 에러 메세지와 코드를 리턴한다.
	 * @param resultCode
	 * @return
	 */
	public static HashMap<String,String> getErrorModel(String resultCode)
	{
		HashMap<String,String> map = new HashMap<String,String>();

		String errorMsg = getErrorMsg(resultCode);

		map.put("errorCode", resultCode);
		map.put("errorMsg" , errorMsg);

		return map;
	}


	/**
	 * 에러 코드에 대응하는 에러 메시지를 가지고 온다.
	 * @param resultCode
	 * @return
	 */
	public static String getErrorMsg(String resultCode)
	{
		ErrorManager manager = ErrorManager.getInstance();

		String errorMsg = manager.get(resultCode);

		/*
		try
		{
			errorMsg = new String(errorMsg.getBytes("8859_1") , "EUC-KR");
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		return errorMsg;
	}



}
