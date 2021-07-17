package app.projetaria.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponseDto {

    private Integer accountNumber;

    private BigDecimal balance;

    private String accountHolder;
}
