package com.pdstpo.unomas.repositories;

import com.pdstpo.unomas.model.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISportRepository extends JpaRepository<Sport, Integer> {
}
