package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Slice;

public interface CustomizedProductDao {

    Slice<Product> find(Long categoryId, int page, int size);

    Slice<Product> find(Long categoryId, String text, int page, int size);

}
