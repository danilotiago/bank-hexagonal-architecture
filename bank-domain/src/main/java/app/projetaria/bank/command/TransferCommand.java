package app.projetaria.bank.command;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class TransferCommand {

    private final BigDecimal value;
    
    private final Integer debitAccount;

    private final Integer creditAccount;
}
