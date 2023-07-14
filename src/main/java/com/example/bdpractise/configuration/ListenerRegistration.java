package com.example.bdpractise.configuration;

import com.example.bdpractise.listeners.PostLoadListener;
import com.example.bdpractise.listeners.PreInsertListener;
import com.example.bdpractise.listeners.PreUpdateListener;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

//@Configuration
@RequiredArgsConstructor
public class ListenerRegistration implements BeanPostProcessor {
    private final PostLoadListener postLoadListener;
    private final PreInsertListener preInsertListener;
    private final PreUpdateListener preUpdateListener;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof LocalContainerEntityManagerFactoryBean) {
            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                    (LocalContainerEntityManagerFactoryBean)bean;
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) localContainerEntityManagerFactoryBean
                    .getNativeEntityManagerFactory();
            EventListenerRegistry eventListenerRegistry = sessionFactory.getServiceRegistry()
                    .getService(EventListenerRegistry.class);
            eventListenerRegistry.appendListeners(EventType.PRE_INSERT, preInsertListener);
            eventListenerRegistry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
            eventListenerRegistry.appendListeners(EventType.POST_LOAD, postLoadListener);
        }
        return bean;
    }
}
