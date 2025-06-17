package com.pdstpo.unomas.controllers;

import com.pdstpo.unomas.model.dtos.*;
import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.services.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private IMatchService matchService;

    @GetMapping()
    public ResponseEntity<List<MatchResponseDTO>> getMatches(@RequestBody SearchMatchDTO dto) {
        List<Match> matches = matchService.search(dto.getSportId(), dto.getRequestingUserId());
        List<MatchResponseDTO> dtos = new ArrayList<>();

        for (Match match : matches) {
            dtos.add(MatchResponseDTO.toDTO(match));
        }

        return ResponseEntity.ok(dtos);
    }

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

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getComments(@PathVariable Integer id) {
        List<Comment> comments = matchService.getAllComments(id);
        List<CommentResponseDTO> commentsDTOs = CommentResponseDTO.toDTOs(comments);

        return ResponseEntity.ok(commentsDTOs);
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

    @PostMapping("/{matchId}/comments")
    public ResponseEntity<Void> addComment(@PathVariable Integer matchId, @RequestBody CommentCreateDTO dto) {
        matchService.addComment(matchId, dto.getComment(), dto.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{matchId}")
    public ResponseEntity<Void> updateMatch(@PathVariable Integer matchId, @RequestBody MatchUpdateDTO dto) {
        matchService.updateMatch(matchId, dto.getUserId(), dto.getState());

        return ResponseEntity.noContent().build();
    }
}