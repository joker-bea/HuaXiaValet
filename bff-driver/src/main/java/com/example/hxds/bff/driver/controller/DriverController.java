package com.example.hxds.bff.driver.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.example.hxds.bff.driver.controller.form.RegisterNewDriverForm;
import com.example.hxds.bff.driver.service.DriverService;
import com.example.hxds.common.util.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author LXD
 * @Date 2023/7/2 21:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/driver")
@Tag(name="DriverController", description = "司机模块Web接口")
public class DriverController {

  @Resource
  private DriverService driverService;

  public R registerNewDriver(@RequestBody @Valid RegisterNewDriverForm form){
    long driverId = driverService.registerNewDriver(form);
    StpUtil.login(driverId);
    String token = StpUtil.getTokenInfo().getTokenValue();
    return R.ok().put("token",token);
  }

}
