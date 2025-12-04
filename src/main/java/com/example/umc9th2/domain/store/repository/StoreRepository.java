package com.example.umc9th2.domain.store.repository;

import com.example.umc9th2.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}