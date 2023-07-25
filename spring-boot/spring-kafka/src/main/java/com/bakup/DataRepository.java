package com.bakup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DataRepository extends JpaRepository<Data, Long> {

//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE tvan_gui_hoadon t SET t.tinhtrang_gui = 0", nativeQuery = true)
//	void resetTinhTrangGui();

	@Modifying
	@Transactional
	@Query(value = "UPDATE data t SET t.status = :status, t.send_time = CURRENT_TIMESTAMP() WHERE t.code = :code", nativeQuery = true)
	void updateStatus(String code, int status);

	@Query(value = "SELECT t.* FROM data t WHERE t.status = 0 ORDER BY t.create_time LIMIT :limit", nativeQuery = true)
	List<Data> findAllLimit(int limit);

}
