package com.example.jpa.hibernate.demojpa;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import com.example.jpa.hibernate.demojpa.entity.Course;
import com.example.jpa.hibernate.demojpa.entity.FullTimeEmployee;
import com.example.jpa.hibernate.demojpa.entity.PartTimeEmployee;
import com.example.jpa.hibernate.demojpa.entity.Review;
import com.example.jpa.hibernate.demojpa.repository.CourseRepository;
import com.example.jpa.hibernate.demojpa.repository.EmployeeRepository;
import com.example.jpa.hibernate.demojpa.repository.StudentRepository;

@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//List<Review> reviews = new ArrayList<>();
		//reviews.add(new Review("5", "Great Hands-on Stuff"));
		//reviews.add(new Review("5", "Hats Off"));
		//courseRepository.addHardCodedReviewsForCourse();
		//courseRepository.addReviewsForCourse(10003L, reviews );
		//studentRepository.insertHardCodedStudentAndCourse();
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("1000")));
		
		employeeRepository.retrieveAllEmployees();
	}

}
