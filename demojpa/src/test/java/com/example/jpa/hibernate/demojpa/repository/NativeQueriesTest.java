package com.example.jpa.hibernate.demojpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpa.hibernate.demojpa.DemojpaApplication;
import com.example.jpa.hibernate.demojpa.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	void findById_basic() {
		List resultList = em.createQuery("Select c From Course c").getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void findById_typed() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	void findById_where() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c  where name like '%100 Steps' -> {}", resultList);
	}
}
