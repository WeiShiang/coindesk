package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoinDeskRequestDTO {

//    @Size(max=3, min =0)
    @NotBlank(message = "currency code can not be empty or null")
    @SerializedName("currencyCode")
    private String currencyCode;

//    @Size(max=60, min =0)
    @NotEmpty(message = "currency name can not be empty or null")
    @SerializedName("currencyName")
    private String currencyName;

//    @DecimalMax("6")
//    @DecimalMin("0.01")
    @SerializedName("rateFloat")
    private BigDecimal rateFloat;

//    @Size(max=60, min =0)
    @NotEmpty(message = "updated time UTC can not be empty or null")
    @SerializedName("updatedTimeUtc")
    private String updatedTimeUtc;

//    @Size(max=60, min =0)
    @NotEmpty(message = "updated time GMT can not be empty or null")
    @SerializedName("updatedTimeGmt")
    private String updatedTimeGmt;

//    @Size(max=60, min =0)
    @NotEmpty(message = "updated time ISO can not be empty or null")
    @SerializedName("updatedTimeIso")
    private String updatedTimeIso;

}
