package app.projetaria.bank.services;

import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.domain.Account;
import app.projetaria.bank.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Service
public class TransferService {

    public void transferAmount(BigDecimal value, Account debit, Account credit) {

        if (isNull(value)) {
            throw new BusinessException(ErrorsConstants.TRANSFER_VALUE_IS_REQUIRED);
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorsConstants.TRANSFER_VALUE_IS_LESS_OR_EQUAL_THAN_ZERO);
        }

        if (isNull(debit)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_DEBIT_IS_REQUIRED);
        }

        if (isNull(credit)) {
            throw new BusinessException(ErrorsConstants.ACCOUNT_CREDIT_IS_REQUIRED);
        }

        debit.debit(value);
        credit.credit(value);
    }
}
