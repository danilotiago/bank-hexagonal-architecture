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

@DisplayName("Regras de crédito de conta")
public class CreditTest {

    private Account account;

    @BeforeEach
    public void prepareBefore() {
        this.account = new Account(1, BigDecimal.TEN, "Um Cliente Qualquer");
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor nulo seja passado como crédito")
    void shouldBeThrowsBusinessException_whenHasNullableCredit() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.account.credit(null));

        assertEquals(businessException.getMessage(), ErrorsConstants.CREDIT_VALUE_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor zero seja passado como crédito")
    void sholdBeThrowsBusinessException_whenHasZeroCreditValue() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.account.credit(BigDecimal.ZERO));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_VALUE_IS_LESS_OR_EQUAL_THAN_ZERO);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor negativo seja passado como crédito")
    void shouldBeThrowsBusinessException_whenNegativeCreditValue() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.account.credit(BigDecimal.valueOf(-1.0)));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_VALUE_IS_LESS_OR_EQUAL_THAN_ZERO);
    }

    @Test
    @DisplayName("Verifica se um crédito é feito com sucesso na conta")
    void shouldBeCreditAccount_whenPositiveCreditValue() {

        BigDecimal finalBalance = BigDecimal.valueOf(11.0);

        this.account.credit(BigDecimal.ONE);

        assertTrue(this.account.getBalance().compareTo(finalBalance) == 0);
    }
}
