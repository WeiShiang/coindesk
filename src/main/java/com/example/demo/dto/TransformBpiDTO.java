
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class TransformBpiDTO {

    @SerializedName("updatedTime")
    private String updatedTime;

    @SerializedName("EUR")
    private EUR eUR;
    @SerializedName("GBP")
    private GBP gBP;
    @SerializedName("USD")
    private USD uSD;

}
