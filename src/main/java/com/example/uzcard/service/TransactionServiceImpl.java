package com.example.uzcard.service;

import com.example.uzcard.consts.Status;
import com.example.uzcard.dto.CheckTransactionDto;
import com.example.uzcard.dto.FindByIdCardDataDto;
import com.example.uzcard.dto.GetMoneyDto;
import com.example.uzcard.dto.GiveMoneyDto;
import com.example.uzcard.entity.Card;
import com.example.uzcard.entity.EOPS;
import com.example.uzcard.entity.Transaction;
import com.example.uzcard.repositories.TransactionRepository;
import com.example.uzcard.service.interfaces.CardService;
import com.example.uzcard.service.interfaces.EOPSService;
import com.example.uzcard.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final EOPSService eopsService;
    private final CardService cardService;
    @Override
    public FindByIdCardDataDto findByIdCard(String cardNumber) {
        Optional<Card> byCardNumber = cardService.findByCardNumberForTransaction(cardNumber);
        if (byCardNumber.isEmpty()) {
            return new FindByIdCardDataDto(null, "Not found card number.....!", null);
        }
        Card card = byCardNumber.get();
        FindByIdCardDataDto findByIdCardDataDto = new FindByIdCardDataDto();
        findByIdCardDataDto.setCardHolder(card.getCardHolder());
        findByIdCardDataDto.setMessage("Successfully...!");
        findByIdCardDataDto.setPhoneNumber(card.getConnectionPhoneNumber());
        return findByIdCardDataDto;
    }
    @Override
    public CheckTransactionDto giveMoney(GiveMoneyDto giveMoneyDto) {
        Card byToken = cardService.findByToken(giveMoneyDto.getCardToken());
        int year = ((LocalDate.now().getYear() / 100) * 100) + Integer.parseInt(byToken.getValidityPeriod().substring(3));
        int month = Integer.parseInt(byToken.getValidityPeriod().substring(0, 2));
        LocalDate of = LocalDate.of(year, month + 1, 1);
        if (of.isBefore(LocalDate.now())) {
            return new CheckTransactionDto("The card has expired...! ", null);
        }
        if (byToken.getConnectionPhoneNumber()==null) {
            return new CheckTransactionDto("Phone number is not connected..!",null);
        }
        if(byToken.isBlocked()){
            return new CheckTransactionDto("The card is blocked..!",null);
        }
        if (giveMoneyDto.getAmountTransfer()*1.01<byToken.getBalance()) {
            EOPS eops=new EOPS();
            eops.setShotNumber(giveMoneyDto.getEops().getShotNumber());
            eopsService.save(eops);
            Transaction transaction=new Transaction();
            transaction.setDate(LocalDateTime.now());
            transaction.setAmount(giveMoneyDto.getAmountTransfer());
            transaction.setCard(byToken);
            transaction.setStatus(String.valueOf(Status.SUCCEED));
            transaction.setCardToken(giveMoneyDto.getCardToken());
            Transaction save = transactionRepository.save(transaction);
            byToken.setBalance((long) (byToken.getBalance()- (giveMoneyDto.getAmountTransfer()*1.01)));
            cardService.save(byToken);

            return new CheckTransactionDto(save.getStatus(), save.getId());
        }
        return new CheckTransactionDto(String.valueOf(Status.FAILED),null);
    }
    @Override
    public CheckTransactionDto getMoney(GetMoneyDto getMoneyDto) {
        Card byCardNumber = cardService.findByCardNumber(getMoneyDto.getCardNumber());
        int year = ((LocalDate.now().getYear() / 100) * 100) + Integer.parseInt(byCardNumber.getValidityPeriod().substring(3));
        int month = Integer.parseInt(byCardNumber.getValidityPeriod().substring(0, 2));
        LocalDate of = LocalDate.of(year, month + 1, 1);
        if (of.isBefore(LocalDate.now())) {
            return new CheckTransactionDto("The card has expired...! ", null);
        }
        if (byCardNumber.getConnectionPhoneNumber()==null) {
            return new CheckTransactionDto("Phone number is not connected..!",null);
        }
        if(byCardNumber.isBlocked()){
            return new CheckTransactionDto("The card is blocked..!",null);
        }
        EOPS eops=new EOPS();
        eops.setShotNumber(getMoneyDto.getEops().getShotNumber());
        eopsService.save(eops);
        Transaction transaction=new Transaction();
        transaction.setStatus(String.valueOf(Status.SUCCEED));
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(getMoneyDto.getAmountTransfer());
        transaction.setCard(byCardNumber);
        Transaction save = transactionRepository.save(transaction);
        byCardNumber.setBalance(byCardNumber.getBalance()+getMoneyDto.getAmountTransfer());
        cardService.save(byCardNumber);

        return new CheckTransactionDto(save.getStatus(), save.getId());
    }
}
