package org.freecode.demo.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long>, JpaRepository<Article, Long> {
	/**
	 * common methods defined in CrudRepository:
	 *   long count()
	 *   void delete(T)
	 *   void deleteAll()
	 *   void deleteAll(Iterable<? extends T>
	 *   boolean deleteById(ID)
	 *   boolean existsById(ID)
	 *   Iterable<T> findAll()
	 *   Iterable<T> findAllById(Iterable<ID>)
	 *   Optional<T> findById(ID)
	 *   S save(S)
	 *   Iterable<S> saveAll(Iterable<S>)
	 */

	List<Article> findAll(); 
	Article save(Article article);
	// Optional<Article> findById(Long id); // Optional is from JDK 1.8.x
	Article findById(Integer id); // this is from ArticleRepository, not CrudRepository above
	long count();
	@Transactional
	// Transactional annotation avoids javax.persistence.TransactionRequiredException
	// http://docs.spring.io/spring/docs/current/spring-framework-reference/html/orm.html):
	void deleteById(Integer id);
	
	/**
	 * JpaRepository allows using native queries to define custom methods
	 */
	@Query(value = "SELECT NEXTVAL('article_id_seq')", nativeQuery = true)
	/**
	 * because save() in CrudRepository requires an ID before persisting the object, need to get the next value from the database sequence
	 * @return
	 */
	Integer getIdForNewArticle();
	
	/**
	 * for custom methods, the field name needs to be mapped to the one defined in entity
	 * e.g. findAllByCategory(String category) uses the category field defined in Article
	 */
	List<Article> findAllByCategory(String category);
}
