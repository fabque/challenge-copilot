package com.accenture.excusas.repository;

import com.accenture.excusas.model.Consecuencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsecuenciaRepository extends JpaRepository<Consecuencia, Long> {
}
