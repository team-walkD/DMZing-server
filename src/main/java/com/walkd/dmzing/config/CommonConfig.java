package com.walkd.dmzing.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class CommonConfig {
    public static final String ENCODE = "UTF-8";


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JsonParser jsonParser() {
        return new JsonParser();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName(ENCODE)));
        return restTemplate;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    public static String getJsonString(JsonElement element) {
        return element.getAsJsonObject().get("response")
                .getAsJsonObject().get("body")
                .getAsJsonObject().get("items")
                .getAsJsonObject().get("item")
                .getAsJsonObject().toString();
    }


}
