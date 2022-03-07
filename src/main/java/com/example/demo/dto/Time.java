
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
public class Time {

    @Expose
    private String updated;
    @Expose
    private String updatedISO;
    @Expose
    private String updateduk;

}
