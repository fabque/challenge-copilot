package com.accenture.excusas.repository;

import com.accenture.excusas.model.Causa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CausaRepository extends JpaRepository<Causa, Long> {
}
