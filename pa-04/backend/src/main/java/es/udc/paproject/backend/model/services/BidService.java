package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.exceptions.*;

import java.math.BigDecimal;

public interface BidService {

    /**ANUNCIAR PRODUCTO*/
    Product insertProduct(Long userId, Long categoryId, String name, String description, int bidMinutesDuration, BigDecimal initialPrice, String shippingInfo)
            throws InstanceNotFoundException;

    /**PUJAR POR UN PRODUCTO*/
    Bid bidOnAProduct(Long userId, Long productId, BigDecimal maxBid)
            throws InstanceNotFoundException, BidTimeExpiredException, BidMinimumPriceException;

    /**CONSULTAR PRODUCTOS ANUNCIADOS*/
    Block<Product> findInsertedProducts(Long userId, int page, int size);

    /**CONSULTAR PUJAS REALIZADAS*/
    Block<Bid> findDoneBids(Long userId, int page, int size);

}
