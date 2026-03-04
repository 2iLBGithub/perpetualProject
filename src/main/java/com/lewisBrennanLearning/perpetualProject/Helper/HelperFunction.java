package com.lewisBrennanLearning.perpetualProject.Helper;

import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
public class HelperFunction {

    public String translateIsoCode(String isoCode) {
        Locale locale = new Locale("", isoCode);
        return locale.getDisplayCountry(Locale.ENGLISH);
    }

}
