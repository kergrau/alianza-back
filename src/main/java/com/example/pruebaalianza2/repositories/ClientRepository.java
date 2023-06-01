package com.example.pruebaalianza2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pruebaalianza2.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

	@Query(value = "SELECT  COUNT(*) FROM clients WHERE shared_key LIKE :sharedKey%;", nativeQuery = true)
	Integer counterSharedKey(@Param("sharedKey") String businessId);

	@Query(value = "SELECT * FROM clients WHERE shared_key LIKE %:sharedKey%;", nativeQuery = true)
	List<Client> findBySharedKey(@Param("sharedKey") String businessId);
}
