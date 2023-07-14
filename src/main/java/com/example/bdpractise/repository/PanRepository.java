package com.example.bdpractise.repository;

import com.example.bdpractise.entityRelation.PanCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanRepository extends JpaRepository<PanCard, Long> {
}
