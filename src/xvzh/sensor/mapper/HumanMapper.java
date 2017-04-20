package xvzh.sensor.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import xvzh.sensor.entity.Human;

public interface HumanMapper {
	@Select("insert into human(data) VALUES (#{status})")
	public void insert(Human human);
	
	@Select("select * from human where time <= #{end} and time >= #{begin}")
	@Results(
		    {
			    @Result(column = "data", property = "status")
		    })
	public List<Human> getHumanByTime(@Param("begin")Date begin, @Param("end")Date end);
}
