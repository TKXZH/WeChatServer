package xvzh.sensor.mapper;

import org.apache.ibatis.annotations.Select;

import xvzh.sensor.entity.Humidity;

public interface HumidityMapper {
	@Select("insert into humidity(data) VALUES (#{humidity})")
	public void insert(Humidity humidity);
}
