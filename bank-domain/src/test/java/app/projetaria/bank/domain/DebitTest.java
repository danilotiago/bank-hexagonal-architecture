package app.projetaria.bank.domain;

import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Regra de débito de conta")
public class DebitTest {

    private Account account;

    @BeforeEach
    public void prepareBefore() {
        this.account = new Account(1, BigDecimal.TEN, "Um Cliente Qualquer");
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor nulo seja passado como débito")
    void shouldBeThrowsBusinessException_whenHasNullableDebit() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.account.debit(null));

        assertEquals(businessException.getMessage(), ErrorsConstants.DEBIT_VALUE_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor zero seja passado como débito")
    void sholdBeThrowsBusinessException_whenHasZeroDebitValue() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.account.debit(BigDecimal.ZERO));

        assertEquals(businessException.getMessage(), ErrorsConstants.DEBIT_VALUE_IS_LESS_THAN_OR_ZERO);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor negativo seja passado como débito")
    void shouldBeThrowsBusinessException_whenNegativeDebitValue() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.account.debit(BigDecimal.valueOf(-1.0)));

        assertEquals(businessException.getMessage(), ErrorsConstants.DEBIT_VALUE_IS_LESS_THAN_OR_ZERO);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso o valor da conta seja insuficiente")
    void shouldBeThrowsBusinessException_whenAccountHasInsuficientFounds() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.account.debit(BigDecimal.valueOf(11.0)));

        assertEquals(businessException.getMessage(), ErrorsConstants.INSUFICIENT_FOUNDS);
    }

    @Test
    @DisplayName("Vefirica se um débito é feito com sucesso na conta com um valor menor que o saldo")
    void shouldBeDebitAccount_whenValidDebitLessThanBalance() {

        BigDecimal finalBalance = BigDecimal.valueOf(9.0);

        this.account.debit(BigDecimal.ONE);

        assertTrue(this.account.getBalance().compareTo(finalBalance) == 0);
    }

    @Test
    @DisplayName("Vefirica se um débito é feito com sucesso na conta com um valor igual ao saldo")
    void shouldBeDebitAccount() {

        BigDecimal finalBalance = BigDecimal.ZERO;

        this.account.debit(this.account.getBalance());

        assertTrue(this.account.getBalance().compareTo(finalBalance) == 0);
    }

}
