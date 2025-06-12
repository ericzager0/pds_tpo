package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.entities.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentResponseDTO {
    private String username;
    private Integer userId;
    private String comment;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static CommentResponseDTO toDTO(Comment comment) {
        CommentResponseDTO commentDTO = new CommentResponseDTO();

        commentDTO.setComment(comment.getComment());
        commentDTO.setUserId(comment.getUser().getId());
        commentDTO.setUsername(comment.getUser().getUsername());

        return commentDTO;
    }

    public static List<CommentResponseDTO> toDTOs(List<Comment> comments) {
        List<CommentResponseDTO> commentsDTO = new ArrayList<>();

        for (Comment comment : comments) {
            commentsDTO.add(toDTO(comment));
        }

        return commentsDTO;
    }
}
