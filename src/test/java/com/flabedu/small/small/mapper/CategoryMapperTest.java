package com.flabedu.small.small.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryMapperTest {

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    @DisplayName("카테고리 리스트를 조회한다. 정상적으로 성공한다.")
    public void getCategoryTest(){
        categoryMapper.findCategory();
    }
}
