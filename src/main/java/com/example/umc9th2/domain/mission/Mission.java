package com.example.umc9th2.domain.mission;

import com.example.umc9th2.domain.common.BaseEntity;
import com.example.umc9th2.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "missions",
        indexes = {
                @Index(name = "idx_missions_store", columnList = "store_id"),
                @Index(name = "idx_missions_active_sort", columnList = "is_active, sort_order, id")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(name = "is_active", nullable = true)
    private boolean active = true;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    public Mission(String title, String description, Store store) {
        this.title = title;
        this.description = description;
        this.store = store;
    }
}
