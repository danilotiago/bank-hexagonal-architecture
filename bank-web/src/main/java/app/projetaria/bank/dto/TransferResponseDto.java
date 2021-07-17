package app.projetaria.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransferResponseDto {

    private Integer debitAccount;

    private Integer creditAccount;

    private BigDecimal amountValue;

    private LocalDateTime date;

}
