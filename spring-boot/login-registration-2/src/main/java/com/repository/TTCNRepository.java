package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.TTCN;

@Repository
public interface TTCNRepository extends JpaRepository<TTCN, String> {

	@Query(value = "SELECT t.* FROM ttcn t WHERE t.masobhxh = :masobhxh AND t.hoten LIKE %:hoten% AND t.diachilh LIKE %:diachilh%", nativeQuery = true)
	public List<TTCN> tracuu(@Param("masobhxh") String masobhxh, @Param("hoten") String hoten,
			@Param("diachilh") String diachilh);

	@Query(value = "SELECT t.* FROM ttcn t WHERE t.masobhxh = :masobhxh", nativeQuery = true)
	public List<TTCN> tracuuMaso(@Param("masobhxh") String masobhxh);

	@Query(value = "SELECT t.* FROM ttcn t WHERE t.hoten LIKE %:hoten% ", nativeQuery = true)
	public List<TTCN> tracuuHoTen(@Param("hoten") String hoten);

	@Query(value = "SELECT t.* FROM ttcn t WHERE t.diachilh LIKE %:diachilh% ", nativeQuery = true)
	public List<TTCN> tracuuDiaChi(@Param("diachilh") String diachilh);

	@Query(value = ":kq", nativeQuery = true)
	public List<TTCN> tracuuAll(@Param("kq") String kq);

	@Query(value = "SELECT t.* FROM ttcn t WHERE t.masobhxh = :masobhxh AND t.hoten LIKE %:hoten%", nativeQuery = true)
	public List<TTCN> tracuuMaVaTen(@Param("masobhxh") String masobhxh, @Param("hoten") String hoten);

	@Query(value = "SELECT t.* FROM ttcn t WHERE t.masobhxh = :masobhxh AND t.diachilh LIKE %:diachilh%", nativeQuery = true)
	public List<TTCN> tracuuMaVaDiaChi(@Param("masobhxh") String masobhxh, @Param("diachilh") String diachilh);

	@Query(value = "SELECT t.* FROM ttcn t WHERE t.hoten LIKE %:hoten% AND t.diachilh LIKE %:diachilh%", nativeQuery = true)
	public List<TTCN> tracuuTenVaDiaChi(@Param("hoten") String hoten, @Param("diachilh") String diachilh);

}
