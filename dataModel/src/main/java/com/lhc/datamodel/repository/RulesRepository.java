package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.MatchRecord;
import com.lhc.datamodel.entities.rules.RuleRecord;

/**
 * Created by exi380 on 18/12/2018.
 */
@Repository
public interface RulesRepository extends JpaRepository<RuleRecord, Long> {


}
