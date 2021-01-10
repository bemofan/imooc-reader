package pers.mofan.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.mofan.reader.entity.Category;
import pers.mofan.reader.mapper.CategoryMapper;
import pers.mofan.reader.service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mofan
 * @date 2021/1/10 17:32
 */
@Service("categoryService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectList(new QueryWrapper<>());
    }
}
