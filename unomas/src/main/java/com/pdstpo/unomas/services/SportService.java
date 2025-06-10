package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.entities.Sport;
import com.pdstpo.unomas.repositories.ISportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService implements ISportService {

    @Autowired
    private ISportRepository sportRepository;

    @Override
    public Sport create(Sport sport) {
        return sportRepository.save(sport);
    }

    @Override
    public List<Sport> findAll() {
        return sportRepository.findAll();
    }
}
