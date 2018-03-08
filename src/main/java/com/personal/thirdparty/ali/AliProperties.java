package com.personal.thirdparty.ali;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wxpay pay properties
 *
 * @author Jacknolfskin
 */
@ConfigurationProperties(prefix = "ali")
public class AliProperties {
  /**
   * 设置支付宝的appid
   */
  private String appId;

  /**
   * 支付宝netWork
   */
  private String netWork;

  /**
   * 支付宝privateKey
   */
  private String privateKey;

  /**
   * 支付宝publicKey
   */
  private String publicKey;

  /**
   * 回调路径
   */
  private String notifyUrl;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getNetWork() {
    return netWork;
  }

  public void setNetWork(String netWork) {
    this.netWork = netWork;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }
}
