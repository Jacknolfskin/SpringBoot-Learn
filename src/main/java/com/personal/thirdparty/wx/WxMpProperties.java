package com.personal.thirdparty.wx;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wxpay pay properties
 *
 * @author Jacknolfskin
 */
@ConfigurationProperties(prefix = "wechat.mp")
public class WxMpProperties {
  /**
   * 设置微信公众号的appid
   */
  private String appId;

  /**
   * 微信公众号APP_SECRET
   */
  private String secret;

  /**
   * 微信公众号Token
   */
  private String token;

  /**
   * 微信公众号aesKey
   */
  private String aesKey;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAesKey() {
    return aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }
}
