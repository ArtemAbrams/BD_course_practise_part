package com.example.bdpractise.entityRelation;

import com.example.bdpractise.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private Byte age;

    @Version
    private Integer version;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<DebitCard> debitCards;

    public void addCard(Account account) {
        if (debitCards == null) {
            debitCards = new ArrayList<>();
        }
        if (account instanceof DebitCard) {
            var debit = (DebitCard) account;
            debitCards.add(debit);
            debit.setOwner(this);
        }
    }
}
