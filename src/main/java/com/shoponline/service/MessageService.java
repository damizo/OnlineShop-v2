package com.shoponline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by dami on 2016-12-24.
 */
@Component
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        String localeString = "PL";

        if(locale.toLanguageTag().contains("EN")){
            localeString = "EN";
        }

        return messageSource.getMessage(id, null, "messages", new Locale(localeString.toLowerCase()));
    }
}
