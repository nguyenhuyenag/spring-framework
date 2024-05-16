package com.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"pageNumber", "pageSize", "totalPage", "totalElements"})
public class PageResponse<T> {

    private Integer pageNumber;
    private Integer pageSize;
    private Object sortBy;
    private Integer totalPage;
    private Long totalElements;
    private T result;

}
