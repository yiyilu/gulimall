package com.atguigu.gulimall.product.service;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.SkuImagesEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * sku图片
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-12-04 23:00:47
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

