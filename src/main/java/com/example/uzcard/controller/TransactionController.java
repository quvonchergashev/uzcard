package com.example.uzcard.controller;
import com.example.uzcard.dto.*;
import com.example.uzcard.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uzcard")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/find-by-transaction-card-number/{cardNumber}")
    public ResponseEntity<?> transactionForCard(
            @PathVariable String cardNumber
    ) {
        FindByIdCardDataDto byIdCard = transactionService.findByIdCard(cardNumber);
        return ResponseEntity.ok(byIdCard);
    }
    @PostMapping("/give-money")
    public ResponseEntity<?> giveMoney(
            @RequestBody GiveMoneyDto giveMoneyDto
    ) {
        CheckTransactionDto checkTransaction = transactionService.giveMoney(giveMoneyDto);
        return ResponseEntity.ok(checkTransaction);
    }
    @PostMapping("/get-money")
    public ResponseEntity<?> getMoney(
            @RequestBody GetMoneyDto getMoneyDto
    ) {
        CheckTransactionDto money = transactionService.getMoney(getMoneyDto);
        return ResponseEntity.ok(money);
    }
}
