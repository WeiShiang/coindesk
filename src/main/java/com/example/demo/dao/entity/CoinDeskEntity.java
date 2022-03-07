package com.example.demo.dao.entity;

import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="coindesk")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CoinDeskEntity {

    @Id
    @Column
    private String currencyCode;
    @Column
    private String currencyName;
    @Column
    private BigDecimal rateFloat;
    @Column
    private String updatedTimeUtc;
    @Column
    private String updatedTimeGmt;
    @Column
    private String updatedTimeIso;

}
