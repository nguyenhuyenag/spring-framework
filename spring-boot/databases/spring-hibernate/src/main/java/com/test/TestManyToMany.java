package com.test;

import org.springframework.stereotype.Component;

import com.entity.manytomany.type2.Article;
import com.entity.manytomany.type2.Tag;

@Component
public class TestManyToMany {

	public void test() {
		create();
	}

	private void create() {
		Tag sport = new Tag("Sport");
		Tag fashion = new Tag("Fashion");
		Tag technology = new Tag("Technology");

		Article at1 = new Article("Ronaldo sang Việt nam thi đấu");
		Article at2 = new Article("Pep Guardiola học lập trình Java");
	}

}
