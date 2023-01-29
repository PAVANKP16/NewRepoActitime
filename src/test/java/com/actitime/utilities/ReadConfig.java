package com.actitime.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ReadConfig {
		Properties pro;
		public ReadConfig() {
			
			pro = new Properties();
			File file = new File("./Configuration/config.properties");
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
					pro.load(fis);
			} catch (IOException e) {
					e.printStackTrace();
			}
		}

		public String getURL()
	{
			String url = pro.getProperty("URL");
			return url;
	}
		
		public String getUsername() 
		{
			String username = pro.getProperty("username");
					return username;
		}
		
		public String getPassword()
		{
			String password = pro.getProperty("password");
			return password;
		}
		
		public String getBrowser()
		{
			String browser = pro.getProperty("browser");
			return browser;
		}
		
		public long getITO() {
			String ITO = pro.getProperty("ITO");
			long lITO = Long.parseLong(ITO);
			return lITO;
		}
		
		public long getETO()
		{
			String ETO = pro.getProperty("ETO");
			long lETO = Long.parseLong(ETO);
			return lETO;
		}
		
		public String getTimeStamp() 
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(" DD_MM_YYYY EEEE hh_mm_s_aa");
			Date date = new Date();
			String currentDateTime = dateFormat.format(date);
			return currentDateTime;
		
}
}



















