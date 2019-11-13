package com.di;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelloWorld {

	private String message;

	public void print() {
		System.out.println("Print: " + this.message);
	}

}
