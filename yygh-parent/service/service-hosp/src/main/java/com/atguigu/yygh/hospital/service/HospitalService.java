package com.atguigu.yygh.hospital.service;

import com.atguigu.yygh.model.hosp.Hospital;

import java.util.Map;

public interface HospitalService {

    //调用service的方法
    void save(Map<String, Object> paramMap);

    //调用service方法实现根据医院编号查询
    Hospital getByHoscode(String hoscode);
}
