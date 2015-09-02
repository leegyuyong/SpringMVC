package kr.co.cylearn.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class StringUtil {

	/**
	 * XSS ó���� �� �� ����Ÿ�� ��ȯ�Ѵ�.
	 *
	 * @param param
	 * @return
	 */
	public static String defaultXSSCheck(String param) {
		param = StringUtil.defaultString(param, "");
		return defaultXSS(param);
	}

	/**
	 * XSS ó���� �� �� ����Ÿ�� ��ȯ�Ѵ�.(�⺻���� �߰��Ѵ�.)
	 *
	 * @param param
	 * @return
	 */
	public static String defaultXSSCheck(String param, String def) {
		param = StringUtil.defaultString(param, def);
		return defaultXSS(param);
	}

	/**
	 * XSS �� ó���Ѵ�.
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
	 * null���� ���ؼ� default�� def�� ���ڰ��� ��ȯ�Ѵ�. �ƴ϶�� �⺻ ����Ÿ�� ��ȯ�Ѵ�.
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
	 * ���� : map �Ķ���� ���� String���� ��ȯ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 28. jdh          ���� �ۼ�
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
	 * ���� : map �Ķ���� ���� Int ���� ��ȯ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 28. jdh          ���� �ۼ�
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
	 * �н����� ��Ȳ�� ���� ���� ������ �´�.
	 *
	 * @param lesson
	 * @param firstEdu
	 * @param firstEnd
	 * @return Y : �н� �Ϸ� N : ���� ������ , �н��Ϸ����� ����
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
	 * �ش� ���ڿ����� older String �� newer String ���� ��ü�Ѵ�.
	 *
	 * @param original
	 *            ��ü String
	 * @param older
	 *            ��ü String �� ��ü �� ���� String
	 * @param newer
	 *            ��ü String �� ��ü �� ���� String
	 * @return result ��ü�� ���ڿ��� ��ȯ��
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
	 * ������ ���ڿ� �޸��� �Է��Ѵ�.
	 *
	 * @param str
	 * @return
	 */
	public static String getComma(String str) {
		return getComma(str, true);
	}

	/**
	 * ������ ���ڿ� �޸��� �Է��Ѵ�.
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
	 * Session_time ���
	 *
	 * @param String
	 *            �����Ͻ�(varchar2 14), �����Ͻ�(varchar2 14)
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
	 * Session_time ���ϱ�
	 *
	 * @param String
	 *            ���ҳ�1(03:01:39.52), ���ҳ�2(03:01:39.52)
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
	 * HTML �����ϰ� Text�� ������ ����
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
	 * ������ ������ STYLE �±׷� ������ �κ��� �����Ѵ�.
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
	 * ���� : �޴��� ��ȣ�� �����ϰ� ��ȯ�Ͽ� ǥ��(����-�չ�ȣ-�޹�ȣ)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2013. 6. 7. jdh          ���� �ۼ�
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

		// �޴��� ����
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

				// ���� ����
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
	 * ���� : �޴��� ��ȣ�� �����ϰ� �迭�� ��ȯ�Ѵ�.(����-�չ�ȣ-�޹�ȣ)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2013. 6. 7. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2013. 6. 7.
	 * @author : jdh
	 * @param number
	 *            010-1234-5678 or 01012345678 ....
	 * @return ���Ŀ� ���� ��� �迭�� ���� �ƴҰ�� null
	 */
	public static String[] getHandPhoneArray(String number) {

		String phone_num = "";
		String phone_num1 = "";
		String phone_num2 = "";
		String phone_num3 = "";
		String phone_num0 = "";
		String[] result_num = null;

		// �޴��� ����
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

				// ���� ����
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
	 * ���� : ��ȭ��ȣ�� �����ϰ� �迭�� ��ȯ�Ѵ�.(����-�չ�ȣ-�޹�ȣ)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2013. 6. 7. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2013. 6. 7.
	 * @author : jdh
	 * @param number
	 *            02-1234-5678
	 * @return ���Ŀ� ���� ��� �迭�� ���� �ƴҰ�� null
	 */
	public static String[] getPhoneNumberArray(String number) {

		String phone_num = "";
		String phone_num1 = "";
		String phone_num2 = "";
		String phone_num3 = "";
		String[] result_num = null;

		// ��������
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
	 * ���� : �ѱ�ó��
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2014. 9. 29. jdh          ���� �ۼ�
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
	 * ���� : html���ڿ��� ��ȯ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 17. jdh          ���� �ۼ�
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
	 * ���� : html���ڿ��� ��ȯ(����)
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 17. jdh          ���� �ۼ�
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
	 * ���� : ���񽺸�
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 30. jdh          ���� �ۼ�
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
	 * ���� : ¦�� ���ڸ� ������ ���ڷ� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 5. 11. jdh          ���� �ۼ�
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
