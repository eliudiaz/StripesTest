package gt.org.isis.service;

import java.util.List;

import gt.org.isis.exception.ShopNotFound;
import gt.org.isis.model.Shop;

public interface ShopService {
	
	public Shop create(Shop shop);
	public Shop delete(int id) throws ShopNotFound;
	public List<Shop> findAll();
	public Shop update(Shop shop) throws ShopNotFound;
	public Shop findById(int id);

}
