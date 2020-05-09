package org.freecode.demo.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.freecode.demo.model.Article;
import org.freecode.demo.model.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ArticleController {

	@Autowired
	public ArticleRepository repository;
	
	@GetMapping(path="/articles", produces=MediaType.APPLICATION_JSON_VALUE)
	/**
	 * Get request URL: http://localhost:8080/articles/
	 * @return
	 */
	public List<Article> findAllArticle() {
		return repository.findAll();
	}
	
	@GetMapping(path="/articles/{category}", produces=MediaType.APPLICATION_JSON_VALUE)
	/**
	 * Get request URL: http://localhost:8080/articles/Test
	 * @param category (e.g. Test)
	 * @return
	 */
	public List<Article> findAllArticlesByCategory(@PathVariable String category) {
		return repository.findAllByCategory(category);
	}
	
	@PostMapping(path="articles/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	/**
	 * Post request URL: http://localhost:8080/articles/add
	 * in HTTP Request header, put Content-Type: application/json
	 * Important: RequestBody should be required to read the JSON data for creating the object
	 * Post request body:
	 *   {"title":"2nd Article for testing purpose","category":"Test","description":"This is a test record added from SpringBoot RestController again."}
	 * @param article
	 */
	public void addArticle(@RequestBody(required = true) Article article) {
		// get a new id from before saving the new article
		if (article != null && (article.getId() == null || article.getId() < 1)) {
			article.setId(repository.getIdForNewArticle());
		}
		
		// get the system time for article
		if (article != null && article.getLastUpdatedAt() == null) {
			Calendar cal = new GregorianCalendar();
			article.setLastUpdatedAt(cal.getTime());
		}
		repository.save(article);
	}
	
	@DeleteMapping(path="articles/{id}")
	/**
	 * Delete request URL: http://localhost:8080/articles/10008
	 * @param id (e.g. 10008)
	 */
	public void deleteArticleById(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}
