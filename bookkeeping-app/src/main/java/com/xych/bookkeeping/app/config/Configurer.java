package com.xych.bookkeeping.app.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xych.bookkeeping.app.common.text.ObjectMapperDateFormat;

// @Configuration
public class Configurer extends WebMvcConfigurationSupport {
    @Autowired
    private JacksonProperties jacksonProperties;

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        SimpleDateFormat format = new SimpleDateFormat(StringUtils.defaultString(jacksonProperties.getDateFormat(), "yyyy-MM-dd HH:mm:ss"));
        TimeZone timeZone = Objects.isNull(jacksonProperties.getTimeZone()) ? TimeZone.getTimeZone("GMT+8") : jacksonProperties.getTimeZone();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ObjectMapperDateFormat(format));
        mapper.setTimeZone(timeZone);
        converter.setObjectMapper(mapper);
        return converter;
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(c -> c instanceof MappingJackson2HttpMessageConverter);
        converters.add(jackson2HttpMessageConverter());
    }
}
