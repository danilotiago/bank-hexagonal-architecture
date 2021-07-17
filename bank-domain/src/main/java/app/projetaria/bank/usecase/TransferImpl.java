package app.projetaria.bank.usecase;

import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.domain.Account;
import app.projetaria.bank.exceptions.BusinessException;
import app.projetaria.bank.ports.repository.AccountRepository;
import app.projetaria.bank.ports.usecase.Transfer;
import app.projetaria.bank.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Component
public class TransferImpl implements Transfer {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private TransferService transferService;

    @Override
    public Account get(Integer number) {
        return this.repository.get(number);
    }

    @Override
    @Transactional
    public void transfer(BigDecimal value, Integer debitNumber, Integer creditNumber) {

        if (isNull(value)) {
            throw new BusinessException(ErrorsConstants.TRANSFER_VALUE_IS_REQUIRED);
        }

        if (isNull(debitNumber)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_DEBIT_IS_REQUIRED);
        }

        if (isNull(creditNumber)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_CREDIT_IS_REQUIRED);
        }

        Account debit = this.get(debitNumber);

        if (isNull(debit)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_DEBIT_NOT_FOUND);
        }

        Account credit = this.get(creditNumber);

        if (isNull(credit)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_CREDIT_NOT_FOUND);
        }

        if (debit.getNumber().equals(credit.getNumber())) {
            throw new BusinessException(ErrorsConstants.TRANSFER_SAME_ACCOUNT_IS_NOT_AUTHORIZED);
        }

        this.transferService.transferAmount(value, debit, credit);
        this.repository.update(debit);
        this.repository.update(credit);
     }
}
