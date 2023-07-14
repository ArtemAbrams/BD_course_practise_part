package com.example.bdpractise.configuration;

import com.example.bdpractise.interceptor.HibernateInterceptor;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Configuration
@RequiredArgsConstructor
public class InterceptorConfiguration implements HibernatePropertiesCustomizer {
    private final HibernateInterceptor hibernateInterceptor;
    @Override
    public void customize(Map<String, Object> hibernateProperties) {
       // hibernateProperties.put("hibernate.session_factory.interceptor",hibernateInterceptor);
    }
}
