package com.example.jpa.hibernate.demojpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.jpa.hibernate.demojpa.DemojpaApplication;
import com.example.jpa.hibernate.demojpa.entity.Course;
import com.example.jpa.hibernate.demojpa.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}
	
	@Test
	public void playingAroundWithSpringDataRepository() {
		Course course = new Course("Microservices in 100 Steps");
		repository.save(course);
		course.setName("Microservices in 100 Steps - Updated");
		repository.save(course);
		logger.info("Course -> {} ", repository.findAll());
		logger.info("Count -> {} ", repository.count());
	}

	@Test
	public void sort() {
		//Sort sort = new Sort(Sort.Direction.ASC, "name");
		//logger.info("Course -> {} ", repository.findAll(sort));
	}
	
	//Make pages to limit the content like google results
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {}", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {}", secondPage.getContent());
	}
	
}
