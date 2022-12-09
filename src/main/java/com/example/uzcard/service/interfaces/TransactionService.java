package com.example.uzcard.service.interfaces;

import com.example.uzcard.dto.CheckTransactionDto;
import com.example.uzcard.dto.FindByIdCardDataDto;
import com.example.uzcard.dto.GetMoneyDto;
import com.example.uzcard.dto.GiveMoneyDto;

public interface TransactionService {

    FindByIdCardDataDto findByIdCard(String cardNumber);
    CheckTransactionDto giveMoney(GiveMoneyDto giveMoneyDto);
    CheckTransactionDto getMoney(GetMoneyDto getMoneyDto);










}
