package com.tgdd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tgdd.entity.TGDDTVANGuiHoaDon;

@Repository
public interface TGDDTVANGuiHoaDonRepository extends JpaRepository<TGDDTVANGuiHoaDon, Long> {

	@Query(value = "SELECT t.* FROM tvan_gui_hoadon t "
			+ "WHERE t.tinhtrang_gui = :tinhtranggui "
			+ "AND (t.ngaygui_tct BETWEEN :tungay AND :denngay)", nativeQuery = true)
	List<TGDDTVANGuiHoaDon> findByTinhTrangGuiTuNgayDenNgay(String tungay, String denngay, int tinhtranggui);
	
	@Query(value = "SELECT t.* FROM tvan_gui_hoadon t "
			+ "WHERE t.matdiep = :matdiep AND t.tinhtrang_gui = :tinhtranggui", nativeQuery = true)
	List<TGDDTVANGuiHoaDon> findByMaTDiepAndTinhTrangGui(String matdiep, int tinhtranggui);

}
