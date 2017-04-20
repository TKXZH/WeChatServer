package xvzh.sensor.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import xvzh.sensor.entity.Humidity;

public interface HumidityMapper {
	@Select("insert into humidity(data) VALUES (#{humidity})")
	public void insert(Humidity humidity);
	
	@Select("select * from humidity where time <= #{end} and time >= #{begin}")
	@Results(
		    {
			    @Result(column = "data", property = "humidity")
		    })
	public List<Humidity> getHumidityByTime(@Param("begin")Date begin, @Param("end")Date end);
}
