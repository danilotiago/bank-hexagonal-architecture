package app.projetaria.bank.usecase;

import app.projetaria.bank.command.TransferCommand;
import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.domain.Account;
import app.projetaria.bank.exceptions.BusinessException;
import app.projetaria.bank.ports.repository.AccountRepository;
import app.projetaria.bank.ports.usecase.TransferUseCase;
import app.projetaria.bank.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Component
public class Transfer implements TransferUseCase {

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
    public void transfer(TransferCommand command) {

        if (isNull(command.getValue())) {
            throw new BusinessException(ErrorsConstants.TRANSFER_VALUE_IS_REQUIRED);
        }

        if (isNull(command.getDebitAccount())) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_DEBIT_IS_REQUIRED);
        }

        if (isNull(command.getCreditAccount())) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_CREDIT_IS_REQUIRED);
        }

        Account debit = this.get(command.getDebitAccount());

        if (isNull(debit)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_DEBIT_NOT_FOUND);
        }

        Account credit = this.get(command.getCreditAccount());

        if (isNull(credit)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_CREDIT_NOT_FOUND);
        }

        if (debit.getNumber().equals(credit.getNumber())) {
            throw new BusinessException(ErrorsConstants.TRANSFER_SAME_ACCOUNT_IS_NOT_AUTHORIZED);
        }

        this.transferService.transferAmount(command.getValue(), debit, credit);
        this.repository.update(debit);
        this.repository.update(credit);
     }
}
