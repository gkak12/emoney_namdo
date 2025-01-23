package com.emoney.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "EMONEY_USAGE_HISTORY")
@NoArgsConstructor
public class EmoneyUsageHistory {

    @Id
    @Column(name = "EMONEY_USAGE_HISTORY_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emoneyUsageHistorySeq;

    @Column(name = "USAGE_TYPE_SEQ")
    private Long usageTypeSeq;

    @Column(name = "USAGE_AMOUNT")
    private Long usageAmount;

    @Column(name = "CONTENT")
    private Long content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMONEY_SEQ")
    @JsonBackReference
    private Emoney emoney;
}
