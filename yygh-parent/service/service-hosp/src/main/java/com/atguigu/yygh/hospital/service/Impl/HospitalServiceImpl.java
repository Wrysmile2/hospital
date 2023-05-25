package com.atguigu.yygh.hospital.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hospital.repository.HospitalRepository;
import com.atguigu.yygh.hospital.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        //将参数的map集合装换成对象 Hospital
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);

        //判断是否存在相同数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExit = hospitalRepository.getHospitalByHoscode(hoscode);

        //如果存在，进行修改
        if(hospitalExit != null){
            hospital.setStatus(hospitalExit.getStatus());
            hospital.setCreateTime(hospitalExit.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else {
            //如果不存在，进行添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }

    }

    //调用service方法实现根据医院编号查询
    @Override
    public Hospital getByHoscode(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);

        return hospital;
    }

}
