package com.boot.tight.coupling;

public class App2 {

	private Sort sort;

	// constructor injection
	public App2(Sort sort) {
		this.sort = sort;
	}

	public void doSomething() {
		sort.sort();
		System.out.println("Do something");
	}

	public static void main(String[] args) {
		BubbleSort sort = new BubbleSort();
		App2 app = new App2(sort);
		app.doSomething();
	}

}
