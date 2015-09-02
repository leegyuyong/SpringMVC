package kr.co.cylearn.common.util;
/**
 * 도메인 관련된 정보를 처리한다.
 * @author dongphil.kim
 *
 */
public class DomainUtil { 
	
	
	/**
	 * url의 https 도메인을 반환환다.
	 * @param url
	 * @return
	 */
	public static String getHttpsDomain(String url){
		if(null == url) {
			return "";
		}
		url = url.toLowerCase().replaceAll("https://", "").replaceAll("http://", "").split(":")[0] ;
		
		// cylearn 의 도메인이 아니면 http로 처리하여 반환한다.
		if(url.indexOf("cylearn.co.kr") > -1) {
			return "https://"+url;
		} else {
			return "http://"+url;
		}
	}
	
	/**
	 * url의 http 도메인을 반환한다.
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
   * url의 http://localhost 메인 도메인만 가져온다.  첫번째 '/' 이전까지의 도메인
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
