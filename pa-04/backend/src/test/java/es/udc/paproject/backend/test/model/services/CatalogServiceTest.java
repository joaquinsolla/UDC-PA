package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.CatalogService;
import es.udc.paproject.backend.model.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CatalogServiceTest {

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private UserService userService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private UserDao userDao;

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

    private Category addCategory(String name) {
        return categoryDao.save(new Category(name));
    }

    private Product addProduct(User user, Category category, String name, BigDecimal initialPrice) {
        return productDao.save(new Product(user, category, name, "testDescription", LocalDateTime.now().plusMinutes(10), initialPrice, "testShippingInfo"));
    }


    /**TESTS FUNC-2*/
    @Test
    public void testFindByKeywords() {

        User userA = signUpUser("userA");
        Category categoryA = addCategory("categoryA");

        Product productA = addProduct(userA, categoryA, "productoA", new BigDecimal(2.99));
        Product productB = addProduct(userA, categoryA, "productoB", new BigDecimal(3.99));
        Product productC = addProduct(userA, categoryA, "productoC", new BigDecimal(4.99));
        Product productD = addProduct(userA, categoryA, "productoD", new BigDecimal(5.99));

        categoryDao.save(categoryA);
        productDao.save(productA);
        productDao.save(productB);
        productDao.save(productC);
        productDao.save(productD);

        Block<Product> expectedBlock = new Block<>(Arrays.asList(productA), false);
        assertEquals(expectedBlock, catalogService.findProducts(null, "A", 0, 1));

    }

    @Test
    public void testFindByCategory() {

        User userA = signUpUser("userA");

        Category categoryA = addCategory("categoryA");
        Category categoryB = addCategory("categoryB");

        Product productA = addProduct(userA, categoryA, "productoA", new BigDecimal(2.99));
        Product productB = addProduct(userA, categoryB, "productoB", new BigDecimal(2.99));

        categoryDao.save(categoryA);
        categoryDao.save(categoryB);
        productDao.save(productA);
        productDao.save(productB);

        Block<Product> expectedBlocki = new Block<>(Arrays.asList(productA), false);
        assertEquals(expectedBlocki, catalogService.findProducts(categoryA.getId(), null, 0, 1));

        Block<Product> expectedBlockii = new Block<>(Arrays.asList(productB), false);
        assertEquals(expectedBlockii, catalogService.findProducts(categoryB.getId(), null, 0, 1));
    }

    @Test
    public void testFindByCategoryAndKeywords(){

        User userA = signUpUser("UserA");

        Category categoryA = addCategory("categoryA");
        Product productA = addProduct(userA, categoryA, "productoA", new BigDecimal(3.99));

        categoryDao.save(categoryA);
        productDao.save(productA);

        Block<Product> expectedBlock = new Block<>(Arrays.asList(productA), false);
        assertEquals(expectedBlock, catalogService.findProducts(categoryA.getId(),"A",0,1));
    }

    @Test
    public void testFindAllProducts(){

        User userA = signUpUser("UserA");

        Category categoryA = addCategory("categoryA");
        Category categoryB = addCategory("categoryB");

        Product productA = addProduct(userA, categoryA, "productoA", new BigDecimal(4.55));
        Product productB = addProduct(userA, categoryB, "productoB", new BigDecimal(6.77));

        categoryDao.save(categoryA);
        categoryDao.save(categoryB);
        productDao.save(productA);
        productDao.save(productB);

        Block<Product> expectedBlock = new Block<>(Arrays.asList(productA,productB), false);
        assertEquals(expectedBlock, catalogService.findProducts(null,"",0,2));
    }

    @Test
    public void testFindZeroProducts(){

        User userA = signUpUser("UserA");

        Category categoryA = addCategory("categoryA");
        Category categoryB = addCategory("categoryB");

        Product productA = addProduct(userA, categoryA, "productoA", new BigDecimal(4.55));
        Product productB = addProduct(userA, categoryB, "productoB", new BigDecimal(6.77));

        categoryDao.save(categoryA);
        categoryDao.save(categoryB);
        productDao.save(productA);
        productDao.save(productB);

        Block<Product> expectedBlock = new Block<>(new ArrayList<>(), false);
        assertEquals(expectedBlock, catalogService.findProducts(null,"srthsfgj",0,1));
    }


    /**TESTS FUNC-3*/
    @Test
    public void testFindProductById() throws InstanceNotFoundException {
        User user = signUpUser("testUser");
        Category category = addCategory("testCategory");
        Product product = addProduct(user, category, "testProduct", new BigDecimal(1.99));

        userDao.save(user);
        categoryDao.save(category);
        productDao.save(product);

        assertEquals(product, catalogService.findProductById(product.getId()));
    }

    @Test
    public void testFindProductByNonExistentId() {
        assertThrows(InstanceNotFoundException.class, () -> catalogService.findProductById(NON_EXISTENT_ID));
    }

    /**TESTS EXTRA FINDALLCATEGORIES*/
    @Test
    public void testFindAllCategories(){

        Category categoryA = new Category("categoryA");
        Category categoryB = new Category("categoryB");
        Category categoryC = new Category("categoryC");
        Category categoryD = new Category("categoryD");

        categoryDao.save(categoryA);
        categoryDao.save(categoryB);
        categoryDao.save(categoryC);
        categoryDao.save(categoryD);

        assertEquals(Arrays.asList(categoryA, categoryB, categoryC, categoryD), catalogService.findAllCategories());

    }

}
