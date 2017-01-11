package com.glufine.dao;

import org.springframework.stereotype.Component;

import com.glufine.entity.Pressure;
@Component
public interface PressureMapper {
    int deleteByPrimaryKey(Integer prssureId);

    int insert(Pressure record);

    int insertSelective(Pressure record);

    Pressure selectByPrimaryKey(Integer prssureId);

    int updateByPrimaryKeySelective(Pressure record);

    int updateByPrimaryKey(Pressure record);
}