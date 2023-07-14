package com.example.bdpractise.callbackclasses;

import com.example.bdpractise.configuration.SpringContextHelper;
import com.example.bdpractise.entityRelation.DebitCard;
import com.example.bdpractise.services.EncryptionServiceImpl;
import jakarta.persistence.*;

public class DebitCardCallBackListener {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(DebitCard debitCard){
        System.out.println("before update insert");
        debitCard.setNumber(getEncryptionService().encrypt(debitCard.getNumber()));
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(DebitCard debitCard){
        System.out.println(" post load insert");
        debitCard.setNumber(getEncryptionService().decrypt(debitCard.getNumber()));
    }

    private EncryptionServiceImpl getEncryptionService(){
        return SpringContextHelper.getApplicationContext().getBean(EncryptionServiceImpl.class);
    }
}
