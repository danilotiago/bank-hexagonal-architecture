package app.projetaria.bank.mapper;

import app.projetaria.bank.domain.Account;
import app.projetaria.bank.entity.AccountEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountEntityMapper {

    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    Account toAccount(AccountEntity object);

    @InheritInverseConfiguration
    AccountEntity toAccountEntity(Account account);
}
