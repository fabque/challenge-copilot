package com.accenture.excusas.repository;

import com.accenture.excusas.model.Contexto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextoRepository extends JpaRepository<Contexto, Long> {
}
