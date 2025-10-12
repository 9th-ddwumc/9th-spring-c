package com.example.umc9th2.domain.member.entity;

import com.example.umc9th2.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private TermName name;

}
