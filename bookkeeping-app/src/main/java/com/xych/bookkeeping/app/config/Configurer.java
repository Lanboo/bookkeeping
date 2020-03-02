package com.xych.bookkeeping.app.config;

import java.text.DateFormat;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.xych.bookkeeping.app.common.text.ObjectMapperDateFormat;

@Configuration
public class Configurer extends WebMvcConfigurationSupport {
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(c -> c instanceof MappingJackson2HttpMessageConverter).findFirst().ifPresent(c -> {
            MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) c;
            DateFormat df = converter.getObjectMapper().getDateFormat();
            converter.getObjectMapper().setDateFormat(new ObjectMapperDateFormat(df));
        });
    }
}
