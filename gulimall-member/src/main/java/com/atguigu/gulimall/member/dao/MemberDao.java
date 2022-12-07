package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author luyiyi
 * @email eeloo1118@gmail.com
 * @date 2022-12-08 00:13:31
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
