package app.projetaria.bank.ports.usecase;

import app.projetaria.bank.domain.Account;

import java.math.BigDecimal;

public interface Transfer {

    Account get(Integer number);

    void transfer(BigDecimal value, Integer debitAccount, Integer creditAccount);
}
