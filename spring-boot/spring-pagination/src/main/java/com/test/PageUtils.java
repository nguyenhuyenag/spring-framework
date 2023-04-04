package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections4.ListUtils;

public class PageUtils {

	// Chi 1 list thành n list con

//	private static <T> Page<T> createPageFromList(List<T> list, Pageable pageable) {
//		if (list == null) {
//			throw new IllegalArgumentException("To create a Page, the list mustn't be null!");
//		}
//		int total = list.size();
//		int start = pageable.getPageNumber() * pageable.getPageSize();
//		if (start > total) {
//			return new PageImpl<>(new ArrayList<>(), pageable, 0);
//		}
//		int end = Math.min(start + pageable.getPageSize(), total);
//		return new PageImpl<>(list.subList(start, end), pageable, total);
//	}
//
//	// Có thể có danh sách rỗng
//	public static <T> List<List<T>> splitList1(List<T> list, int nPage) {
//		List<List<T>> result = new ArrayList<>();
//		if (list == null || list.size() == 0) {
//			return result;
//		}
//		int pageSize = 0;
//		int total = list.size();
//		if (total % nPage == 0) {
//			pageSize = total / nPage;
//		} else {
//			pageSize = total / nPage + 1;
//		}
//		for (int i = 0; i < nPage; i++) {
//			Page<T> page = createPageFromList(list, PageRequest.of(i, pageSize));
//			result.add(page.getContent());
//		}
//		return result;
//	}

	// Không có danh sách rỗng
//	public static List<List<Integer>> splitList2(List<Integer> originalList, int n) {
//		List<List<Integer>> subLists = new ArrayList<List<Integer>>();
//		int size = originalList.size(); // Kích thước của danh sách ban đầu
//		int blockSize = size / n; // Kích thước của từng danh sách con
//		int remainder = size % n; // Số lượng phần tử thừa, nếu có
//		int start = 0;
//		int end = blockSize;
//		for (int i = 0; i < n; i++) {
//			if (remainder > 0) {
//				end++;
//				remainder--;
//			}
//			// List<Integer> subList = originalList.subList(start, end);
//			subLists.add(originalList.subList(start, end));
//			start = end;
//			end += blockSize;
//		}
//		return subLists;
//	}

	public static <T> List<List<T>> splitList1(List<T> list, int n) {
		List<List<T>> result = new ArrayList<>();
		int partitionSize = list.size() / n; 	// Dự đoán kích thước 1 list con
		int remaining = list.size() % n; 		// Phần dư
		int currentIndex = 0;
		for (int i = 0; i < n; i++) {
			int subListSize = partitionSize;	// Kích thước 1 list con thực tế
			if (remaining > 0) {
				subListSize++;
				remaining--;
			}
			// int subListSize = partitionSize + (remaining-- > 0 ? 1 : 0);
			result.add(list.subList(currentIndex, currentIndex + subListSize));
			currentIndex += subListSize;
		}
		return result;
	}

	// Chi 1 list thành các list con có kích thước <= n

	/*-
	 * originalList	-> Danh sách ban đầu
	 *  		  n	-> Kích thước mong muốn của sublist (sublist cuối có thể nhỏ hơn)
	 * Mỗi danh sách con sẽ có độ dài tối đa là (originalList.size() + n - 1) / n
	 */
	public static List<List<Integer>> splitBySizeOfSubList(List<Integer> originalList, int subSize) {
		if (originalList.size() <= 0) {
			throw new IllegalArgumentException("Size must be greater than 0");
		}
		return ListUtils.partition(originalList, subSize);
	}

	private static int count = 0;

	public static void test() throws InterruptedException {
		while (true) {
			int total = ThreadLocalRandom.current().nextInt(0, 50);
			int page = ThreadLocalRandom.current().nextInt(3, 10);
			if (total > page) {
				List<Integer> originalList = IntStream.rangeClosed(1, total).boxed().collect(Collectors.toList());
				List<List<Integer>> subsList = splitList1(originalList, page);
				System.out.println("Total: " + total + ", page: " + page + ", subList: " + subsList.size());
				if (subsList.size() != page) {
					System.err.println("The number of pages is wrong!");
					System.exit(-1);
				}
				subsList.forEach(t -> {
					count += t.size();
					System.out.println(t.toString());
				});
				if (originalList.size() != count) {
					System.err.println("Check: " + (originalList.size() - count));
					System.exit(-1);
				}
				count = 0;
				System.out.println();
				TimeUnit.SECONDS.sleep(2);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		test();
	}

}
