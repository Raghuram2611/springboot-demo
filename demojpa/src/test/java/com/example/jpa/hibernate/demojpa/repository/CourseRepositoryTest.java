package com.example.jpa.hibernate.demojpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
import com.example.jpa.hibernate.demojpa.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	void findById_basic() {
		Course course = repository.findById(10003L);
		assertEquals("Spring Boot in 50steps", course.getName());
	}
	
	@Test
	@DirtiesContext
	void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	void save_basic() {
		//get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50steps", course.getName());
		//update details
		course.setName("JPA in 50steps - Updated");
		
		repository.save(course);
		//check the value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50steps - Updated", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}
	
	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		logger.info("{}",course.getReviews());
	}
	
	@Test
	@Transactional
	public void retrieveCourseForReviews() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{}",review.getCourse());
	}
}
