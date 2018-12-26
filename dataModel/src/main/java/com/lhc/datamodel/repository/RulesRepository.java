package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.rules.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by exi380 on 18/12/2018.
 */
@Repository
public interface RulesRepository extends JpaRepository<Rule, Long> {


}
