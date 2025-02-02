package com.emoney.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "EMONEY_USAGE_HISTORY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String content;

    @Column(name = "CREATION_DATE_TIME")
    private LocalDateTime creationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMONEY_SEQ")
    @JsonBackReference
    private Emoney emoney;
}
