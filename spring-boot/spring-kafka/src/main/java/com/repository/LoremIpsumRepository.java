package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Ipsum;

@Repository
public interface LoremIpsumRepository extends JpaRepository<Ipsum, Long> {
	
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE tvan_gui_hoadon t SET t.tinhtrang_gui = 0", nativeQuery = true)
//	void resetTinhTrangGui();
//
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE tvan_gui_hoadon t SET t.tinhtrang_gui = 1 WHERE t.guid = :guid", nativeQuery = true)
//	void updateTinhTrangGui(String guid);
//
//	@Query(value = "SELECT t.* FROM tvan_gui_hoadon t WHERE t.tinhtrang_gui = 0 LIMIT :limit", nativeQuery = true)
//	List<Lorem> findAllWithLimit(int limit);

}
