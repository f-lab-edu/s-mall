package com.flabedu.small.small.service;

import com.flabedu.small.small.mapper.CategoryMapper;
import com.flabedu.small.small.web.dto.response.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findCategory(){
        return categoryMapper.findCategory()
                .stream().map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
    }
}
