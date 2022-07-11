package es.udc.paproject.backend.model.services;

import java.util.Optional;

import es.udc.paproject.backend.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

@Service
@Transactional(readOnly=true)
public class PermissionCheckerImpl implements PermissionChecker {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private BidDao bidDao;
	
	@Override
	public void checkUserExists(Long userId) throws InstanceNotFoundException {
		
		if (!userDao.existsById(userId)) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
	}

	@Override
	public User checkUser(Long userId) throws InstanceNotFoundException {

		Optional<User> user = userDao.findById(userId);
		
		if (!user.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
		return user.get();
		
	}

	@Override
	public void checkCategoryExists(Long categoryId) throws InstanceNotFoundException {

		if (!categoryDao.existsById(categoryId)) {
			throw new InstanceNotFoundException("project.entities.category", categoryId);
		}

	}

	@Override
	public Category checkCategory(Long categoryId) throws InstanceNotFoundException {

		Optional<Category> category = categoryDao.findById(categoryId);

		if (!category.isPresent()) {
			throw new InstanceNotFoundException("project.entities.category", categoryId);
		}

		return category.get();

	}

	@Override
	public void checkProductExists(Long productId) throws InstanceNotFoundException {

		if (!productDao.existsById(productId)) {
			throw new InstanceNotFoundException("project.entities.product", productId);
		}

	}

	@Override
	public Product checkProduct(Long productId) throws InstanceNotFoundException {

		Optional<Product> product = productDao.findById(productId);

		if (!product.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", productId);
		}

		return product.get();

	}

	@Override
	public void checkBidExists(Long bidId) throws InstanceNotFoundException {

		if (!bidDao.existsById(bidId)) {
			throw new InstanceNotFoundException("project.entities.bid", bidId);
		}

	}

	@Override
	public Bid checkBid(Long bidId) throws InstanceNotFoundException {

		Optional<Bid> bid = bidDao.findById(bidId);

		if (!bid.isPresent()) {
			throw new InstanceNotFoundException("project.entities.bid", bidId);
		}

		return bid.get();

	}

}
