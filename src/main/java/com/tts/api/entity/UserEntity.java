package com.tts.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 0, max = 20)
    private String firstName;
    @Size(min = 2, max = 20)
    private String lastName;
    @Size(min = 4, max = 20)
    private String state;

    public UserEntity(String firstName, String lastName, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
    }
}
