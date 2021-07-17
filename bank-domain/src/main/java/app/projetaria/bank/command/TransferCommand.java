package app.projetaria.bank.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TransferCommand {

    private final BigDecimal value;

    private final Integer debitAccount;

    private final Integer creditAccount;
}
