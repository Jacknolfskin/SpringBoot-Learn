package com.personal.controller;

import com.personal.entity.dto.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * cache 操作
 * 
 * @author 肖文杰 https://github.com/xwjie/
 *
 */
@RequestMapping("/cache")
@RestController
@CrossOrigin
public class CacheController {
	private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

	@Autowired
	CacheManager cacheManager;

	/**
	 * 得到所有的cache
	 * 
	 * @return
	 */
	@GetMapping("/names")
	public ResultBean<Collection<String>> index() {
		return new ResultBean<>(cacheManager.getCacheNames());
	}

	/**
	 * 清空某个cache
	 * 
	 * @param name
	 * @return
	 */
	@PostMapping("/clear")
	public ResultBean<Boolean> clear(@RequestParam String name) {
		logger.info("clear cache : {}", name);
		cacheManager.getCache(name).clear();
		return new ResultBean<>(true);
	}

	/**
	 * 清空所有cache
	 * 
	 * @return
	 */
	@PostMapping("/clearAll")
	public ResultBean<Boolean> clearAll() {
		logger.info("clear all cache ...");

		Collection<String> cacheNames = cacheManager.getCacheNames();

		for (String name : cacheNames) {
			logger.info("clear cache : {}", name);
			cacheManager.getCache(name).clear();
		}

		return new ResultBean<>(true);
	}
}
