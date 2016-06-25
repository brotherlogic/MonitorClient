package com.github.brotherlogic.monitorclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MonitorClient {

	String version;

	protected Properties getProperties(InputStream stream) {
		try {
			Properties props = new Properties();
			props.load(stream);
			return props;
		} catch (IOException e) {
			return null;
		}
	}

	public String getVersion() {
		Properties props = getProperties(MonitorClient.class.getResourceAsStream("properties.txt"));
		return props.getProperty("version");
	}
}
