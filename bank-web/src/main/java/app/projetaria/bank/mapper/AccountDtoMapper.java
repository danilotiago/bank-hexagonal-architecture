package app.projetaria.bank.mapper;

import app.projetaria.bank.domain.Account;
import app.projetaria.bank.dto.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountDtoMapper {

    AccountDtoMapper INSTANCE = Mappers.getMapper(AccountDtoMapper.class);

    @Mapping(source = "number", target = "accountNumber")
    AccountResponseDto accountResponseDto(Account account);

}
