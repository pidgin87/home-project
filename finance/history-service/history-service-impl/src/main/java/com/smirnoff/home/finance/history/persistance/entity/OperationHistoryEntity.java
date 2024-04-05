package com.smirnoff.home.finance.history.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "OPERATION")
@EntityListeners(AuditingEntityListener.class)
public class OperationHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "SOURCE_PRODUCT")
    private String sourceProduct;

    @Column(name = "SOURCE_AMOUNT")
    private BigDecimal sourceAmount;

    @Column(name = "SOURCE_CURRENCY")
    private String sourceCurrency;

    @Column(name = "DESTINATION_PRODUCT")
    private String destinationProduct;

    @Column(name = "DESTINATION_AMOUNT")
    private BigDecimal destinationAmount;

    @Column(name = "DESTINATION_CURRENCY")
    private String destinationCurrency;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;
}


