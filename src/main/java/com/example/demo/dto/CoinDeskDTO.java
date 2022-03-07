
package com.example.demo.dto;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CoinDeskDTO {

    @Expose
    private Bpi bpi;
    @Expose
    private String chartName;
    @Expose
    private String disclaimer;
    @Expose
    private Time time;

}
