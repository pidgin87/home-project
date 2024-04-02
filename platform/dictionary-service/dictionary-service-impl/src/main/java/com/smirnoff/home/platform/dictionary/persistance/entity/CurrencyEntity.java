package com.smirnoff.home.platform.dictionary.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "CURRENCY_DICT")
public record CurrencyEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "ID", nullable = false)
        String id,

        @Column(name = "ISO", nullable = false)
        String iso,
        @Column(name = "NAME", nullable = false)
        String name,

        @Column(name = "ORDER", nullable = false)
        String order

) {
}
