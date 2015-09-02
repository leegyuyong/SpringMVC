package kr.co.cylearn.common.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.FileAppender;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class DatedFileAppender extends FileAppender {
	public static final String MONTHLY = "MONTHLY";
	public static final String WEEKLY = "WEEKLY";
	public static final String DAILY = "DAILY";
	public static final String HOURLY = "HOURLY";
	public static final String CUSTOM = "CUSTOM";
	private String m_directory = "logs";

	private String m_prefix = "tomcat.";

	private String m_suffix = ".log";
	private String m_scheduleType;
	private int m_cycleMinutes = 60;

	private File m_path = null;

	private Calendar m_calendar = null;

	private long m_nextCycle = 0L;

	public DatedFileAppender() {
	}

	public DatedFileAppender(String paramString1, String paramString2, String paramString3) {
		this.m_directory = paramString1;
		this.m_prefix = paramString2;
		this.m_suffix = paramString3;
		activateOptions();
	}

	public String getDirectory() {
		return this.m_directory;
	}

	public void setDirectory(String paramString) {
		this.m_directory = paramString;
	}

	public String getPrefix() {
		return this.m_prefix;
	}

	public void setPrefix(String paramString) {
		this.m_prefix = paramString;
	}

	public String getSuffix() {
		return this.m_suffix;
	}

	public void setSuffix(String paramString) {
		this.m_suffix = paramString;
	}

	public String getScheduleType() {
		return this.m_scheduleType;
	}

	public void setScheduleType(String paramString) {
		this.m_scheduleType = paramString;
	}

	public int getCycleMinutes() {
		return this.m_cycleMinutes;
	}

	public void setCycleMinutes(int paramInt) {
		if (paramInt > 0) {
			this.m_cycleMinutes = paramInt;

			this.m_scheduleType = "CUSTOM";
		} else {
			throw new IllegalArgumentException("Invalid cycleMinutes value: " + paramInt + ". Value must be positive");
		}
	}

	public void activateOptions() {
		if (this.m_prefix == null) {
			this.m_prefix = "";
		}
		if (this.m_suffix == null) {
			this.m_suffix = "";
		}
		if ((this.m_directory == null) || (this.m_directory.length() == 0)) {
			this.m_directory = ".";
		}
		this.m_path = new File(this.m_directory);
		if (!(this.m_path.isAbsolute())) {
			String str = System.getProperty("catalina.base");
			if (str != null) {
				this.m_path = new File(str, this.m_directory);
			}
		}
		this.m_path.mkdirs();
		if (this.m_path.canWrite()) this.m_calendar = Calendar.getInstance();
	}

	public void append(LoggingEvent paramLoggingEvent) {
		if (this.layout == null) {
			this.errorHandler.error("No layout set for the appender named [" + this.name + "].");
			return;
		}
		if (this.m_calendar == null) {
			this.errorHandler.error("Improper initialization for the appender named [" + this.name + "].");
			return;
		}
		long l = System.currentTimeMillis();
		if (l >= this.m_nextCycle) {
			this.m_calendar.setTime(new Date(l));
			String str = datestamp(this.m_calendar, this.m_scheduleType);
			nextCycle(this.m_calendar, this.m_scheduleType, this.m_cycleMinutes);

			this.m_nextCycle = this.m_calendar.getTime().getTime();
			File localFile = new File(this.m_path, this.m_prefix + str + this.m_suffix);
			this.fileName = localFile.getAbsolutePath();
			super.activateOptions();
		}
		if (this.qw == null) {
			this.errorHandler.error("No output stream or file set for the appender named [" + this.name + "].");

			return;
		}
		subAppend(paramLoggingEvent);
	}

	public static String datestamp(Calendar paramCalendar, String paramString) {
		int i = paramCalendar.get(1);
		int j = paramCalendar.get(2) + 1;
		int k = paramCalendar.get(5);
		int l = paramCalendar.get(11);
		int i1 = paramCalendar.get(12) + 1;

		StringBuffer localStringBuffer = new StringBuffer();
		if (i < 1000) {
			localStringBuffer.append('0');
			if (i < 100) {
				localStringBuffer.append('0');
				if (i < 10) {
					localStringBuffer.append('0');
				}
			}
		}
		localStringBuffer.append(Integer.toString(i));
		localStringBuffer.append('-');
		if (j < 10) {
			localStringBuffer.append('0');
		}
		localStringBuffer.append(Integer.toString(j));
		localStringBuffer.append('-');
		if (k < 10) {
			localStringBuffer.append('0');
		}
		localStringBuffer.append(Integer.toString(k));

		if (("HOURLY".equals(paramString)) || ("CUSTOM".equals(paramString))) {
			localStringBuffer.append('.');
			if (l < 10) {
				localStringBuffer.append('0');
			}
			localStringBuffer.append(Integer.toString(l));

			localStringBuffer.append('-');
			if (i1 < 10) {
				localStringBuffer.append('0');
			}
			localStringBuffer.append(Integer.toString(i1));
		}

		return localStringBuffer.toString();
	}

	private static void nextCycle(Calendar paramCalendar, String paramString, int paramInt) {
		int i = paramCalendar.get(1);
		int j = paramCalendar.get(2);
		int k = paramCalendar.get(5);
		int l = paramCalendar.get(11);
		int i1 = paramCalendar.get(12);

		if ("MONTHLY".equals(paramString)) {
			++j;
			k = 0;
			l = 0;
			i1 = 0;
		} else if ("WEEKLY".equals(paramString)) {
			k += 7;
			l = 0;
			i1 = 0;
		} else if ("DAILY".equals(paramString)) {
			++k;
			l = 0;
			i1 = 0;
		} else if ("HOURLY".equals(paramString)) {
			++l;
			i1 = 0;
		} else if ("CUSTOM".equals(paramString)) {
			i1 += paramInt;
		}

		paramCalendar.clear();
		paramCalendar.set(i, j, k, l, i1, 0);
	}
}