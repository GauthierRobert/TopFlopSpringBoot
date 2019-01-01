package com.lhc.business.service.validation;

import org.springframework.validation.Errors;

/**
 * Created by exi380 on 17/12/2018.
 */
public interface ValidationService<DTO> {

    String validate(DTO dto);

}
