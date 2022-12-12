package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//通过这个接口远程调用别的服务，是一个声明式的远程调用
//这个注解表明这里是个客户端,里面要写需要调用的服务的服务名字
@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    //把需要调用的远程服务的方法签名拿过来,但是接口路径需要写完整哦
    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();
}
