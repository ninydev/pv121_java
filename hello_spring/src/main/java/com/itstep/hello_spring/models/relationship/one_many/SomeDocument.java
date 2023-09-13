package com.itstep.hello_spring.models.relationship.one_many;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "some_documents")
public class SomeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    // Техническое поле - которое я буду использовать при вносе данных
    @Transient // Используем, если нам не нужно создавать поле в базе
    @JsonIgnore // Используем - если это поле не нужно в JSON выдаче
    private UUID person_id;
    
    @ManyToOne // Говорит о том, что у многих документов может быть только один хозяин
    @JoinColumn(name = "person_id", nullable = false) // описывает ключи и правила отношений
    private SomePerson person;



}
