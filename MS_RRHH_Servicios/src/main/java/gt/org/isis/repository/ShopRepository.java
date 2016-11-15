package gt.org.isis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gt.org.isis.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
