package br.com.lab.webapi.repositories;

import org.springframework.stereotype.Repository;

import br.com.lab.webapi.models.Log;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
  
}
