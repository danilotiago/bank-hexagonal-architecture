package app.projetaria.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponseDto {

    @JsonProperty("account_number")
    private Integer accountNumber;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("account_holder")
    private String accountHolder;
}
