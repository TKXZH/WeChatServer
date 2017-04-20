package xvzh.sensor.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import xvzh.sensor.entity.Temperature;

public interface TemperatureMapper {
	@Select("insert into temperature(data) VALUES (#{temperature})")
	public void insert(Temperature temperature);
	
	@Select("select * from temperature where time <= #{end} and time >= #{begin}")
	@Results(
		    {
			    @Result(column = "data", property = "temperature")
		    })
	public List<Temperature> getTemperatureByTime(@Param("begin")Date begin, @Param("end")Date end);
}

