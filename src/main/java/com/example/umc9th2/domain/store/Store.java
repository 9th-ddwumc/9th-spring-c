package com.example.umc9th2.domain.store;

import com.example.umc9th2.domain.common.BaseEntity;
import com.example.umc9th2.domain.region.Region;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "stores",
        indexes = { @Index(name = "idx_stores_region", columnList = "region_id") })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 120)
    private String name;

    @Column(nullable = true, length = 255)
    private String address;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = true)
    private Region region;

    public Store(String name, String address, Region region) {
        this.name = name;
        this.address = address;
        this.region = region;
    }
}
