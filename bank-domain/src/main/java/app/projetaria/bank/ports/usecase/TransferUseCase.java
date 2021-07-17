package app.projetaria.bank.ports.usecase;

import app.projetaria.bank.command.TransferCommand;
import app.projetaria.bank.domain.Account;

public interface TransferUseCase {

    Account get(Integer number);

    void transfer(TransferCommand command);
}
