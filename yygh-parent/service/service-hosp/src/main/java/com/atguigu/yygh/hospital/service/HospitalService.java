package com.atguigu.yygh.hospital.service;

import com.atguigu.yygh.hospital.service.Impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface HospitalService{

    //调用service的方法
    void save(Map<String, Object> paramMap);

    //调用service方法实现根据医院编号查询
    Hospital getByHoscode(String hoscode);


    Page selectHospPage(Integer page, Integer limit, HospitalSetQueryVo hospitalSetQueryVo);

    //更新医院上线状态
    void updateStatus(String id, Integer status);

    //医院详情信息
    Map<String, Object> getHospById(String id);
}
