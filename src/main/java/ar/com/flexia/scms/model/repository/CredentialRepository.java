package ar.com.flexia.scms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexia.scms.model.entity.*;


@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {}