package net.acq;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ParseProps {

	private static Properties properties;

	static {
		final File file = new File("jobs.schedule.properties");
		ParseProps.properties = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			ParseProps.properties.load(in);
		} catch (final Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static String getProperty(final String key) {
		return ParseProps.properties.getProperty(key);
	}

}
