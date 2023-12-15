package com.example.lab_3.validate;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

public class ValidateR implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                        "Радиус не должен быть нулевым."));
    }

}
