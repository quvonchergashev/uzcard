package com.example.uzcard.entity;


import com.example.uzcard.consts.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private LocalDateTime date;
    private String cardToken;
    private String status= String.valueOf(Status.IN_PROGRESS);

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
