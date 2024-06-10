package com.smirnoff.home.platform.dictionary.persistance.entity.currencypair;

import com.smirnoff.home.platform.dictionary.persistance.entity.currency.CurrencyEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CURRENCY_PAIR_DICT")
@Getter
@Setter
public class CurrencyPairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LEFT_CURRENCY_ID", referencedColumnName = "ID")
    private CurrencyEntity left;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RIGHT_CURRENCY_ID", referencedColumnName = "ID")
    private CurrencyEntity right;

    @Column(name = "TICKER", nullable = false)
    private String ticker;
}
