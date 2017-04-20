package xvzh.sensor.dao;

import java.util.Date;
import java.util.List;

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
	
	public static List<Human> select(Date begin, Date end) {
		SqlSession session = SessionUtil.getSqlSession();
		HumanMapper humanMapper = session.getMapper(HumanMapper.class);
		List<Human> data = humanMapper.getHumanByTime(begin, end);
		session.close();
		return data;
	}
}
