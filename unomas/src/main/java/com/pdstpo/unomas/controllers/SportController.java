package com.pdstpo.unomas.controllers;

import com.pdstpo.unomas.model.dtos.SportCreateDTO;
import com.pdstpo.unomas.model.dtos.SportResponseDTO;
import com.pdstpo.unomas.model.entities.Sport;
import com.pdstpo.unomas.services.ISportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    @Autowired
    private ISportService sportService;

    @PostMapping
    public ResponseEntity<SportResponseDTO> createSport(@RequestBody SportCreateDTO sportCreateDTO) {
        Sport sport = sportCreateDTO.toEntity();
        Sport createdSport = sportService.create(sport);
        SportResponseDTO sportResponseDTO = SportResponseDTO.toDTO(createdSport);

        return ResponseEntity.status(HttpStatus.CREATED).body(sportResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SportResponseDTO>> getSports() {
        List<Sport> sports = sportService.findAll();
        List<SportResponseDTO> sportsDTO = new ArrayList<>();

        for (Sport sport : sports) {
            sportsDTO.add(SportResponseDTO.toDTO(sport));
        }

        return ResponseEntity.ok(sportsDTO);
    }
}
