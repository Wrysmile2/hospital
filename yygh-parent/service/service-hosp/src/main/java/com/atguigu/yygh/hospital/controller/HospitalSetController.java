package com.atguigu.yygh.hospital.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hospital.service.Impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Resource
    private HospitalSetServiceImpl hospitalSetService;

    //1.查询医院设置表里的所有信息
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //2.删除医院设置
    @DeleteMapping("/{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean result = hospitalSetService.removeById(id);
        if (result){
            return Result.ok();
        }else {
            return Result.fail();
        }

    }

}
