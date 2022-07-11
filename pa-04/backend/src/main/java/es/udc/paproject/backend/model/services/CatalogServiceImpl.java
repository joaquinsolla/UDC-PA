package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.CategoryDao;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.ProductDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class CatalogServiceImpl implements CatalogService{

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Block<Product> findProducts(Long categoryId, String keywords, int page, int size) {

        Slice<Product> slice = productDao.find(categoryId, keywords, page, size);
        return new Block<>(slice.getContent(), slice.hasNext());

    }

    @Override
    public Product findProductById(Long productId) throws InstanceNotFoundException {
        Optional<Product> product = productDao.findById(productId);

        if(!product.isPresent()){
            throw new InstanceNotFoundException("pa-04.entities.product", productId);
        }
        return product.get();
    }

    @Override
    public List<Category> findAllCategories() {

        Iterable<Category> categorias = categoryDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<Category> listaCategorias = new ArrayList<>();

        categorias.forEach(c -> listaCategorias.add(c));

        return listaCategorias;

    }

}
