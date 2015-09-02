package kr.co.cylearn.common.util;
/**
 * ������ ���õ� ������ ó���Ѵ�.
 * @author dongphil.kim
 *
 */
public class DomainUtil { 
	
	
	/**
	 * url�� https �������� ��ȯȯ��.
	 * @param url
	 * @return
	 */
	public static String getHttpsDomain(String url){
		if(null == url) {
			return "";
		}
		url = url.toLowerCase().replaceAll("https://", "").replaceAll("http://", "").split(":")[0] ;
		
		// cylearn �� �������� �ƴϸ� http�� ó���Ͽ� ��ȯ�Ѵ�.
		if(url.indexOf("cylearn.co.kr") > -1) {
			return "https://"+url;
		} else {
			return "http://"+url;
		}
	}
	
	/**
	 * url�� http �������� ��ȯ�Ѵ�.
	 * @param url
	 * @return
	 */
	public static String getHttpDomain(String url){
		if(null == url) {
			return "";
		}
		url = url.toLowerCase().replaceAll("https://", "").replaceAll("http://", "").split(":")[0] ;
		
		return "http://"+url;

	}
	
  /**
   * url�� http://localhost ���� �����θ� �����´�.  ù��° '/' ���������� ������
   * @param url
   * @return
   */
  public static String getMainDomain(String url){
    if(null == url) {
      return "";
    }
    url = url.substring(0,url.indexOf("/", 8));
    
    return url;

  }	
}
