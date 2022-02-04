package ar.com.flexia.scms.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexia.scms.model.entity.*;


@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
	
	public Optional<Configuration> findById(Long configurationId);

}