package com.lhc.datamodel.repository.security;

import com.lhc.datamodel.entities.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("privilegeRepository")
public interface PrivilegeRepository  extends JpaRepository<Privilege, Long>{
	

}
