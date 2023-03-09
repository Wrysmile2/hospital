package com.atguigu.yygh.hospital.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hospital.service.Impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Resource
    private HospitalSetServiceImpl hospitalSetService;

    //1.查询医院设置表里的所有信息
    @GetMapping("/findAll")
    public Result findAllHospSet(){
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

    //3.条件查询带分页
    @PostMapping("/findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalQueryVo hospitalQueryVo){
        //创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current,limit);
        //构造条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        //判断值是否为空
        String hosname = hospitalQueryVo.getHosname(); //医院名称
        String hoscode = hospitalQueryVo.getHoscode(); //医院编号
        if (StringUtils.isNotBlank(hoscode)){
            wrapper.like("hosname",hospitalQueryVo.getHosname());
        }
        if (StringUtils.isNotBlank(hoscode)){
            wrapper.select("hoscode",hospitalQueryVo.getHoscode());
        }
        //调用方法实现分页的查询
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, wrapper);

        return Result.ok(hospitalSetPage);
    }

    //4 添加医院设置
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //设置状态 1 使用 0 不能使用
        hospitalSet.setStatus(1);
        //签名秘钥
        Random random = new Random();

        return null;
    }

}
