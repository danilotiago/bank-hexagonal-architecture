package app.projetaria.domain;

import app.projetaria.exceptions.BusinessException;
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
            throw new BusinessException("O valor para efetuar o crédito é obrigatório");
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor para efetuar o crédito deve ser maior que zero");
        }

        balance = balance.add(value);
    }


    public void debit(BigDecimal value) throws BusinessException {

        if (isNull(value)) {
            throw new BusinessException("O valor para efetuar o débito é obrigatório");
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor para efetuar o débito deve ser maior que zero");
        }

        if (balance.compareTo(value) < 0) {
            throw new BusinessException("Saldo insuficiente para efetuar o débito");
        }

        balance = balance.subtract(value);
    }
}
