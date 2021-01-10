package pers.mofan.reader.service;

import pers.mofan.reader.entity.Category;

import java.util.List;

/**
 * @author mofan
 * @date 2021/1/10 17:31
 */
public interface CategoryService {
    /**
     * 获取类别集合
     * @return List<Category>
     */
    List<Category> selectAll();
}
