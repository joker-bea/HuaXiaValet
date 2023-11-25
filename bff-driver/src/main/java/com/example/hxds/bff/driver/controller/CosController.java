package com.example.hxds.bff.driver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.example.hxds.bff.driver.controller.form.DeleteCosFileForm;
import com.example.hxds.common.exception.HxdsException;
import com.example.hxds.common.util.CosUtil;
import com.example.hxds.common.util.R;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/cos")
@Slf4j
@Tag(name = "CosController",description = "对象web存储接口")
public class CosController {
    @Resource
    private CosUtil cosUtil;

    @PostMapping("/uploadCosPrivateFile")
    @SaCheckLogin
    @Operation(summary = "文件上传")
    public R uploadCosPrivateFile(@Param("file") MultipartFile file, @Param("module") String module){
        // 判断文件是否为空
        if (file.isEmpty()){
            throw new HxdsException("上传文件不能为空！");
        }
        try{
            String path = null;
            // 根据module判断,我将会把文件上传到什么文件路径中
            if ("driverAuth".equals(module)){
                path = "driver/auth/";
            }else {
                throw new HxdsException("module出现错误！");
            }
            // map中包含文件名，和文件路径
            HashMap hashMap = cosUtil.uploadPrivateFile(file, path);
            return R.ok(hashMap);
        }catch (Exception e){
            log.error("文件上传腾讯云出错！",e);
            throw new HxdsException("文件上传腾讯云出错！");
        }
    }

    @PostMapping("/deleteCosPrivateFile")
    @SaCheckLogin
    @Operation(summary = "删除COS文件")
    public R deleteCosPrivateFile(@Valid @RequestBody DeleteCosFileForm cosFileForm){
        cosUtil.deletePrivateFile(cosFileForm.getPathes());
        return R.ok();
    }
}
