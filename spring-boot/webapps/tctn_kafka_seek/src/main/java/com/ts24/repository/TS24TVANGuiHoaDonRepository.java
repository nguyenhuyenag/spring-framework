package com.ts24.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ts24.entity.TS24TVANGuiHoaDon;

@Repository
public interface TS24TVANGuiHoaDonRepository extends JpaRepository<TS24TVANGuiHoaDon, Long> {

	@Query(value = "SELECT t.* FROM tvan_gui_hoadon t "
			+ "WHERE t.tinhtrang_gui = :tinhtranggui "
			+ "AND (t.ngaygui_tct BETWEEN :tungay AND :denngay)", nativeQuery = true)
	List<TS24TVANGuiHoaDon> findByTinhTrangGuiTuNgayDenNgay(String tungay, String denngay, int tinhtranggui);
	
	@Query(value = "SELECT t.* FROM tvan_gui_hoadon t "
			+ "WHERE t.matdiep = :matdiep AND t.tinhtrang_gui = :tinhtranggui", nativeQuery = true)
	List<TS24TVANGuiHoaDon> findByMaTDiepAndTinhTrangGui(String matdiep, int tinhtranggui);

}
