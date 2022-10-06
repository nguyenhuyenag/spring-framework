package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtils {

	private static <T> Page<T> createPageFromList(List<T> list, Pageable pageable) {
		if (list == null) {
			throw new IllegalArgumentException("To create a Page, the list mustn't be null!");
		}
		int total = list.size();
		int start = pageable.getPageNumber() * pageable.getPageSize();
		if (start > total) {
			return new PageImpl<>(new ArrayList<>(), pageable, 0);
		}
		int end = Math.min(start + pageable.getPageSize(), total);
		return new PageImpl<>(list.subList(start, end), pageable, total);
	}

	public static <T> List<List<T>> createPageFromList(List<T> list, int nPage) {
		List<List<T>> result = new ArrayList<>();
		if (list == null || list.size() == 0) {
			return result;
		}
		int pageSize = 0;
		int total = list.size();
		if (total % nPage == 0) {
			pageSize = total / nPage;
		} else {
			pageSize = total / nPage + 1;
		}
		for (int i = 0; i < nPage; i++) {
			Page<T> page = createPageFromList(list, PageRequest.of(i, pageSize));
			result.add(page.getContent());
		}
		return result;
	}
	
	
	private static int count = 0;
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			int total =  ThreadLocalRandom.current().nextInt(0, 50);
			List<Integer> list = IntStream.rangeClosed(1, total).boxed().collect(Collectors.toList());
			int page = ThreadLocalRandom.current().nextInt(3, 10);
			System.out.println("Total: " + total + ", page: " + page);
			List<List<Integer>> split = PageUtils.createPageFromList(list, page);
			split.forEach(t -> {
				count += t.size();
				System.out.println(t.toString());
			});
			if (count != list.size()) {
				System.err.println("Check: " + (count - list.size()));
			}
			TimeUnit.SECONDS.sleep(2);
			count = 0;
			System.out.println();
		}
	}

}
