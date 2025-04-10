package com.emoney.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "EMONEY", schema = "emoney_namdo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Long usageAmount;

    @Column(name = "REMAIN_AMOUNT")
    private Long remainAmount;

    @Column(name = "IS_APPROVED")
    private Boolean isApproved;

    @Column(name = "IS_EXPIRED")
    private Boolean isExpired;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "EXPIRATION_DATE_TIME")
    private LocalDateTime expirationDateTime;

    @Column(name = "CREATION_DATE_TIME")
    private LocalDateTime creationDateTime;

    @JsonManagedReference
    @OneToMany(mappedBy = "emoney", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmoneyUsageHistory> usageHistory;

    public void deduct(Long emoneyUsageAmount){
        this.usageAmount = this.usageAmount + emoneyUsageAmount;
        this.remainAmount = this.remainAmount - emoneyUsageAmount;
    }

    public void approve(){
        this.isApproved = true;
        this.isExpired = false;
    }

    public void reject(){
        this.isApproved = false;
    }

    public void extendExpirationTime(LocalDateTime expirationDateTime){
        this.expirationDateTime = expirationDateTime;
    }

    public void expire(){
        this.isExpired = true;
    }
}
