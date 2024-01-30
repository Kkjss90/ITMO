package com.example.lab_3.validate;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;
@FacesValidator("validateY")
public class ValidateY implements Validator {
    private static final String NUMBER_PATTERN = "^(-)?[0-9]+(\\.[0-9]+)?$";
    private static final BigDecimal MAX_Y = new BigDecimal("5");
    private static final BigDecimal MIN_Y = new BigDecimal("-5");
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Map<String, String> requestParameterMap = facesContext.getExternalContext().getRequestParameterMap();
        String source = requestParameterMap.get("form:source");
        if (o == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Координата Y не должна быть пустой."));
        }

        String input = o.toString();

        input = input.replace(",", ".");

        if (!Pattern.matches(NUMBER_PATTERN, input)) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Координата Y должна быть числом."));
        }

        if ("button".equals(source)) {
            BigDecimal yValue = new BigDecimal(input);

            if (yValue.compareTo(MIN_Y) <= 0 || yValue.compareTo(MAX_Y) >= 0) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                String.format("Координата Y должна быть в диапозоне (%s;%s)", MIN_Y, MAX_Y)));
            }
        }
    }
}
