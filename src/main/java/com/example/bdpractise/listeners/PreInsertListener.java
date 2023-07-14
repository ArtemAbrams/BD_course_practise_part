package com.example.bdpractise.listeners;

import com.example.bdpractise.services.EncryptionServiceImpl;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreInsertListener extends AbstractEncryptionListener implements PreInsertEventListener {
    public PreInsertListener(EncryptionServiceImpl encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        this.encrypt(event.getState(), event.getPersister().getPropertyNames() ,event.getEntity());
        return false;
    }
}
