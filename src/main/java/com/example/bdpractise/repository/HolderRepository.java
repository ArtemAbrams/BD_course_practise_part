package com.example.bdpractise.repository;

import com.example.bdpractise.entityRelation.HolderCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolderRepository extends JpaRepository<HolderCard, Long> {
}
