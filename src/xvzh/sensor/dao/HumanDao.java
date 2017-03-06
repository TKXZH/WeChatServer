package xvzh.sensor.dao;

import org.apache.ibatis.session.SqlSession;

import xvzh.sensor.entity.Human;
import xvzh.sensor.mapper.HumanMapper;
import xvzh.sensor.mapper.SessionUtil;

public class HumanDao {
	public static void insert(boolean status) {
		SqlSession session = SessionUtil.getSqlSession();
		HumanMapper humanMapper = session.getMapper(HumanMapper.class);
		humanMapper.insert(new Human(status));
		session.close();
	}
}
