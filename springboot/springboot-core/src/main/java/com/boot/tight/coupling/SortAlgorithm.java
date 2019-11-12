package com.boot.tight.coupling;

interface Sort {
	void sort(int[] array);
}

class QuickSort implements Sort {
	@Override
	public void sort(int[] array) {
		System.out.println("I'm Quick Sort Algorithm");
	}
}

class BubbleSort implements Sort {
	@Override
	public void sort(int[] array) {
		System.out.println("I'm Bubble Sort Algorithm");
	}
}

public class SortAlgorithm {

	private Sort sort;

	public SortAlgorithm(Sort sort) {
		this.sort = sort;
	}

	public void business(int[] array) {
		sort.sort(array);
	}

	public static void main(String[] args) {

		int[] arr = null;

		Sort bubbleSort = new BubbleSort();
		SortAlgorithm business1 = new SortAlgorithm(bubbleSort);
		business1.business(arr);

		Sort quickSort = new QuickSort();
		SortAlgorithm business2 = new SortAlgorithm(quickSort);
		business2.business(arr);
	}

}
