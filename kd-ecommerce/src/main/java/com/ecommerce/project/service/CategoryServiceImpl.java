package com.ecommerce.project.service;

import com.ecommerce.project.api.response.model.CategoryResponse;
import com.ecommerce.project.dtos.CategoryDTO;
import com.ecommerce.project.exceptions.custom.APIException;
import com.ecommerce.project.exceptions.custom.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortDetailsObj = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortDetailsObj);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryPage.toList();
        if(categories.isEmpty()){
            throw new APIException("No categories present till now");
        }
        // DTO logic
        List<CategoryDTO> categoryDTOList = categories.stream().map(categoryObj -> {
            return modelMapper.map(categoryObj, CategoryDTO.class);
        }).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOList);
        categoryResponse.setPageNumber(pageable.getPageNumber());
        categoryResponse.setPageSize(pageable.getPageSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setIsLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category foundCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(foundCategory != null){
            throw new APIException(String.format("%s Category already present in System", categoryDTO.getCategoryName()));
        }else{
            Category categoryDAO = modelMapper.map(categoryDTO, Category.class);
            categoryDAO = categoryRepository.save(categoryDAO);
            return  modelMapper.map(categoryDAO, CategoryDTO.class);
        }
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDto) {
        Optional<Category> optional_CatToBeUpdate = categoryRepository.findById(categoryId);
        Category categoryToBeUpdate =  optional_CatToBeUpdate.orElseThrow(() -> new ResourceNotFoundException(categoryId,"categoryId","Category"));
        categoryToBeUpdate.setCategoryName(categoryDto.getCategoryName());
        return modelMapper.map(categoryRepository.save(categoryToBeUpdate), CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Optional<Category> op_CatToDelete = categoryRepository.findById(categoryId);
        Category categoryToDelete = op_CatToDelete.orElseThrow(() -> new ResourceNotFoundException(categoryId , "categoryId", "Category"));
        categoryRepository.delete(categoryToDelete);
        return modelMapper.map(categoryToDelete, CategoryDTO.class);
    }
}
