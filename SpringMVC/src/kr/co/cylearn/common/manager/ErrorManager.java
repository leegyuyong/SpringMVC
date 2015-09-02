package kr.co.cylearn.common.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * ConfigManager Singleton pattern
 * @author MintOcean
 *
 */
public class ErrorManager {

	private final static Logger log = Logger.getLogger(ErrorManager.class);

	// instance
	private static ErrorManager instance = null;

	// properties info
	private final static String PROPERTY_FILE = "error.properties";

	// properties
	Properties prop = null;

	public static ErrorManager getInstance()
	{
		if(instance == null)
		{
			synchronized (ErrorManager.class) {
				if(instance == null)
					instance = new ErrorManager();
			}
		}

		return instance;
	}


	private ErrorManager()
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
			return (String)prop.get(key);

		return "";
	}

}

