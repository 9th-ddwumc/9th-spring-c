package com.example.umc9th2.domain.review;

import com.example.umc9th2.domain.common.BaseEntity;
import com.example.umc9th2.domain.store.Store;
import com.example.umc9th2.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Double star;

    @Column(length = 1000)
    private String reply;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
