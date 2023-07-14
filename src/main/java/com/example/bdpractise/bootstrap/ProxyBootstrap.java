package com.example.bdpractise.bootstrap;

import com.example.bdpractise.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
public class ProxyBootstrap {
    private final OwnerRepository ownerRepository;
    @Transactional
    public void lazyLoad() throws Exception {
        var owner = ownerRepository.findById(1L).get();
        var name = owner.getName();
        owner.setName("Friedman");
        var updatedOwner = ownerRepository.findById(1L).get();
        var compare = name.compareTo(updatedOwner.getName());
        System.out.println(compare);
        owner.getDebitCards().forEach(el -> {
            el.getElectronicsList().forEach(e -> {
                System.out.println(e.getName());
            });
        });
    }
}
