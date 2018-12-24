package com.lhc.datamodel.repository;


import com.lhc.datamodel.entities.VisiteurRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteurRepository extends JpaRepository<Visiteur, Long> {
}
