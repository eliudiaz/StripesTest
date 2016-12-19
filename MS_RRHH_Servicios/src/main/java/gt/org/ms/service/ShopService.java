package gt.org.ms.service;

import java.util.List;

import gt.org.ms.exception.ShopNotFound;
import gt.org.ms.model.Shop;

public interface ShopService {
	
	public Shop create(Shop shop);
	public Shop delete(int id) throws ShopNotFound;
	public List<Shop> findAll();
	public Shop update(Shop shop) throws ShopNotFound;
	public Shop findById(int id);

}
