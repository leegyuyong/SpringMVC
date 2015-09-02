package kr.co.cylearn.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class StringUtil {

	/**
	 * XSS 처리를 한 후 데이타를 반환한다.
	 *
	 * @param param
	 * @return
	 */
	public static String defaultXSSCheck(String param) {
		param = StringUtil.defaultString(param, "");
		return defaultXSS(param);
	}

	/**
	 * XSS 처리를 한 후 데이타를 반환한다.(기본값을 추가한다.)
	 *
	 * @param param
	 * @return
	 */
	public static String defaultXSSCheck(String param, String def) {
		param = StringUtil.defaultString(param, def);
		return defaultXSS(param);
	}

	/**
	 * XSS 를 처리한다.
	 *
	 * @param param
	 * @return
	 */
	public static String defaultXSS(String param) {
		param = param.replaceAll("&", "&amp;");
		param = param.replaceAll("<", "&lt;");
		param = param.replaceAll(">", "&gt;");
		param = param.replaceAll("%00", null);
		param = param.replaceAll("\"", "&#34;");
		param = param.replaceAll("\'", "&#39;");
		param = param.replaceAll("%", "&#37;");

		param = param.replaceAll("../", "");
		param = param.replaceAll("..\\\\", "");
		param = param.replaceAll("./", "");
		param = param.replaceAll("%2F", "");

		return param;
	}

	/**
	 * null값에 대해서 default로 def의 인자값을 반환한다. 아니라면 기본 데이타를 반환한다.
	 *
	 * @param param
	 * @param def
	 * @return
	 */
	public static String defaultString(String param, String def) {
		if (param == null || param.equals("")) return def;

		return param;
	}

	public static int defaultInteger(String param) {
		param = StringUtil.defaultString(param, "");

		if (param.equals("")) return 0;

		return Integer.valueOf(param);
	}

	/**
	 * <pre>
	 * 설명 : map 파라미터 값을 String으로 반환
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 28. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 28.
	 * @author : jdh
	 * @param map map.get("paramName")
	 * @param def "String"
	 * @return
	 */
	public static String mapDefaultString(Object param, String def) {
		if (param == null) 	return def;
		if ("".equals(String.valueOf(param))) return def;
		return String.valueOf(param);
	}

	/**
	 * <pre>
	 * 설명 : map 파라미터 값을 Int 으로 반환
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 28. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 28.
	 * @author : jdh
	 * @param param
	 * @param def
	 * @return
	 */
	public static int mapDefaultInt(Object param, int def) {
		if (param == null) 	return def;
		if (Integer.parseInt(param.toString()) == 0) return def;
		return Integer.parseInt(param.toString());
	}

	/**
	 * String null check
	 * @param argsData
	 * @param argsStr
	 * @return
	 */
	public static String nullToStr(String argsData, String argsStr){
		if(argsData == null) {
			return argsStr;
		} else {
			return argsData;
		}
	}

	/**
	 * object null check
	 * @param argsData
	 * @param argsStr
	 * @return
	 */
	public static String nullToStr(Object argsData, String argsStr){
		if(argsData == null) {
			return argsStr;
		} else {
			return (String)argsData;
		}
	}

	/**
	 * 학습진행 상황에 대한 값을 가지고 온다.
	 *
	 * @param lesson
	 * @param firstEdu
	 * @param firstEnd
	 * @return Y : 학습 완료 N : 현재 진행중 , 학습완료하지 않음
	 */
	public static String getProgressStatus(String lesson, String firstEdu, String firstEnd) {
		String result = "";

		if (!lesson.equals("00")) {
			if (firstEdu != null && firstEnd != null && !firstEdu.equals("") && !firstEnd.equals("")) {
				result = "Y";
			} else if (firstEdu != null && !firstEdu.equals("")) {
				result = "N";
			} else {
				result = "N";
			}
		}

		return result;
	}

	/**
	 * 해당 문자열에서 older String 을 newer String 으로 교체한다.
	 *
	 * @param original
	 *            전체 String
	 * @param older
	 *            전체 String 중 교체 전 문자 String
	 * @param newer
	 *            전체 String 중 교체 후 문자 String
	 * @return result 교체된 문자열을 반환함
	 */
	public static String replace(String original, String older, String newer) {
		String result = original;

		if (original != null) {
			int idx = result.indexOf(older);
			int newLength = newer.length();

			while (idx >= 0) {
				if (idx == 0) {
					result = newer + result.substring(older.length());
				} else {
					result = result.substring(0, idx) + newer + result.substring(idx + older.length());
				}
				idx = result.indexOf(older, idx + newLength);
			}
		}

		return result;
	}

	/**
	 * 문자형 숫자에 콤마를 입력한다.
	 *
	 * @param str
	 * @return
	 */
	public static String getComma(String str) {
		return getComma(str, true);
	}

	/**
	 * 문자형 숫자에 콤마를 입력한다.
	 *
	 * @param str
	 * @return
	 */
	public static String getComma(String str, boolean isTruncated) {
		if (str == null) return "0";
		if (str.trim().equals("")) return "0";
		if (str.trim().equals("&nbsp;")) return "&nbsp;";
		int pos = str.indexOf(".");
		if (pos != -1) {
			if (!isTruncated) {
				DecimalFormat commaFormat = new DecimalFormat("#,##0.00");
				return commaFormat.format(Float.parseFloat(str.trim()));
			} else {
				DecimalFormat commaFormat = new DecimalFormat("#,##0");
				return commaFormat.format(Long.parseLong(str.trim().substring(0, pos)));
			}
		} else {
			DecimalFormat commaFormat = new DecimalFormat("#,##0");
			return commaFormat.format(Long.parseLong(str.trim()));
		}
	}

	/**
	 * Session_time 얻기
	 *
	 * @param String
	 *            시작일시(varchar2 14), 종료일시(varchar2 14)
	 * @return String session_time string (03:01:39.52)
	 */
	public static String getDuringtime(String p_s, String p_e) {
		String results = "";
		int gap = 0;
		try {
			long l_gap = FormatDate.getTimeDifference(p_s, p_e);
			int hh = (int) (l_gap / (1000 * 60 * 60));
			gap = (int) (l_gap - hh * 1000 * 60 * 60);
			int mm = (int) (gap / (1000 * 60));
			gap = gap - mm * 1000 * 60;
			int ss = (int) (gap / 1000);
			gap = gap - ss * 1000;
			int ms = gap;
			results = (new DecimalFormat("00").format(hh)) + ":" + (new DecimalFormat("00").format(mm)) + ":" + (new DecimalFormat("00").format(ss)) + "." + (new DecimalFormat("00").format(ms));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

		return results;
	}

	/**
	 * Session_time 더하기
	 *
	 * @param String
	 *            더할넘1(03:01:39.52), 더할넘2(03:01:39.52)
	 * @return String total_time string (03:01:39.52)
	 */
	public static String addDuringtime(String p_s, String p_e) {
		String results = "";

		int hh = 0;
		int mm = 0;
		double ss = 0.0;

		ss = Double.parseDouble(getSecond(p_s)) + Double.parseDouble(getSecond(p_e));
		if (ss > 60.0) {
			ss = ss - 60.0;
			mm = mm + 1;
		}

		mm = mm + Integer.parseInt(getMinute(p_s)) + Integer.parseInt(getMinute(p_e));
		if (mm > 60) {
			mm = mm - 60;
			hh = hh + 1;
		}
		hh = hh + Integer.parseInt(getHour(p_s)) + Integer.parseInt(getHour(p_e));

		results = (new DecimalFormat("00").format(hh)) + ":" + (new DecimalFormat("00").format(mm)) + ":" + (new DecimalFormat("00.00").format(ss));

		return results;
	}

	private static String getHour(String time) {
		return getTime(time, "HOUR");
	}

	private static String getMinute(String time) {
		return getTime(time, "MINUTE");
	}

	private static String getSecond(String time) {
		return getTime(time, "SECOND");
	}

	private static String getTime(String time, String type) {
		String result = "";
		String[] str = time.split(":");

		if (str == null || str.length != 3) {
			return "00";
		}

		if ("HOUR".equals(type)) {
			result = str[0];
		} else if ("MINUTE".equals(type)) {
			result = str[1];
		} else if ("SECOND".equals(type)) {
			result = str[2];
		}

		return result;
	}

	/**
	 * HTML 제거하고 Text만 가지고 오기
	 *
	 * @param param
	 * @return
	 */
	public static String replaceHtml(String param) {
		String result = param.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		result = result.replaceAll("\n", "<br/>");

		return result;
	}

	/**
	 * 본문의 내용중 STYLE 태그로 감싸진 부분을 제거한다.
	 *
	 * @param contents
	 * @return
	 */
	public static String replaceStyle(String contents) {

		int startPos = contents.indexOf("<BODY>");

		if (startPos > 1) {
			startPos = startPos + 6;
			int endPos = contents.indexOf("</BODY>");
			String result = contents.substring(startPos, endPos);

			return replaceHtml(result);
		}

		return contents;
	}

	/**
	 * <pre>
	 * 설명 : 휴대폰 번호를 검증하고 변환하여 표시(국번-앞번호-뒷번호)
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2013. 6. 7. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2013. 6. 7.
	 * @author : jdh
	 * @param str
	 *            010-1234-5678 or 01012345678 ....
	 * @return 000-0000-0000
	 */
	public static String getHandPhone(String str) {

		String phone_num = "";
		String phone_num1 = "";
		String phone_num2 = "";
		String phone_num3 = "";
		String result_num = "";

		// 휴대폰 국번
		String s[] = { "010", "011", "016", "017", "018", "019" };

		boolean isvalid = false;

		try {
			if (str != null) {
				phone_num = str;
				phone_num = phone_num.replace("-", "");

				if (phone_num.length() == 10) {
					phone_num1 = phone_num.substring(0, 3);
					phone_num2 = phone_num.substring(3, 6);
					phone_num3 = phone_num.substring(6, 10);
				} else if (phone_num.length() == 11) {
					phone_num1 = phone_num.substring(0, 3);
					phone_num2 = phone_num.substring(3, 7);
					phone_num3 = phone_num.substring(7, 11);
				} else {
					return "";
				}

				// 국번 검증
				for (int i = 0; i < s.length; i++) {
					if (s[i].equals(phone_num1)) {
						isvalid = true;
						continue;
					}
				}

				if (isvalid) {
					result_num = phone_num1 + "-" + phone_num2 + "-" + phone_num3;
				}
			}
		} catch (Exception e) {

		}
		return result_num;
	}

	/**
	 * <pre>
	 * 설명 : 휴대폰 번호를 검증하고 배열로 반환한다.(국번-앞번호-뒷번호)
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2013. 6. 7. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2013. 6. 7.
	 * @author : jdh
	 * @param number
	 *            010-1234-5678 or 01012345678 ....
	 * @return 형식에 맞을 경우 배열로 리턴 아닐경우 null
	 */
	public static String[] getHandPhoneArray(String number) {

		String phone_num = "";
		String phone_num1 = "";
		String phone_num2 = "";
		String phone_num3 = "";
		String phone_num0 = "";
		String[] result_num = null;

		// 휴대폰 국번
		String s[] = { "010", "011", "016", "017", "018", "019" };

		boolean isvalid = false;

		try {
			if (number != null) {
				phone_num = number;
				phone_num = phone_num.replace("-", "");

				if (phone_num.length() == 10) {
					phone_num1 = phone_num.substring(0, 3);
					phone_num2 = phone_num.substring(3, 6);
					phone_num3 = phone_num.substring(6, 10);
				} else if (phone_num.length() == 11) {
					phone_num1 = phone_num.substring(0, 3);
					phone_num2 = phone_num.substring(3, 7);
					phone_num3 = phone_num.substring(7, 11);
				} else {
					return null;
				}

				// 국번 검증
				for (int i = 0; i < s.length; i++) {
					if (s[i].equals(phone_num1)) {
						isvalid = true;
						continue;
					}
				}

				if (isvalid) {
					phone_num0 = phone_num1 + "-" + phone_num2 + "-" + phone_num3;
					result_num = phone_num0.split("-");
				}
			}
		} catch (Exception e) {

		}
		return result_num;
	}

	/**
	 * <pre>
	 * 설명 : 전화번호를 검증하고 배열로 반환한다.(국번-앞번호-뒷번호)
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2013. 6. 7. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2013. 6. 7.
	 * @author : jdh
	 * @param number
	 *            02-1234-5678
	 * @return 형식에 맞을 경우 배열로 리턴 아닐경우 null
	 */
	public static String[] getPhoneNumberArray(String number) {

		String phone_num = "";
		String phone_num1 = "";
		String phone_num2 = "";
		String phone_num3 = "";
		String[] result_num = null;

		// 지역국번
		// String s[] = { "02", "031", "032", "033", "041", "042", "043", "051", "052", "053", "054", "055", "061", "062", "063", "064", "070" };

		// boolean isvalid = false;

		try {
			if (number != null) {
				phone_num = number;

				if (phone_num.indexOf("-") > 0) {
					result_num = phone_num.split("-");
				}
			}
		} catch (Exception e) {

		}
		return result_num;
	}

	/**
	 * <pre>
	 * 설명 : 한글처리
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2014. 9. 29. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2014. 9. 29.
	 * @author : jdh
	 * @param str
	 * @param key
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String[] korEncode(String[] str, String key) throws UnsupportedEncodingException {
		String[] tmp = null;
		if (str == null) return null;
		else {

			tmp = new String[str.length];

			for (int i = 0; i < str.length; i++) {
				tmp[i] = new String(str[i].getBytes("8859_1"), "EUC-KR");
				// System.out.println(key+":=============>"+tmp[i]);
			}
		}
		return tmp;
	}

	/**
	 * <pre>
	 * 설명 : html문자열로 변환
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 17. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 17.
	 * @author : jdh
	 * @param str
	 * @return
	 */
	public static String htmlSpecialChar(String str) {
		if (str == null) return "";

		str = str.replaceAll("&", "&amp;");

		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\'", "&apos;");
		str = str.replaceAll("\"", "&quot");

		str = str.replaceAll("\r\n", "<BR>");
		str = str.replaceAll("\n", "<BR>");

		return str;
	}

	/**
	 * <pre>
	 * 설명 : html문자열로 변환(엑셀)
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 17. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 17.
	 * @author : jdh
	 * @param str
	 * @return
	 */
	public static String htmlSpecialCharForExcel(String str) {
		if (str == null) return "";

		str = str.replaceAll("&", "&amp;");

		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\'", "&apos;");
		str = str.replaceAll("\"", "&quot");

		return str;
	}

	/**
	 * <pre>
	 * 설명 : 서비스명
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 30.
	 * @author : jdh
	 * @param serviceName
	 * @return
	 */
	public static String getServiceName(String serviceClassName) {
		return serviceClassName.substring(serviceClassName.lastIndexOf(".") + 1, serviceClassName.lastIndexOf("Service"));
	}

	/**
	 * <pre>
	 * 설명 : 짝수 문자를 지정한 문자로 변경
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 5. 11. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 5. 11.
	 * @author : jdh
	 * @param str
	 * @param hiddenStr
	 * @return
	 */
	public static String hiddenString(String str, String hiddenStr) {

		String chgStr = "";

		if (str != null && !"".equals(str)){
			int len = str.length();
			char[] arr = str.toCharArray();

			for (int j = 0; j < len; j++) {

				if ((j + 1) % 2 == 0) {
					chgStr += hiddenStr;
				} else {
					chgStr += arr[j];
				}
			}
		} else {
			chgStr = str;
		}

		return chgStr;
	}
}
