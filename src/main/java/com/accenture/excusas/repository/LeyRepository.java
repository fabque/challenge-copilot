package com.accenture.excusas.repository;

import com.accenture.excusas.model.Ley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeyRepository extends JpaRepository<Ley, Long> {
}
