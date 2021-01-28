package com.boot.di;

/*-
	HiepSy hs = null;
	
	NhiemVu dietrong = new DietRong();
	hs = new HiepSy(dietrong);
	hs.thucHienNhiemVu();
	
	// HiepSy hs = null;
	NhiemVu ccc = new CuuCongChua();
	hs = new HiepSy(ccc);
	hs.thucHienNhiemVu();
*/
public class HiepSy {

	private NhiemVu nhiemvu;

	public HiepSy() {

	}

	public HiepSy(NhiemVu nhiemvu) {
		this.nhiemvu = nhiemvu;
	}

	public void thucHienNhiemVu() {
		nhiemvu.thuchien();
	}

}
