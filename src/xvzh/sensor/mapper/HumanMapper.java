package xvzh.sensor.mapper;

import org.apache.ibatis.annotations.Select;

import xvzh.sensor.entity.Human;

public interface HumanMapper {
	@Select("insert into human(data) VALUES (#{status})")
	public void insert(Human human);
}
