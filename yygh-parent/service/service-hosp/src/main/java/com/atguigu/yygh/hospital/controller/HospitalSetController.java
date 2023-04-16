package com.atguigu.yygh.hospital.controller;

import com.atguigu.yygh.common.execption.YyghException;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.utils.MD5;
import com.atguigu.yygh.hospital.service.Impl.HospitalSetServiceImpl;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Api("医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {

    @Resource
    private HospitalSetServiceImpl hospitalSetService;

    /**
     * 查询医院设置表里的所有信息
     * @return
     */
    @GetMapping("/findAll")
    public Result findAllHospSet(){
        try {
            //模拟异常
//            int i = 10 / 0;
        } catch (Exception e) {
            throw new YyghException("失败",201);
        }
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    /**
     * 删除医院设置
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean result = hospitalSetService.removeById(id);
        if (result){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * 条件查询带分页
     * @param current
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    @PostMapping("/findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable Long current,
                                  @PathVariable Long limit,
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

    /**
     * 添加医院设置
     * @param hospitalSet
     * @return
     */
    @PostMapping("/saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //设置状态 1 使用 0 不能使用
        hospitalSet.setStatus(1);
        //签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        //调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if (save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * 根据id获取医院设置
     * @param id
     * @return
     */
    @GetMapping("/getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    //6.修改医院设置
    @PostMapping("/updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean result = hospitalSetService.updateById(hospitalSet);
        if (result){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //7.批量删除医院设置
    @DeleteMapping("/batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList){
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

    //8.医院设置锁定和解锁
    @PutMapping("/lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status){
        //根据ID查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        hospitalSetService.updateById(hospitalSet);

        return Result.ok();
    }

    //9.发送签名秘钥
    @PutMapping("/sendHospSignKey/{id}/{status}")
    public Result sendHospSignKey(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }

}
