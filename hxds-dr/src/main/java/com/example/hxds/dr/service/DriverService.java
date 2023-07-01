package com.example.hxds.dr.service;

import java.util.Map;

/**
 * @Author LXD
 * @Date 2023/7/1 22:20
 * @Version 1.0
 */
public interface DriverService {
  /**
   * 注册新司机
   *
   * @param param 参数
   * @return {@link String}
   */
  public String registerNewDriver(Map param);
}
