package com.example.hxds.dr.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.hxds.common.exception.HxdsException;
import com.example.hxds.common.util.MicroAppUtil;
import com.example.hxds.dr.db.dao.DriverDao;
import com.example.hxds.dr.db.dao.DriverSettingsDao;
import com.example.hxds.dr.db.dao.WalletDao;
import com.example.hxds.dr.db.pojo.DriverSettingsEntity;
import com.example.hxds.dr.db.pojo.WalletEntity;
import com.example.hxds.dr.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author LXD
 * @Date 2023/7/1 22:22
 * @Version 1.0
 */
@Service
@Slf4j
public class DriverServiceImpl implements DriverService {

  @Resource
  private MicroAppUtil microAppUtil;
  @Resource
  private DriverDao driverDao;

  @Resource
  private DriverSettingsDao driverSettingsDao;

  @Resource
  private WalletDao walletDao;
  /**
   * 注册新司机
   *
   * @param param 参数
   * @return {@link String}
   */
  @Override
  @Transactional
  @LcnTransaction
  public String registerNewDriver(Map param) {
    String code = MapUtil.getStr(param, "code");
    String openId = microAppUtil.getOpenId(code);

    HashMap temParam = new HashMap<>() {{
      put("opedId", openId);
    }};
    // 根据openId判断司机是否已经注册过
    if (driverDao.hasDriver(temParam) != 0){
      throw new HxdsException("该微信无法注册!");
    }

    param.put("openId",openId);
    // 插入司机的注册信息
    driverDao.registerNewDriver(param);
    //查询司机主键值
    String driverId = driverDao.searchDriverId(openId);


    //添加司机设置记录
    DriverSettingsEntity driverSettingsEntity = new DriverSettingsEntity();
    driverSettingsEntity.setDriverId(Long.parseLong(driverId));
    JSONObject json = new JSONObject();
    json.set("orientation", "");
    json.set("listenService", true);
    json.set("orderDistance", 0);
    json.set("rangeDistance", 5);
    json.set("autoAccept", false);
    driverSettingsEntity.setSettings(json.toString());
    driverSettingsDao.insertDriverSettings(driverSettingsEntity);

    // 添加司机钱包记录
    WalletEntity walletEntity = new WalletEntity();
    walletEntity.setDriverId(Long.parseLong(driverId));
    walletEntity.setBalance(new BigDecimal(0));
    // 支付密码设置为空，当用户支付的时候，系统会自动提示用户设置支付密码
    walletEntity.setPassword(null);
    walletDao.insertWallet(walletEntity);

    return driverId;
  }
}
