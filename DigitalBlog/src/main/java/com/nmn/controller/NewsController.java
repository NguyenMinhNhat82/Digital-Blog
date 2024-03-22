package com.nmn.controller;

import com.nmn.dto.ArticleDTO;
import com.nmn.dto.CommentDTO;
import com.nmn.model.Articles;
import com.nmn.model.Comments;
import com.nmn.service.ArticleService;
import com.nmn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService  commentService;


    @GetMapping("/article/get-all")
    ResponseEntity<List<Objects>> getListArticles(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(articleService.getListArticle(params), HttpStatus.OK);
    }

    @PostMapping("/article/save")
    ResponseEntity<Articles> saveArticle(@RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<>(articleService.saveArticle(articleDTO), HttpStatus.OK);
    }

    @DeleteMapping("/article/{id}/delete")
    ResponseEntity<String> deleteArticle(@PathVariable("id") Integer idArticle){
        if(articleService.deleteArticles(idArticle))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/comment/save")
    ResponseEntity<Comments> saveComment(CommentDTO commentDTO){
        return  new ResponseEntity<>(commentService.saveCommentToArticle(commentDTO), HttpStatus.OK);
    }
}
