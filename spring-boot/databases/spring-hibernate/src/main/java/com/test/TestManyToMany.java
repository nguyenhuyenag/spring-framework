package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entity.manytomany.type2.Article;
import com.entity.manytomany.type2.Tag;
import com.repository.ArticleRepository;
import com.repository.TagRepository;

@Component
public class TestManyToMany {

	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	ArticleRepository articleRepository;

	public void test() {
		create();
	}

	@Transactional
	private void create() {
		Tag tagSport = new Tag("Sport");
		tagSport.setTitle("Thể thao 24/7");

		Article at1 = new Article("Ronaldo sang Việt nam thi đấu");
		Article at2 = new Article("Pep Guardiola học lập trình Java");

		tagSport.getArticles().add(at1);
		tagSport.getArticles().add(at2);

		at1.getTags().add(tagSport);
		at2.getTags().add(tagSport);

		// Cach 1
		// tagRepository.save(tagSport);
		
		// Cach 2
		articleRepository.save(at1);
		articleRepository.save(at2);
	}

}
