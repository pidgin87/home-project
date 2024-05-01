package com.smirnoff.home.platform.user.profile.persistance.entity;

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
@Table(name = "user_profile")
@Getter
@Setter
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    String id;

    @Column(name = "FIRSTNAME")
    String firstName;

    @Column(name = "LASTNAME")
    String lastName;

    @Column(name = "EMAIL", nullable = false)
    String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "id")
    private CompanyEntity company;
}
