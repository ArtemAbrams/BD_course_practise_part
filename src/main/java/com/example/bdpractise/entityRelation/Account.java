package com.example.bdpractise.entityRelation;

import com.example.bdpractise.Enum.Status;
import com.example.bdpractise.annotation.EncryptedField;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EncryptedField
    private String number;
    private Long cvv;

    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;
}
