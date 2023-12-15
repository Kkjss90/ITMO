package com.example.lab_3.validate;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ValidateX implements Validator {
    private static final String NUMBER_PATTERN = "^(-)?[0-9]+(\\.[0-9]+)?$";
    private static final double[] valueX = {-4, -3, -2, -1, 0, 1, 2, 3, 4};
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Координата X не может быть пустой."));
        }
        String input = o.toString();

        input = input.replace(",", ".");

        if (!Pattern.matches(NUMBER_PATTERN, input)) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Значение X должно быть числом."));
        }
        boolean flagX = Arrays.binarySearch(valueX, Double.parseDouble(input)) > -1;
        if (!flagX){
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Координата X должна быть выбрана из диапозона {-4, -3, -2, -1, 0, 1, 2, 3, 4}."));
        }


    }

}
