package com.boot.di;

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
