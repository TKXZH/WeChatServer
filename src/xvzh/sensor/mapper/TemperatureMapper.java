package xvzh.sensor.mapper;

import org.apache.ibatis.annotations.Select;

import xvzh.sensor.entity.Temperature;

public interface TemperatureMapper {
	@Select("insert into temperature(data) VALUES (#{temperature})")
	public void insert(Temperature temperature);
}
