package com.example.hxds.bff.driver.service.impl;

import cn.hutool.core.convert.Convert;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.hxds.bff.driver.feign.DrServiceApi;
import com.example.hxds.bff.driver.controller.form.RegisterNewDriverForm;
import com.example.hxds.bff.driver.service.DriverService;
import com.example.hxds.common.util.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author LXD
 * @Date 2023/7/2 21:15
 * @Version 1.0
 */
@Service
public class DriverServiceImpl implements DriverService {

  @Resource
  private DrServiceApi drServiceApi;
  @Override
  @Transactional
  @LcnTransaction
  public long registerNewDriver(RegisterNewDriverForm form) {
    R r = drServiceApi.registerNewDriver(form);
    return Convert.toLong(r.get("userId"));
  }
}
