package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.entities.Sport;

import java.util.List;

public interface ISportService {
    Sport create(Sport user);
    List<Sport> findAll();
}
