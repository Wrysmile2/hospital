package com.atguigu.yygh.hospital.service;

import com.atguigu.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.IService;

public interface HospitalSetService extends IService<HospitalSet> {

    //2 根据传递过来的医院编码，查询数据库，查询签名
    String getSignKey(String hoscode);
}
