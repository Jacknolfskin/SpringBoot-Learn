package com.personal.thirdparty.wx;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号主配置
 * <p>
 * Created by Jacknolfskin on 2017/12/12.
 */
@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {

	@Autowired
	private WxMpProperties wxMpProperties;

	@Bean
	@ConditionalOnMissingBean
	public WxMpConfigStorage wxMpConfigStorage() {
		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
		configStorage.setAppId(this.wxMpProperties.getAppId());
		configStorage.setSecret(this.wxMpProperties.getSecret());
		configStorage.setToken(this.wxMpProperties.getToken());
		configStorage.setAesKey(this.wxMpProperties.getAesKey());
		return configStorage;
	}

	@Bean
	public WxMpService wxMpService() {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}
}
