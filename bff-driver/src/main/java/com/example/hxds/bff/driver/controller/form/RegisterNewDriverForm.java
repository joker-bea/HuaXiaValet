package com.example.hxds.bff.driver.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author LXD
 * @Date 2023/7/2 21:09
 * @Version 1.0
 */
@Data
@Schema(description = "新司机注册表单")
public class RegisterNewDriverForm {

  @NotBlank(message = "code不能为空")
  @Schema(description = "微信小程序临时授权")
  private String code;

  @NotBlank(message = "nickname不能为空")
  @Schema(description = "用户昵称")
  private String nickname;

  @NotBlank(message = "photo不能为空")
  @Schema(description = "用户头像")
  private String photo;

}

