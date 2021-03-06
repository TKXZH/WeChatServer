CREATE DATABASE　sensor;

USE sensor;

CREATE TABLE temperature
(
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '温度数据id',
    `data` FLOAT NOT NULL COMMENT '温度数据',
    `time` DATE NOT NULL COMMENT '时刻',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '温度数据表';

CREATE TABLE humidity
(
   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '湿度数据id',
    `data` FLOAT NOT NULL COMMENT '湿度数据',
    `time` DATE NOT NULL COMMENT '时刻',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '湿度数据表';

CREATE TABLE human
(
   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '人体感应数据id',
    `data` BOOLEAN NOT NULL COMMENT '有无人状态',
    `time` DATE NOT NULL COMMENT '时刻',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '人体感应数据表';
