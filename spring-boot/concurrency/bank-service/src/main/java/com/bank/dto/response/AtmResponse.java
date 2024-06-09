package com.bank.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtmResponse {

    private boolean status;
    private String username;
    private String message;

    @JsonProperty("account_balance")
    private Integer accountBalance;

    @JsonProperty("amount_withdrawn")
    private Integer amountWithdrawn;

}
