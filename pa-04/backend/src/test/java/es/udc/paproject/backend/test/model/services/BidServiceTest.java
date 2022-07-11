package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.BidService;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BidServiceTest {

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private UserService userService;

    @Autowired
    private BidService bidService;

    @Autowired
    private BidDao bidDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    private User signUpUser(String userName) {

        User user = new User(userName, "testPassword", "testFirstName", "testLastName", userName + "@test.com");

        try {
            userService.signUp(user);
        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }

        return user;

    }

    private Category addCategory() {
        return categoryDao.save(new Category("testCategory"));
    }

    private Product addProduct(User user, Category category, String name, BigDecimal initialPrice) {
        return productDao.save(new Product(user, category, name, "testDescription", LocalDateTime.now().plusMinutes(10), initialPrice, "testShippingInfo"));
    }

    private Product addOldProduct(User user, Category category, String name, BigDecimal initialPrice) {
        return productDao.save(new Product(user, category, name, "testDescription", LocalDateTime.now().minusMinutes(10), initialPrice, "testShippingInfo"));
    }

    private Bid addBid(User user, Product product, BigDecimal initialPrice) {
        return bidDao.save(new Bid(user, product, initialPrice));
    }


    /**TESTS FUNC-1*/
    @Test
    public void testInsertProductWithNonExistentUserId(){

        Category category = addCategory();

        assertThrows(InstanceNotFoundException.class,
                () -> bidService.insertProduct(NON_EXISTENT_ID, category.getId(), "testName", "testDescription", 10, new BigDecimal(4.95), "testShippingInfo"));
    }

    @Test
    public void testInsertProductWithNonExistentCategoryId(){

        User user = signUpUser("testUser");

        assertThrows(InstanceNotFoundException.class,
                () -> bidService.insertProduct(user.getId(), NON_EXISTENT_ID, "testName", "testDescription", 10, new BigDecimal(4.95), "testShippingInfo"));

    }

    @Test
    public void testInsertProduct() throws InstanceNotFoundException{

        User user = signUpUser("testUser");
        Category category = addCategory();

        Product testProduct = bidService.insertProduct(user.getId(), category.getId(), "testName", "testDescription", 10, new BigDecimal(4.95), "testShippingInfo");

        assertEquals(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), testProduct.getPublicationDate().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(LocalDateTime.now().plusMinutes(10).truncatedTo(ChronoUnit.MINUTES), testProduct.getExpirationDate().truncatedTo(ChronoUnit.MINUTES));

        assertEquals(user.getId(), testProduct.getUser().getId());
        assertEquals(category.getId(), testProduct.getCategory().getId());
        assertEquals("testName", testProduct.getName());
        assertEquals("testDescription", testProduct.getDescription());
        assertEquals(BigDecimal.valueOf(4.95).floatValue(), testProduct.getInitialPrice().floatValue());
        assertEquals(BigDecimal.valueOf(4.95).floatValue(), testProduct.getCurrentPrice().floatValue());
        assertEquals("testShippingInfo", testProduct.getShippingInfo());
        assertNull(testProduct.getWinnerBid());

    }

    /**TESTS FUNC-4*/
    @Test
    public void testBidOnAProductWithNonExistentUserId(){

        User user = signUpUser("UserProduct");
        Category category = addCategory();
        Product product = addProduct(user, category, "testProduct", new BigDecimal(1.99));

        assertThrows(InstanceNotFoundException.class,
                () -> bidService.bidOnAProduct(NON_EXISTENT_ID, product.getId(), new BigDecimal(2.99)));

    }

    @Test
    public void testBidOnAProductWithNonExistentProduct(){

        User user = signUpUser("UserBid");

        assertThrows(InstanceNotFoundException.class,
                () -> bidService.bidOnAProduct(user.getId(), NON_EXISTENT_ID, new BigDecimal(2.99)));

    }

    @Test
    public void testBidOnAProductWithBidTimeExpired() {

        User user1 = signUpUser("UserProduct");
        User user2 = signUpUser("UserBid");
        Category category = addCategory();
        Product product = addOldProduct(user1, category, "testProduct", new BigDecimal(1.99));

        assertThrows(BidTimeExpiredException.class,
                () -> bidService.bidOnAProduct(user2.getId(), product.getId(), new BigDecimal(2.99)));

    }

    @Test
    public void testBidOnAProductWithBidLowerThanInitialPrice() {

        User user1 = signUpUser("UserProduct");
        User user2 = signUpUser("UserBid");
        Category category = addCategory();
        Product product = addProduct(user1, category, "testProduct", new BigDecimal(10));

        assertThrows(BidMinimumPriceException.class,
                () -> bidService.bidOnAProduct(user2.getId(), product.getId(), new BigDecimal(9)));

    }

    @Test
    public void testBidOnAProductWithBidEqualToCurrentBid() throws BidMinimumPriceException, InstanceNotFoundException, BidTimeExpiredException {

        User user1 = signUpUser("UserProduct");
        User user2 = signUpUser("UserBidA");
        User user3 = signUpUser("UserBidB");
        User user4 = signUpUser("UserBidC");
        Category category = addCategory();
        Product product = addProduct(user1, category, "testProduct", new BigDecimal(10));

        bidService.bidOnAProduct(user2.getId(), product.getId(), new BigDecimal(12));
        bidService.bidOnAProduct(user3.getId(), product.getId(), new BigDecimal(11));

        assertThrows(BidMinimumPriceException.class,
                () -> bidService.bidOnAProduct(user4.getId(), product.getId(), new BigDecimal(11.5)));

    }

    @Test
    public void testBidOnAProductWithBidLowerThanCurrentBid() throws BidMinimumPriceException, InstanceNotFoundException, BidTimeExpiredException {

        User user1 = signUpUser("UserProduct");
        User user2 = signUpUser("UserBidA");
        User user3 = signUpUser("UserBidB");
        User user4 = signUpUser("UserBidC");
        Category category = addCategory();
        Product product = addProduct(user1, category, "testProduct", new BigDecimal(10));

        bidService.bidOnAProduct(user2.getId(), product.getId(), new BigDecimal(12));
        bidService.bidOnAProduct(user3.getId(), product.getId(), new BigDecimal(11));

        assertThrows(BidMinimumPriceException.class,
                () -> bidService.bidOnAProduct(user4.getId(), product.getId(), new BigDecimal(9)));

    }

    @Test
    public void testBidOnAProduct() throws BidMinimumPriceException, InstanceNotFoundException, BidTimeExpiredException {

        User user1 = signUpUser("UserProduct");
        User user2 = signUpUser("UserBidA");
        User user3 = signUpUser("UserBidB");
        Category category = addCategory();
        Product product = addProduct(user1, category, "testProduct", new BigDecimal(10));

        Bid testbid = bidService.bidOnAProduct(user2.getId(), product.getId(), new BigDecimal(12));

        assertEquals(user2.getId(), testbid.getUser().getId());
        assertEquals(product.getId(), testbid.getProduct().getId());
        assertEquals(BigDecimal.valueOf(12), testbid.getMaxBid());
        assertEquals(BigDecimal.valueOf(10), testbid.getProduct().getCurrentPrice());
        assertEquals(user2.getId(), testbid.getProduct().getWinnerBid().getUser().getId());

        Bid testbid2 = bidService.bidOnAProduct(user3.getId(), product.getId(), new BigDecimal(11));

        assertEquals(user3.getId(), testbid2.getUser().getId());
        assertEquals(product.getId(), testbid2.getProduct().getId());
        assertEquals(BigDecimal.valueOf(11), testbid2.getMaxBid());
        assertEquals(BigDecimal.valueOf(11.5), testbid2.getProduct().getCurrentPrice());
        assertEquals(user2.getId(), testbid.getProduct().getWinnerBid().getUser().getId());

    }


    /**TESTS FUNC-5*/
    @Test
    public void TestFindDoneBids(){

        User user = signUpUser("testUser");
        Category category = addCategory();
        Product product = addProduct(user, category, "product", new BigDecimal(2.99));
        Bid bid = addBid(user, product, new BigDecimal(10.00));

        categoryDao.save(category);
        productDao.save(product);
        bidDao.save(bid);

        Block<Bid> expectedBids = new Block<>(Arrays.asList(bid), false);

        assertEquals(expectedBids, bidService.findDoneBids(user.getId(), 0, 1));
    }


    /**TESTS FUNC-6*/
    @Test
    public void testFindNoInsertedProducts(){

        User user = signUpUser("testUser");
        Block<Product> expectedProducts = new Block<>(new ArrayList<>(), false);

        assertEquals(expectedProducts, bidService.findInsertedProducts(user.getId(), 0, 1));

    }

    @Test
    public void testFindInsertedProducts(){

        User user = signUpUser("testUser");
        Category category = addCategory();

        Product product1 = addProduct(user, category, "testProduct1", new BigDecimal(1.99));
        Product product2 = addProduct(user, category, "testProduct2", new BigDecimal(2.99));
        Product product3 = addProduct(user, category, "testProduct3", new BigDecimal(3.99));

        Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), true);
        assertEquals(expectedBlock, bidService.findInsertedProducts(user.getId(), 0, 2));

        expectedBlock = new Block<>(Arrays.asList(product3), false);
        assertEquals(expectedBlock, bidService.findInsertedProducts(user.getId(), 1, 2));

    }

}
