package com.example.bdpractise.repository;

import com.example.bdpractise.entityRelation.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
