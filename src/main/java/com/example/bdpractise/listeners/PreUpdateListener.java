package com.example.bdpractise.listeners;

import com.example.bdpractise.services.EncryptionServiceImpl;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreUpdateListener extends AbstractEncryptionListener implements PreUpdateEventListener {
    public PreUpdateListener(EncryptionServiceImpl encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        this.encrypt(event.getState(), event.getPersister().getPropertyNames() ,event.getEntity());
        return false;
    }
}
