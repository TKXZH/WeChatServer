package xvzh.sensor.dao;

import org.apache.ibatis.session.SqlSession;

import xvzh.sensor.entity.Humidity;
import xvzh.sensor.mapper.HumidityMapper;
import xvzh.sensor.mapper.SessionUtil;

public class HumidityDao {
	public static void insert(float humidity) {
		SqlSession session = SessionUtil.getSqlSession();
		HumidityMapper humidityMapper = session.getMapper(HumidityMapper.class);
		humidityMapper.insert(new Humidity(humidity));
		session.close();
	}
}
