package app.projetaria.bank.adapters.usecase;

import app.projetaria.bank.RunConfiguration;
import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.domain.Account;
import app.projetaria.bank.exceptions.BusinessException;
import app.projetaria.bank.ports.conduct.AccountRepository;
import app.projetaria.bank.ports.usecase.Transfer;
import app.projetaria.bank.services.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RunConfiguration.class)
@ActiveProfiles(profiles = "test")
@DisplayName("Caso de uso - Efetuar transferência entre contas")
public class TransferTest {

    @Autowired
    private Transfer transfer;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferService transferService;

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor nulo seja passado para efetuar uma transferência")
    void shouldBeThrowsBusinessException_whenHasNullableCredit() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transfer.transfer(null, 10, 20));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_VALUE_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um número de conta de débito seja passado para efetuar uma transferência")
    void shouldBeThrowsBusinessException_whenHasNullableDebitNumber() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transfer.transfer(BigDecimal.ONE, null, 20));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_DEBIT_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um número de conta de crédito seja passado para efetuar uma transferência")
    void shouldBeThrowsBusinessException_whenHasNullableCreditNumber() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transfer.transfer(BigDecimal.ONE, 10, null));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_CREDIT_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso a conta de débito seja inexistente")
    void shouldBeThrowsBusinessException_whenAccountDebitNotFound() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transfer.transfer(BigDecimal.ONE, 50, 10));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_DEBIT_NOT_FOUND);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso a conta de crédito seja inexistente")
    void shouldBeThrowsBusinessException_whenAccountCreditNotFound() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transfer.transfer(BigDecimal.ONE, 10, 50));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_CREDIT_NOT_FOUND);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso a conta de crédito e débito sejam a mesma")
    void shouldBeThrowsBusinessException_whenSameAccountCreditAndDebit() {

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transfer.transfer(BigDecimal.ONE, 10, 10));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_SAME_ACCOUNT_IS_NOT_AUTHORIZED);
    }

    @Test
    @DisplayName("Verifica se uma transferência é efetuada com sucesso")
    void shouldBeTransfer() {

        Integer debitAccountNumber = 30;
        Integer creditAccountNumber = 10;
        BigDecimal transferValue = BigDecimal.valueOf(7.0);

        this.transfer.transfer(transferValue, debitAccountNumber, creditAccountNumber);

        Account debit = this.transfer.get(30);
        Account credit = this.transfer.get(10);

        assertTrue(debit.getBalance().compareTo(BigDecimal.valueOf(3.0)) == 0);
        assertTrue(credit.getBalance().compareTo(BigDecimal.valueOf(8.0)) == 0);
    }
}
