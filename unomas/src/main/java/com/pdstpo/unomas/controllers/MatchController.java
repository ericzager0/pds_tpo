package com.pdstpo.unomas.controllers;

import com.pdstpo.unomas.model.dtos.MatchCreateDTO;
import com.pdstpo.unomas.model.dtos.MatchResponseDTO;
import com.pdstpo.unomas.model.dtos.PlayerAddDTO;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.services.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private IMatchService matchService;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody MatchCreateDTO matchCreateDTO) {
       Match match = matchCreateDTO.toEntity();
       Match createdMatch = matchService.create(match);
       MatchResponseDTO matchResponseDTO = MatchResponseDTO.toDTO(createdMatch);

       return ResponseEntity.status(HttpStatus.CREATED).body(matchResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getMatch(@PathVariable Integer id) {
        Match match = matchService.getById(id);
        MatchResponseDTO matchResponseDTO = MatchResponseDTO.toDTO(match);

        return ResponseEntity.ok(matchResponseDTO);
    }

    @PostMapping("/{id}/players")
    public ResponseEntity<Void> addPlayerToMatch(
            @PathVariable Integer id,
            @RequestBody PlayerAddDTO dto
    ){
        matchService.addPlayer(id, dto.getPlayerId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{matchId}/players/{playerId}")
    public ResponseEntity<Void> removePlayer(
            @PathVariable Integer matchId,
            @PathVariable Integer playerId) {

        matchService.removePlayer(matchId, playerId);

        return ResponseEntity.noContent().build();
    }
}