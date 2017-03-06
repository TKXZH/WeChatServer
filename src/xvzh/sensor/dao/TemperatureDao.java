package xvzh.sensor.dao;

import org.apache.ibatis.session.SqlSession;

import xvzh.sensor.entity.Temperature;
import xvzh.sensor.mapper.SessionUtil;
import xvzh.sensor.mapper.TemperatureMapper;

public class TemperatureDao {
	public static void insert(float temperature) {
		SqlSession session = SessionUtil.getSqlSession();
		TemperatureMapper temperatureMapper = session.getMapper(TemperatureMapper.class);
		temperatureMapper.insert(new Temperature(temperature));
		session.close();
	}
}
