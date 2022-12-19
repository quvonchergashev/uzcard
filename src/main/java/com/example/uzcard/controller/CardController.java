package com.example.uzcard.controller;
import com.example.uzcard.entity.Card;
import com.example.uzcard.entity.Token;
import com.example.uzcard.service.interfaces.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uzcard")
public class CardController {
    private final CardService cardService;

    @GetMapping("/find-by-id{cardId}")
    public ResponseEntity<?> findById(
            @PathVariable Long id
    ){
        Card byId = cardService.findById(id);
        return ResponseEntity.ok(byId);
    }
    @GetMapping("/find-by-card-number/{cardNumber}")
    public ResponseEntity<?> findByCardNumber(
            @PathVariable String cardNumber
    ){
        Card byCardNumber = cardService.findByCardNumber(cardNumber);
        return ResponseEntity.ok(byCardNumber);
    }
    @GetMapping("/add-card-for-generate-token/{cardMask}")
    public ResponseEntity<?> addCardForGenerateToken(
            @PathVariable String cardMask
    ){
        Token token = cardService.addCardForGenerateToken(cardMask);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/find-by-token/{token}")
    public ResponseEntity<?> findByToken(
            @PathVariable String token
    ){
        Card byToken = cardService.findByToken(token);
        return ResponseEntity.ok(byToken);
    }
    @GetMapping("/refresh/{token}")
    public ResponseEntity<?> refresh(
            @PathVariable String token
    ){
        Long refresh = cardService.refresh(token);
        return ResponseEntity.ok(refresh);
    }


}
