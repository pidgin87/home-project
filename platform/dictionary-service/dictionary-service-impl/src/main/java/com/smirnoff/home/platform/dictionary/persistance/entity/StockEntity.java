package com.smirnoff.home.platform.dictionary.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STOCK_DICT")
@Getter
@Setter
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    String id;
    @Column(name = "TICKER", nullable = false)
    String ticker;
    @Column(name = "NAME", nullable = false)
    String name;
}
