package com.smirnoff.home.finance.fund.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static org.hibernate.annotations.SoftDeleteType.ACTIVE;

@Getter
@Setter
@Entity(name = "FUND")
@EntityListeners(AuditingEntityListener.class)
public class FundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId;

    @SoftDelete(columnName = "ACTIVE", strategy = ACTIVE)
    private Boolean active;
}


