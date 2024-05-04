package com.smirnoff.home.platform.session.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_profile_session")
@Getter
@Setter
public class UserProfileSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_profile_id", nullable = false)
    private String userProfileId;

    @Column(name = "company_id", nullable = false)
    private String companyId;
}
