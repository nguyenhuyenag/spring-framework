package com.boot.tight.coupling;

interface Sort {

	void sort();

}

class QuickSort implements Sort {

	@Override
	public void sort() {
		System.out.println("I'm Quick Sort");
	}

}

class BubbleSort implements Sort {

	@Override
	public void sort() {
		System.out.println("I'm Bubble Sort");
	}

}

public class SortAlgorithm {

	private Sort sort;

	// Dùng constructor
	public SortAlgorithm(Sort sort) {
		this.sort = sort;
	}

	public void todo() {
		sort.sort();
	}

	public static void main(String[] args) {
		Sort bubbleSort = new BubbleSort();
		SortAlgorithm business1 = new SortAlgorithm(bubbleSort);
		business1.todo();

		Sort quickSort = new QuickSort();
		SortAlgorithm business2 = new SortAlgorithm(quickSort);
		business2.todo();
	}

}
