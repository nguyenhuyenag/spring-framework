package com.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SomeField implements Serializable {

	private static final long serialVersionUID = -1706798494855757080L;

	private Integer ordernumber;
	private String status;
	private BigDecimal total;

}
