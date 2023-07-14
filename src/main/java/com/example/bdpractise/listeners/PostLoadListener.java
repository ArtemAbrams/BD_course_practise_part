package com.example.bdpractise.listeners;

import com.example.bdpractise.services.EncryptionServiceImpl;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.springframework.stereotype.Component;

@Component
public class PostLoadListener extends AbstractEncryptionListener implements PostLoadEventListener {
    public PostLoadListener(EncryptionServiceImpl encryptionService) {
        super(encryptionService);
    }

    @Override
    public void onPostLoad(PostLoadEvent event) {
        this.decrypt(event.getEntity());
    }
}
