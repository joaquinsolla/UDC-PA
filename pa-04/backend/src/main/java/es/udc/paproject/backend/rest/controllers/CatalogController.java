package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.CatalogService;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.CategoryDto;
import es.udc.paproject.backend.rest.dtos.ProductDto;
import es.udc.paproject.backend.rest.dtos.ProductSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static es.udc.paproject.backend.rest.dtos.CategoryConversor.toCategoryDtos;
import static es.udc.paproject.backend.rest.dtos.ProductConversor.toProductDto;
import static es.udc.paproject.backend.rest.dtos.ProductConversor.toProductSummaryDtos;


@RestController
@RequestMapping("/catalog")


public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) throws InstanceNotFoundException{
        return toProductDto(catalogService.findProductById(id));
    }

    @GetMapping("/categories")
    public List<CategoryDto> findAllCategories(){
        return toCategoryDtos(catalogService.findAllCategories());
    }

    @GetMapping("/products")
    public BlockDto<ProductSummaryDto> findProductsByKeywords(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keywords,
            @RequestParam(defaultValue = "0") int page){

        Block<Product> productos;

        productos = catalogService.findProducts(categoryId, keywords, page, 2);

        return new BlockDto<>(toProductSummaryDtos(productos.getItems()), productos.getExistMoreItems());
    }

}
