package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
@Transactional
public class BidServiceImpl implements  BidService{

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private BidDao bidDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Product insertProduct(Long userId, Long categoryId, String name, String description, int bidMinutesDuration, BigDecimal initialPrice, String shippingInfo)
            throws InstanceNotFoundException{

        User user = permissionChecker.checkUser(userId);
        Category category = permissionChecker.checkCategory(categoryId);

        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(bidMinutesDuration);

        Product product = new Product(user, category, name, description, expirationDate, initialPrice, shippingInfo);

        productDao.save(product);

        return product;
    }

    @Override
    public Bid bidOnAProduct(Long userId, Long productId, BigDecimal maxBid) throws InstanceNotFoundException, BidTimeExpiredException, BidMinimumPriceException {

        User user = permissionChecker.checkUser(userId);
        Product product = permissionChecker.checkProduct(productId);

        if (LocalDateTime.now().isAfter(product.getExpirationDate())) throw new BidTimeExpiredException();

        Bid bid;

        if (product.getWinnerBid() == null) { //Si no ha pujado nadie todavia

            if (maxBid.floatValue() < product.getCurrentPrice().floatValue()) throw new BidMinimumPriceException();

            bid = new Bid(user, product, maxBid);
            product.setWinnerBid(bid);

        } else { //Si ya hay pujas por el producto

            if (maxBid.floatValue() <= product.getCurrentPrice().floatValue()) throw new BidMinimumPriceException();

            bid = new Bid(user, product, maxBid);
            float winnerBidValue = product.getWinnerBid().getMaxBid().floatValue();
            float maxBidValue = bid.getMaxBid().floatValue();

            if (maxBidValue > winnerBidValue) {
                if (maxBidValue > winnerBidValue + 0.5) product.setCurrentPrice(BigDecimal.valueOf(winnerBidValue + 0.5));
                else product.setCurrentPrice(BigDecimal.valueOf(maxBidValue));
                product.setWinnerBid(bid);
            } else {
                if (winnerBidValue > maxBidValue + 0.5) product.setCurrentPrice(BigDecimal.valueOf(maxBidValue + 0.5));
                else product.setCurrentPrice(BigDecimal.valueOf(winnerBidValue));
            }

        }

        bidDao.save(bid);
        productDao.save(product);
        return bid;
    }

    @Override
    @Transactional(readOnly=true)
    public Block<Product> findInsertedProducts(Long userId, int page, int size){

        Slice<Product> slice = productDao.findByUserIdOrderByExpirationDateDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }

    @Override
    @Transactional(readOnly=true)
    public Block<Bid> findDoneBids(Long userId, int page, int size){

        Slice<Bid> slice = bidDao.findByUserIdOrderByDateDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }

}
