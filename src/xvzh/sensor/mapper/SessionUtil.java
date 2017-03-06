package xvzh.sensor.mapper;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionUtil {
	private static SqlSessionFactory sessionFactory;
	
	private static SqlSessionFactory getSessionFactory() throws IOException {
		if(sessionFactory == null) {
			String resource = "mybatis.cfg.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		return sessionFactory;
	}
	
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		try {
			session = getSessionFactory().openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}
