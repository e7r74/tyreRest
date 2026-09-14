package com.jpa.tyre.repository;

import com.jpa.tyre.model.Tyre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyreRepository extends JpaRepository<Tyre,Long> {
}
