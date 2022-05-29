package com.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtils {

	private static <T> Page<T> toPages(List<T> list, Pageable pageable) {
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

	public static <T> List<List<T>> toPages(List<T> list, int nPage) {
		List<List<T>> result = new ArrayList<>();
		if (list == null || list.size() == 0) {
			return result;
		}
		int sizeOfPage = 0;
		int total = list.size();
		if (total % nPage == 0) {
			sizeOfPage = total / nPage;
		} else {
			sizeOfPage = total / nPage + 1;
		}
		for (int i = 0; i < nPage; i++) {
			Page<T> page = toPages(list, PageRequest.of(i, sizeOfPage));
			result.add(page.getContent());
		}
		return result;
	}

}
