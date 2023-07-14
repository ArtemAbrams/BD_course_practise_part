package com.example.bdpractise.bootstrap;

import com.example.bdpractise.Enum.Gender;
import com.example.bdpractise.entityRelation.Owner;
import com.example.bdpractise.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final OwnerRepository ownerRepository;
    private final ProxyBootstrap proxyBootstrap;

    @Override
    public void run(String... args) throws Exception {
        proxyBootstrap.lazyLoad();
        System.out.println(proxyBootstrap.getClass().toString());

        var owner = Owner.builder()
                .age((byte)12)
                .surname("Friedman")
                .gender(Gender.Man)
                .name("Gans")
                .build();
        var saved = ownerRepository.save(owner);
        System.out.println("Version is " + saved.getVersion());

        saved.setSurname("Rommel");
        var update = ownerRepository.save(saved);
        System.out.println("Version is " + update.getVersion());

        update.setName("Erwin");
        var update_2 = ownerRepository.save(update);
        System.out.println("Version is " + update_2.getVersion());
    }
}
