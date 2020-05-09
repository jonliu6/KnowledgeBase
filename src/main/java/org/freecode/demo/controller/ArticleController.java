package org.freecode.demo.controller;

import java.util.List;

import org.freecode.demo.model.Article;
import org.freecode.demo.model.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ArticleController {

	@Autowired
	public ArticleRepository repository;
	
	@GetMapping(path="/articles", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Article> findAllArticle() {
		return repository.findAll();
	}
}
