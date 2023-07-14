package com.example.bdpractise.repository;

import com.example.bdpractise.entityRelation.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitRepository extends JpaRepository<DebitCard, Long> {
}
