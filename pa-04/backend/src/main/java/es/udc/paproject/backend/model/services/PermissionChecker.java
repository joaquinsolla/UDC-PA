package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

public interface PermissionChecker {
	
	void checkUserExists(Long userId) throws InstanceNotFoundException;
	
	User checkUser(Long userId) throws InstanceNotFoundException;

	void checkCategoryExists(Long categoryId) throws InstanceNotFoundException;

	Category checkCategory(Long categoryId) throws InstanceNotFoundException;

	void checkProductExists(Long categoryId) throws InstanceNotFoundException;

	Product checkProduct(Long categoryId) throws InstanceNotFoundException;

	void checkBidExists(Long bidId) throws InstanceNotFoundException;

	Bid checkBid(Long bidId) throws InstanceNotFoundException;

}
