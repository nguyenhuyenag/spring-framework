package com.dto;

import lombok.Data;

@Data
public class SearchRequest {

    private String keyword;       // Từ khóa tìm kiếm (tên cơ quan)
    private String maCoQuan;      // Mã cơ quan (chính xác)
    private String maTinh;        // Mã tỉnh (chính xác)
    private String maPhuongXa;    // Mã phường/xã (chính xác)
    private Integer kkqm;         // Trạng thái kkqm
    private int page = 0;         // Trang (bắt đầu từ 0)
    private int size = 20;        // Số bản ghi mỗi trang

}
