package com.di.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String country;
	private String province;
	private String district;

	@Override
	public String toString() {
		return "Address [country=" + country + ", province=" + province + ", district=" + district + "]";
	}

}
