package app.projetaria.bank.ports.persistence;

import app.projetaria.bank.domain.Account;

public interface AccountPersistence {

    Account get(Integer number);

    void update(Account account);
}
