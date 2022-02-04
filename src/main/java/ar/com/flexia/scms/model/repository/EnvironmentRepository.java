package ar.com.flexia.scms.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexia.scms.model.entity.*;


@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

	public Optional<Environment> findByName(String name);
	
	public Optional<Environment> findByNameAndApplication(String name, Optional<Application> application);
	
	public Optional<Environment> findByNameAndApplication(String name, Application application);
	
}