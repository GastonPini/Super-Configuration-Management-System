package ar.com.flexia.scms.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexia.scms.model.entity.*;


public interface ApplicationRepository extends JpaRepository<Application, Long> {

	public Optional<Application> findByName(String name);

	public Optional<Application> findById(long applicationId);

}