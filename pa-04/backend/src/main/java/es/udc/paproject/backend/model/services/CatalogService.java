package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

import java.util.List;

public interface CatalogService {

    /**BUSCAR UN PRODUCTO*/
    Block<Product> findProducts(Long categoryId, String keywords, int page, int size);

    /**VER INFORMACION DE UN PRODUCTO*/
    Product findProductById(Long productId) throws InstanceNotFoundException;

    /**RECUPERAR TODAS LAS CATEGORIAS*/
    List<Category> findAllCategories();

}