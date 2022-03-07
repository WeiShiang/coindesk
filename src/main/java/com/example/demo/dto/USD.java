
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public class USD {

    @Expose
    private String code;
    @Expose
    private String description;
    @Expose
    private String rate;
    @Expose
    private Double rateFloat;
    @Expose
    private String symbol;
}
