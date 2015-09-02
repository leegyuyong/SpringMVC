package kr.co.cylearn.common.base;

import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.co.cylearn.common.util.StringUtil;
import kr.co.cylearn.model.UserSession;

import org.apache.log4j.Logger;

public class BaseController {
	private final static Logger log = Logger.getLogger(BaseController.class);

	public static final String RESULT_OK = "OK";
	public static final String RESULT_FAIL = "FAIL";

	public static final String NOTICE_DEFAULT_TABSEQ = "12";
	public static final String EVENT_DEFAULT_TABSEQ = "5";

	/**
	 * <pre>
	 * ���� : ���̵�(����)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 29. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 29.
	 * @author : jdh
	 * @param session
	 * @return
	 */
	public static String getUserId(HttpSession session) {
		if((UserSession)session.getAttribute("userSession")==null){
			return "";
		}else{
			return StringUtil.defaultString(((UserSession)session.getAttribute("userSession")).getUserid(),"");
		}
	}


	/**
	 * <pre>
	 * ���� : �̸�(����)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 29. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 29.
	 * @author : jdh
	 * @param session
	 * @return
	 */
	public static String getName(HttpSession session) {
		if((UserSession)session.getAttribute("userSession")==null){
			return "";
		}else{
			return StringUtil.defaultString(((UserSession)session.getAttribute("userSession")).getName(),"");
		}
	}

}
