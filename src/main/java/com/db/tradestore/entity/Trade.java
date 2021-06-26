package com.db.tradestore.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trade")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trade {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NonNull
    @Column(name = "TradeId")
    private String tradeId;

    @NonNull
    @Column
    private int version;

    @NonNull
    @Column
    private String counterPartyId;

    @NonNull
    @Column
    private String bookId;

    @NonNull
    @Column
    private LocalDate maturityDate;

    @NonNull
    @Column
    private LocalDate createdDate;

    @NonNull
    @Column
    private String expired;

}