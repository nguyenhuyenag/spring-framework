package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.LIpsum;

@Repository
public interface LIpsumRepository extends JpaRepository<LIpsum, Long> {

//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE tvan_gui_hoadon t SET t.tinhtrang_gui = 0", nativeQuery = true)
//	void resetTinhTrangGui();

//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE lorem_ipsum t SET t.status = :status WHERE t.code = :code", nativeQuery = true)
//	void updateStatus(String code, int status);

	@Query(value = "SELECT t.* FROM lorem_ipsum t WHERE t.status = 0 ORDER BY t.create_time LIMIT :limit", nativeQuery = true)
	List<LIpsum> findAllLimit(int limit);

}
