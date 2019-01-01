package com.lhc.business.service.validation;

import org.springframework.validation.Errors;

/**
 * Created by exi380 on 17/12/2018.
 */
public abstract class AbstractValidationService<DTO> implements ValidationService<DTO> {

    public abstract String validate(DTO object);
}
