package xvzh.wechat.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取系统配置文件
 * @author xvzh
 *
 */
public class SystemConf {
	public String getConfByName(String name) throws IOException {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("system_config.properties"));
		return properties.getProperty(name);
	}
}
