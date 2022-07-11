package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConversor {

    private  CategoryConversor(){}

    public final static CategoryDto toCategoryDto(Category category){
        return new CategoryDto(category.getId(), category.getName());
    }

    public final static List<CategoryDto> toCategoryDtos(List<Category> categories){
        return categories.stream().map(c -> toCategoryDto(c)).collect(Collectors.toList());
    }

}
