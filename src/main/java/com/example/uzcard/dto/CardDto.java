package com.example.uzcard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    @NotBlank(message = "card number cannot be null")
    @Size(min = 16, max = 16)
    private String cardNumber;
    @Pattern(regexp = "^{0,1}[0-9]/[1-100] ")
    private String validityPeriod;
    @NotBlank
    private String cardName;
}
