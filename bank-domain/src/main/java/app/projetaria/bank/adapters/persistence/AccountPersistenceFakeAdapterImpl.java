package app.projetaria.bank.adapters.persistence;

import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.domain.Account;
import app.projetaria.bank.exceptions.BusinessException;
import app.projetaria.bank.ports.persistence.AccountPersistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Profile({"test"})
@Repository
public class AccountPersistenceFakeAdapterImpl implements AccountPersistence {

    private static final Logger logger = LogManager.getLogger(AccountPersistenceFakeAdapterImpl.class);

    private Map<Integer, Account> repository = new HashMap<>();

    public AccountPersistenceFakeAdapterImpl() {
        this.repository.put(10, new Account(10, BigDecimal.ONE, "Correntista Um"));
        this.repository.put(20, new Account(20, BigDecimal.ZERO, "Correntista Dois"));
        this.repository.put(30, new Account(30, BigDecimal.TEN, "Correntista Três"));
    }

    @Override
    public Account get(Integer number) {
        logger.info("Fake database::get");
        return this.repository.get(number);
    }

    @Override
    public void update(Account account) {
        logger.info("Fake database::update");
        Account accountFound = this.repository.get(account.getNumber());

        if (isNull(accountFound)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_NOT_FOUND + " " + account.getNumber());
        }

        this.repository.put(account.getNumber(), account);
    }
}
