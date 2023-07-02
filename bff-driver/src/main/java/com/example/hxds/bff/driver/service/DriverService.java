package com.example.hxds.bff.driver.service;

import com.example.hxds.bff.driver.controller.form.RegisterNewDriverForm;

/**
 * @Author LXD
 * @Date 2023/7/2 21:14
 * @Version 1.0
 */
public interface DriverService {

  public long registerNewDriver(RegisterNewDriverForm form);
}
