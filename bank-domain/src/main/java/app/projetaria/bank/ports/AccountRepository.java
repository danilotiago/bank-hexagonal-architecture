package app.projetaria.bank.ports;

import app.projetaria.bank.domain.Account;

public interface AccountRepository {

    Account get(Integer number);

    void update(Account account);
}
