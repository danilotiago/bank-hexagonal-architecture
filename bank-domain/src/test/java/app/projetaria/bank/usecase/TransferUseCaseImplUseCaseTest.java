package app.projetaria.bank.usecase;

import app.projetaria.bank.RunConfiguration;
import app.projetaria.bank.command.TransferCommand;
import app.projetaria.bank.constants.ErrorsConstants;
import app.projetaria.bank.domain.Account;
import app.projetaria.bank.exceptions.BusinessException;
import app.projetaria.bank.ports.repository.AccountRepository;
import app.projetaria.bank.ports.usecase.TransferUseCase;
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
public class TransferUseCaseImplUseCaseTest {

    @Autowired
    private TransferUseCase transferUseCase;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferService transferService;

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um valor nulo seja passado para efetuar uma transferência")
    void shouldBeThrowsBusinessException_whenHasNullableCredit() {

        TransferCommand command = new TransferCommand(null, 10, 20);

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transferUseCase.transfer(command));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_VALUE_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um número de conta de débito seja passado para efetuar uma transferência")
    void shouldBeThrowsBusinessException_whenHasNullableDebitNumber() {

        TransferCommand command = new TransferCommand(BigDecimal.ONE, null, 20);

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transferUseCase.transfer(command));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_DEBIT_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso um número de conta de crédito seja passado para efetuar uma transferência")
    void shouldBeThrowsBusinessException_whenHasNullableCreditNumber() {

        TransferCommand command = new TransferCommand(BigDecimal.ONE, 10, null);

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transferUseCase.transfer(command));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_CREDIT_IS_REQUIRED);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso a conta de débito seja inexistente")
    void shouldBeThrowsBusinessException_whenAccountDebitNotFound() {

        TransferCommand command = new TransferCommand(BigDecimal.ONE, 50, 10);

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transferUseCase.transfer(command));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_DEBIT_NOT_FOUND);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso a conta de crédito seja inexistente")
    void shouldBeThrowsBusinessException_whenAccountCreditNotFound() {

        TransferCommand command = new TransferCommand(BigDecimal.ONE, 10, 50);

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transferUseCase.transfer(command));

        assertEquals(businessException.getMessage(), ErrorsConstants.ACCOUNT_CREDIT_NOT_FOUND);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada caso a conta de crédito e débito sejam a mesma")
    void shouldBeThrowsBusinessException_whenSameAccountCreditAndDebit() {

        TransferCommand command = new TransferCommand(BigDecimal.ONE, 10, 10);

        BusinessException businessException = assertThrows(
                BusinessException.class,
                () -> this.transferUseCase.transfer(command));

        assertEquals(businessException.getMessage(), ErrorsConstants.TRANSFER_SAME_ACCOUNT_IS_NOT_AUTHORIZED);
    }

    @Test
    @DisplayName("Verifica se uma transferência é efetuada com sucesso")
    void shouldBeTransfer() {

        TransferCommand command = new TransferCommand(BigDecimal.valueOf(7.0), 30, 10);

        this.transferUseCase.transfer(command);

        Account debit = this.transferUseCase.get(30);
        Account credit = this.transferUseCase.get(10);

        assertTrue(debit.getBalance().compareTo(BigDecimal.valueOf(3.0)) == 0);
        assertTrue(credit.getBalance().compareTo(BigDecimal.valueOf(8.0)) == 0);
    }
}
