package app.projetaria.bank.domain;

import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static java.util.Objects.isNull;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class Account {

    private Integer number;
    private BigDecimal balance;
    private String accountHolder;

    public Account() {
        this.number = 0;
        this.balance = BigDecimal.ZERO;
        this.accountHolder = "Não informado";
    }

    public void credit(BigDecimal value) throws BusinessException {

        if (isNull(value)) {
            throw new BusinessException(ErrorsConstants.CREDIT_VALUE_IS_REQUIRED);
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorsConstants.TRANSFER_VALUE_IS_LESS_OR_EQUAL_THAN_ZERO);
        }

        balance = balance.add(value);
    }


    public void debit(BigDecimal value) throws BusinessException {

        if (isNull(value)) {
            throw new BusinessException(ErrorsConstants.DEBIT_VALUE_IS_REQUIRED);
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorsConstants.DEBIT_VALUE_IS_LESS_THAN_OR_ZERO);
        }

        if (balance.compareTo(value) < 0) {
            throw new BusinessException(ErrorsConstants.INSUFICIENT_FOUNDS);
        }

        balance = balance.subtract(value);
    }
}
