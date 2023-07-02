package com.example.hxds.bff.driver.feign;

import com.example.hxds.bff.driver.controller.form.RegisterNewDriverForm;
import com.example.hxds.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author LXD
 * @Date 2023/7/2 21:07
 * @Version 1.0
 */
@FeignClient(value = "hxds-dr")
public interface DrServiceApi {
  @PostMapping("/driver/registerNewDriver")
  public R registerNewDriver(RegisterNewDriverForm form);
}
