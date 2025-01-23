package com.emoney.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "EMONEY")
@NoArgsConstructor
public class Emoney {

    @Id
    @Column(name = "EMONEY_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emoneySeq;

    @Column(name = "USER_SEQ")
    private Long userSeq;

    @Column(name = "ORDER_SEQ")
    private Long orderSeq;

    @Column(name = "TYPE_SEQ")
    private Long typeSeq;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "USAGE_AMOUNT")
    private Long usageAmonut;

    @Column(name = "REMAIN_AMOUNT")
    private Long remainAmount;

    @Column(name = "EXPIRATION_DATE")
    private LocalDateTime expirationDate;

    @Column(name = "IS_APPROVED")
    private Boolean isApproved;

    @Column(name = "CONTENT")
    private String content;

    @JsonManagedReference
    @OneToMany(mappedBy = "emoney", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmoneyUsageHistory> usageHistory;
}
