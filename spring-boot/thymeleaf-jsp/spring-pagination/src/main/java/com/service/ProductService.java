package com.service;

import com.model.Product;
import com.repository.ProductRepository;
import com.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    // @Autowired
    private final ProductRepository productRepository;

    /*-
     * getSize() vs getNumberOfElements()
     *
     * For items = 55, page_size = 10
     *
     * getSize() -> Return the Page size (current page size) if it is pageable
     *
     * Example: A page can be defined to have 10 items. So, getSize() would return
     * 10 based on Page definition.
     *
     * getNumberOfElements -> Returns the actual content size of a page
     *
     * Example: The number of elements can be 10 or less than 10 based on the actual
     * data. The last page would return 5 items
     */
    public Map<String, Object> info(HttpServletRequest request) throws ServletRequestBindingException {
        Page<Product> pagedResult;
        Map<String, Object> map = new LinkedHashMap<>();
        String url = request.getRequestURL() + "?showContent=true";
        if (request.getParameter("page") == null || request.getParameter("size") == null) {
            pagedResult = productRepository.findAll(Pageable.unpaged());
        } else {
            url += "&" + request.getQueryString();
            int page = ServletRequestUtils.getIntParameter(request, "page");
            int size = ServletRequestUtils.getIntParameter(request, "size");
            pagedResult = productRepository.findAll(PageRequest.of(page, size));
        }
        if (pagedResult.hasContent()) {
            map.put("Total items", pagedResult.getTotalElements());
            int totalPages = pagedResult.getTotalPages();
            map.put("Total pages (0 -> " + (totalPages - 1) + ")", pagedResult.getTotalPages());
            map.put("Page number (current page)", pagedResult.getNumber());
            map.put("Capacity of page", pagedResult.getSize());
            map.put("Current page size (<= capacity)", pagedResult.getNumberOfElements());
            // map.put("isFirst", pagedResult.isFirst());
            // map.put("isLast", pagedResult.isLast());
            map.put("Contents", url);
        }
        return map;
    }

    public List<Product> getContent(HttpServletRequest request) throws ServletRequestBindingException {
        Page<Product> pagedResult;
        if (request.getParameter("page") == null || request.getParameter("size") == null) {
            pagedResult = productRepository.findAll(Pageable.unpaged());
        } else {
            int page = ServletRequestUtils.getIntParameter(request, "page");
            int size = ServletRequestUtils.getIntParameter(request, "size");
            pagedResult = productRepository.findAll(PageRequest.of(page, size));
        }
        return pagedResult.getContent();
    }

    public void pagingWithoutSorting(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        pagedResult.getContent();
    }

    public void pagingWithSorting(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("email"));
        // Pageable pageRequest = PageRequest.of(page, size,
        // Sort.by(sortby).ascending());
        // Pageable pageRequest = PageRequest.of(page, size,
        // Sort.by(sortby).descending());
        Page<Product> pagedResult = productRepository.findAll(paging);
        pagedResult.getContent();
    }

    public void sortingOnly(int pageNo, int pageSize) {
        Sort sortOrder = Sort.by("email");
        List<Product> list = productRepository.findAll(sortOrder);
        System.out.println(list.size());
    }

    public void sortingAnd(int pageNo, int pageSize) {
        Sort emailSort = Sort.by("email");
        Sort firstNameSort = Sort.by("first_name");
        Sort groupBySort = emailSort.and(firstNameSort);
        List<Product> list = productRepository.findAll(groupBySort);
        System.out.println(list.size());
    }

    public boolean isValidField(Class<?> clazz, String fieldName) {
        return ReflectionUtils.findField(clazz, fieldName) != null;
    }

    public PageResponse<?> getProducts(int pageNo, int pageSize, String sortField) {
        // Page count from 0
        pageNo = Math.max(0, pageNo - 1);

        List<Sort.Order> sortOrders = new ArrayList<>();
        if (StringUtils.isNotEmpty(sortField)) {
            for (String fieldName : sortField.split(",")) {
                String trimmedFieldName = fieldName.trim();
                if (isValidField(Product.class, trimmedFieldName)) {
                    sortOrders.add(Sort.Order.by(trimmedFieldName));
                }
            }
        }

        Sort sort = sortOrders.isEmpty() ? Sort.by("id") : Sort.by(sortOrders); // Default is sort by `id`
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        var result = productRepository.findAll(pageable);

        return PageResponse.builder()
                .totalPage(result.getTotalPages())
                .pageNo(result.getNumber() + 1) // Count from 0
                .pageSize(result.getSize())
                .sortBy(sort.stream().map(Sort.Order::getProperty).toList())
                .result(result.getContent())
                .build();
    }


//	public Page<Product> getProducts(int pageNo, int pageSize, String sortField, String sortDir) {
//		if (pageNo > 0) {
//			pageNo--;
//		}
//		Pageable pageable = PageRequest.of(pageNo, pageSize, //
//				sortDir.equals("asc") ? Sort.by(sortField).ascending() //
//						: Sort.by(sortField).descending() //
//		);
//		return repository.findAll(pageable);
//	}

    //	public List<Product> get(int page, int size, String sortby) {
//		Pageable pageRequest = PageRequest.of(page, size, Sort.by(sortby));
//		Sort.by("id").and(Sort.by("email"));
//		Page<Product> recordsPage = repository.findAll(pageRequest);
//		if (recordsPage.hasContent()) {
//			return recordsPage.getContent();
//		}
//		return Collections.emptyList();
//	}

}
