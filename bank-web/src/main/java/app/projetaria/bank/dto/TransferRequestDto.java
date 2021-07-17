package app.projetaria.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequestDto {

    @JsonProperty("debit_account")
    private Integer creditAccount;

    @JsonProperty("credit_account")
    private Integer debitAccount;

    @JsonProperty("amount_value")
    private BigDecimal amountValue;
}
