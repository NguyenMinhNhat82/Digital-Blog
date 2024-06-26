package com.nmn.dto.mapper;

import com.nmn.dto.CommentDTO;
import com.nmn.model.Comments;
import com.nmn.model.Users;
import com.nmn.repository.ArticleRepository;
import com.nmn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommentMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    public CommentDTO toDto(Comments comments){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comments.getId());
        commentDTO.setContent(commentDTO.getContent());
        commentDTO.setCreatedDate(comments.getCreatedDate());
        commentDTO.setUpdatedDate(commentDTO.getUpdatedDate());
        commentDTO.setUserID(comments.getUserId().getId());
        commentDTO.setArticleID(comments.getArticleId().getId());
        return commentDTO;
    }

    public Comments toEntity(CommentDTO commentDTO){
        Comments comment  = new Comments();
        if(commentDTO.getArticleID() == null)
            comment.setId(0);
        else
            comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setUpdatedDate(commentDTO.getUpdatedDate());
        comment.setCreatedDate(commentDTO.getCreatedDate());
        comment.setUserId(userRepository.findUsersById(commentDTO.getUserID()));
        comment.setArticleId(articleRepository.findArticlesById(commentDTO.getArticleID()));
        return comment;

    }
}
