package com.example.hxds.dr.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.hxds.common.util.R;
import com.example.hxds.dr.controller.form.RegisterNewDriverForm;
import com.example.hxds.dr.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Author LXD
 * @Date 2023/7/1 22:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/driver")
@Tag(name = "DriverController", description = "司机模块Web接口")
public class DriverController {

  @Resource
  private DriverService driverService;

  @PostMapping("/registerNewDriver")
  @Operation(summary = "新司机注册")
  public R registerNewDriver(@RequestBody @Valid RegisterNewDriverForm form){
    Map param = BeanUtil.beanToMap(form);
    String userId = driverService.registerNewDriver(param);
    return R.ok().put("userId",userId);
  }

}
