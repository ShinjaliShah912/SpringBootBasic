package com.in28minutes.springboot.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Autowired
	UserRepository repository;
	
	@Autowired
	DataSource dataSource;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void run(String... args) throws Exception {
		// System.out.println("++++++++++++++++++++UserCommandLineRunner++++++++++++++++++++++++");
		repository.save(new User("Ranga", "Admin"));
		repository.save(new User("Ravi", "User"));
		repository.save(new User("Satish", "Admin"));
		repository.save(new User("Raghu", "User"));
		System.out.println(dataSource);
		log.info("------------------------ All Users ---------------------------");

		for (User user : repository.findAll()) {
			log.info(user.toString());
			entityManager.persist(user);
		}

		log.info("----------------------- Admin Users -------------------------");

		for (User user : repository.findByRole("Admin")) {
			log.info(user.toString());
		}

	}

}
