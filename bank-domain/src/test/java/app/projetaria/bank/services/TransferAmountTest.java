package app.projetaria.bank.services;

import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.domain.Account;
import app.projetaria.bank.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Regras de transferência entre contas")
public class TransferAmountTest {

    private Transfer transfer;
    private Account debit;
    private Account credit;

    @BeforeEach
    public void prepareBefore() {
        this.transfer = new Transfer();
        this.debit = new Account(1, BigDecimal.TEN, "Um cliente qualquer");
        this.credit = new Account(2, BigDecimal.TEN, "Um outro cliente qualquer");
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor nulo seja passado na transferência")
    void shouldBeThrowsBusinessException_whenNullableTransferAmountValue() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.transfer.transferAmount(null, debit, credit));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_VALUE_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor zero seja passado na transferência")
    void shouldBeThrowsBusinessException_whenZeroTransferAmountValue() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.transfer.transferAmount(BigDecimal.ZERO, debit, credit));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_VALUE_IS_LESS_OR_EQUAL_THAN_ZERO);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso uma conta de débito nula seja passada na transferência")
    void shouldBeThrowsBusinessException_whenNullableDebitAccount() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.transfer.transferAmount(BigDecimal.ONE, null, credit));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_DEBIT_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso uma conta de crédito nula seja passada na transferência")
    void shouldBeThrowsBusinessException_whenNullableCreditAccount() {

        BusinessException businessException = assertThrows(BusinessException.class,
                () -> this.transfer.transferAmount(BigDecimal.ONE, debit, null));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_CREDIT_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma transferência entre contas é feita com sucesso")
    void shouldBeTransferAmountBetweenAccounts_whenHasValidValues() {

        BigDecimal finalBalanceDebitAccount = BigDecimal.valueOf(3.0);
        BigDecimal finalBalanceCreditAccount = BigDecimal.valueOf(17.0);

        this.transfer.transferAmount(BigDecimal.valueOf(7.0), debit, credit);

        assertTrue(debit.getBalance().compareTo(finalBalanceDebitAccount) == 0);
        assertTrue(credit.getBalance().compareTo(finalBalanceCreditAccount) == 0);
    }
}