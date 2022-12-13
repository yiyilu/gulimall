package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    /*@Autowired
    CategoryDao categoryDao;*/

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //这边需要拿到所有分类数据，第一种方法可以在上面注入CategoryDao
        //第二种发法是直接用basemapper
        //因为CategoryServiceImpl已经继承了ServiceImpl，而ServiceImpl里面又有CategoryDao的泛型
        //1. 查出所有分类
        List<CategoryEntity> entities = this.baseMapper.selectList(null);

        //2.组装成父子的树形结构
        //2.1找到所有的一级分类
        //一步步剖析
        //首先，entities里面获得的是所有的实体，通过filter，获得所有的一级分类
        //获得一级分类之后，通过map，把一级菜单（本来是不包括子菜单的）映射到一级菜单（已经set好子菜单了）
        //映射好之后，通过sorted()进行排序，把菜单号 升序排列
        //最后再通过collect方法转化成list集合
        List<CategoryEntity> level1Menu = entities.stream().filter((categoryEntity) ->
                        categoryEntity.getParentCid() == 0
                ).map((menu) -> {
                    menu.setChildren(getChildren(menu, entities));
                    return menu;
                }).sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());


        return level1Menu;
    }

    //这个方法是用来递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(categoryEntity -> {
            //1. 找到子菜单
            categoryEntity.setChildren(getChildren(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return children;
    }

}