package kr.co.cylearn.common.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * <pre>
 * kr.co.cylearn.common.manager
 * ConfigManager.java
 *
 * 설명 : 프로퍼티 관리
 * </pre>
 *
 * @since : 2015. 7. 30.
 * @author : jdh
 * @version : v1.0
 */
public class ConfigManager {

	// instance
	private static ConfigManager instance = null;

	// properties info
	private final static String PROPERTY_FILE = "common.properties";

	// properties
	Properties prop = null;

	public static ConfigManager getInstance()
	{
		if(instance == null)
		{
			synchronized (ConfigManager.class) {
				if(instance == null)
					instance = new ConfigManager();
			}
		}

		return instance;
	}


	private ConfigManager()
	{
		// set file path
		prop = new Properties();

		try
		{
			String abPath = this.getClass().getResource("").getPath();
			String[] tmp = abPath.split("classes");
			String path = tmp[0] + File.separator + "properties" + File.separator + PROPERTY_FILE;
			InputStream stream = new FileInputStream(path);
			prop.load(stream);
			stream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public String get(String key)
	{
		if(prop.containsKey(key))
			return prop.getProperty(key);

		return "";
	}

	public int getInt(String key) {
		int value = -1;

		if(prop.containsKey(key))
			value = Integer.parseInt(prop.getProperty(key));

		return value;
	}

	public boolean getBoolean(String key) {
		boolean value = false;

		if(prop.containsKey(key))
			value = (new Boolean(prop.getProperty(key))).booleanValue();

		return value;
	}

	/**
	 * 시스템 환경설정 config 파일(common.properties)의 설정값중에서 파일이 업로드되는 디렉토리명을 String type 으로 리턴한다.
	 * @param key common.properties 의 각 옵션별 설정명
	 * @param serviceName 파일을 업로드하는 serviceName
	 * @return String 파일업로드될 디렉토리명을 반환한다.
	 */
	public String getSearchKey(String key, String serviceName) {
		String str = get(key);
		String dirKey = "";
		StringTokenizer st = new StringTokenizer(str, ";");
		while (st.hasMoreElements()) {
			String token = st.nextToken();

			int isDir = serviceName.toLowerCase().indexOf(token);

			if (isDir > -1) {
				dirKey = token;
				return dirKey;
			} else {
				dirKey = "default";
			}
		}
		return dirKey;
	}


	/**
	 * <pre>
	 * 설명 : 서비스별 업로드 디렉토리
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
	public String getUploadDir(String serviceName){
		String dirKey = getSearchKey("dir.upload",serviceName);
		return prop.getProperty("dir.upload." + dirKey);
	}

	/**
	 * <pre>
	 * 설명 : 서비스별 파일사이즈
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
	public int getFileSize(String serviceName){
		String dirKey = getSearchKey("file.size",serviceName);
		return getInt("file.size." + dirKey);
	}

	/**
	 * <pre>
	 * 설명 : 서비스별 파일가능타입
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
	public String getFileType(String serviceName){
		String dirKey = getSearchKey("file.type",serviceName);
		return prop.getProperty("file.type." + dirKey);
	}

}
