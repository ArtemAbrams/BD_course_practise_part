package com.example.bdpractise.listeners;

import com.example.bdpractise.annotation.EncryptedField;
import com.example.bdpractise.services.EncryptionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@RequiredArgsConstructor
public abstract class AbstractEncryptionListener {
    private final EncryptionServiceImpl encryptionService;

    public void encrypt(Object[] state, Object[] propertiesNames, Object entity){
        ReflectionUtils.doWithFields(entity.getClass(), field -> encryptField(field, propertiesNames, state) , this::isFieldAccessForEncrypted);
    }
    public void decrypt(Object entity){
        ReflectionUtils.doWithFields(entity.getClass(), field -> decryptField(field, entity) , this::isFieldAccessForEncrypted);
    }
    private boolean isFieldAccessForEncrypted(Field field){
       return AnnotationUtils.findAnnotation(field, EncryptedField.class) != null;
    }
    private void encryptField(Field field, Object[] property, Object[] state){
        try {
            var index = getIndexOfIndex(field.getName(), property);
            state[index] = encryptionService.encrypt(state[index].toString());
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    private void decryptField(Field field, Object entity){
        try {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, entity);
            ReflectionUtils.setField(field, entity, encryptionService.decrypt(value.toString()));
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    private Integer getIndexOfIndex(String name, Object[] property){
       for (int i =0 ; i < property.length; i++){
           if(property[i].equals(name)){
               return i;
           }
       }
       return null;
    }
}
