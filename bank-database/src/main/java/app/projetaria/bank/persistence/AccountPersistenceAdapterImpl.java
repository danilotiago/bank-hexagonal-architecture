package app.projetaria.bank.persistence;

import app.projetaria.bank.domain.Account;
import app.projetaria.bank.entity.AccountEntity;
import app.projetaria.bank.mapper.AccountEntityMapper;
import app.projetaria.bank.ports.persistence.AccountPersistence;
import app.projetaria.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountPersistenceAdapterImpl implements AccountPersistence  {

    @Autowired
    private AccountRepository repository;

    @Override
    public Account get(Integer number) {
        Optional<AccountEntity> result = this.repository.findById(number);

        if (result.isPresent()) {
            return AccountEntityMapper.INSTANCE.toAccount(result.get());
        }
        return null;
    }

    @Override
    public void update(Account account) {
        AccountEntity entity = AccountEntityMapper.INSTANCE.toAccountEntity(account);

        this.repository.save(entity);
    }
}
