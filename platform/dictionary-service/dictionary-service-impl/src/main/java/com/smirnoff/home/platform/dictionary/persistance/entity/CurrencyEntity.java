package com.smirnoff.home.platform.dictionary.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CURRENCY_DICT")
@Getter
@Setter
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    String id;
    @Column(name = "ISO", nullable = false)
    String iso;
    @Column(name = "NAME", nullable = false)
    String name;
    @Column(name = "SYMBOL", nullable = false)
    String symbol;
    @Column(name = "DIRECTION", nullable = false)
    Long direction;
}
