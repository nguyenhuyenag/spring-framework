package com.ts24.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ts24.entity.TS24TVANTDiepMQ;

@Repository
public interface TS24TVANTDiepMQRepository extends JpaRepository<TS24TVANTDiepMQ, String> {

	@Query(value = "SELECT t.maloai FROM tvan_tdiep_mq t " // 
			+ "WHERE t.matdieptchieu = :matdieptchieu " //
			+ "AND t.maloai != 999", nativeQuery = true)
	List<String> findMaLoaiByMaTDiepTChieu(String matdieptchieu);

}
