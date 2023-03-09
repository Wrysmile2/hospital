package com.atguigu.yygh.hospital.service.Impl;

import com.atguigu.yygh.hospital.mapper.HospitalSetMapper;
import com.atguigu.yygh.hospital.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService{

    @Resource
    private HospitalSetMapper hospitalSetMapper;

}
