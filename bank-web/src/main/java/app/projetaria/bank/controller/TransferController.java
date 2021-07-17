package app.projetaria.bank.controller;

import app.projetaria.bank.command.TransferCommand;
import app.projetaria.bank.dto.AccountResponseDto;
import app.projetaria.bank.dto.TransferRequestDto;
import app.projetaria.bank.dto.TransferResponseDto;
import app.projetaria.bank.ports.usecase.TransferUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    @Autowired
    private TransferUseCase transferUseCase;

    @GetMapping("/{account_number}")
    public ResponseEntity<AccountResponseDto> get(@PathVariable(name = "account_number") Integer accountNumber) {

        this.transferUseCase.get(accountNumber);

        return null;
    }

    @PostMapping
    public ResponseEntity<TransferResponseDto> transfer(@RequestBody TransferRequestDto request) {

        TransferCommand command = new TransferCommand(request.getAmountValue(), request.getDebitAccount(), request.getCreditAccount());

        this.transferUseCase.transfer(command);

        TransferResponseDto transferResponseDto = new TransferResponseDto();

        return  ResponseEntity.ok().body(transferResponseDto);
    }

}
