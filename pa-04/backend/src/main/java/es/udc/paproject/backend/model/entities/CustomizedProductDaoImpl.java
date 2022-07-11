package es.udc.paproject.backend.model.entities;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedProductDaoImpl implements CustomizedProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    private String[] getTokens(String keywords) {

        if (keywords == null || keywords.length() == 0) {
            return new String[0];
        } else {
            return keywords.split("\\s");
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Slice<Product> find(Long categoryId, int page, int size) {

        String queryString = "SELECT p FROM Product p";

        if (categoryId != null) {
            queryString += " WHERE p.category.id = :categoryId";
        }

        if (categoryId != null) {
            queryString += " AND p.expirationDate > CURRENT_TIMESTAMP";
        } else {
            queryString += " WHERE p.expirationDate > CURRENT_TIMESTAMP";
        }

        queryString += " ORDER BY p.name";

        Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        List<Product> products = query.getResultList();
        boolean hasNext = products.size() == (size+1);

        if (hasNext) {
            products.remove(products.size()-1);
        }

        return new SliceImpl<>(products, PageRequest.of(page, size), hasNext);

    }

    @SuppressWarnings("unchecked")
    @Override
    public Slice<Product> find(Long categoryId, String keywords, int page, int size) {

        String[] tokens = getTokens(keywords);
        String queryString = "SELECT p FROM Product p";

        if (categoryId != null || tokens.length > 0) {
            queryString += " WHERE ";
        }

        if (categoryId != null) {
            queryString += "p.category.id = :categoryId";
        }

        if (tokens.length != 0) {

            if (categoryId != null) {
                queryString += " AND ";
            }

            for (int i = 0; i<tokens.length-1; i++) {
                queryString += "LOWER(p.name) LIKE LOWER(:token" + i + ") AND ";
            }

            queryString += "LOWER(p.name) LIKE LOWER(:token" + (tokens.length-1) + ")";

        }

        if(tokens.length == 0 && categoryId == null) {
            queryString += " WHERE p.expirationDate > CURRENT_TIMESTAMP";
        }
        else{
            queryString += " AND p.expirationDate > CURRENT_TIMESTAMP";
        }

        queryString += " ORDER BY p.name";

        Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        if (tokens.length != 0) {
            for (int i = 0; i<tokens.length; i++) {
                query.setParameter("token" + i, '%' + tokens[i] + '%');
            }

        }

        List<Product> products = query.getResultList();
        boolean hasNext = products.size() == (size+1);

        if (hasNext) {
            products.remove(products.size()-1);
        }

        return new SliceImpl<>(products, PageRequest.of(page, size), hasNext);

    }

}
