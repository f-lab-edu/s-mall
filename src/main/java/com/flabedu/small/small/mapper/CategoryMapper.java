package com.flabedu.small.small.mapper;

import com.flabedu.small.small.web.dto.response.CategoryResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    List<CategoryResponseDTO> findCategory();
}
