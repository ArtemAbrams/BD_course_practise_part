package com.example.bdpractise.interceptor;

import com.example.bdpractise.annotation.EncryptedField;
import com.example.bdpractise.entityRelation.DebitCard;
import com.example.bdpractise.services.EncryptionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Component
@RequiredArgsConstructor
public class HibernateInterceptor implements Interceptor, Serializable{
    private final EncryptionServiceImpl encryptionService;
    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        Object[] newState = processFields(entity, state, propertyNames, "onSave");
        return Interceptor.super.onSave(entity, id, newState, propertyNames, types);
    }
    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        Object[] newState = processFields(entity, currentState, propertyNames, "onFlushDirty");
        return Interceptor.super.onFlushDirty(entity, id, newState, previousState, propertyNames, types);
    }
    private Object[] processFields(Object entity, Object[] state, String[] propertyNames, String type){
       var annotations = getAnnotationFields(entity);
       for (var fields : annotations) {
          for (int i =0; i<propertyNames.length; i++){
              if(propertyNames[i].equals(fields)){
                  if((state[i] != null && !String.valueOf(state[i]).isEmpty())) {
                      if(type.equals("onLoad")){
                          state[i] = encryptionService.decrypt(state[i].toString());
                      }
                      else {
                          state[i] = encryptionService.encrypt(state[i].toString());
                      }
                  }
              }
          }
       }
       return state;
    }

    private List<String> getAnnotationFields(Object entity){
        var annotatedFields = new ArrayList<String>();
        for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
            if(!Objects.isNull(field.getAnnotation(EncryptedField.class))){
              annotatedFields.add(field.getName());
            }
        }
        return annotatedFields;
    }

}
