package com.schoolmanagment.entity.concretes;

import com.schoolmanagment.entity.abstracts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Student extends User {

    private String motherName;
    private String fatherName;
    private int studentNumber;
    private boolean isActive;

    @Column(unique = true)
    private String email;


}
