package com.schoolmanagment.entity.concretes;

import com.schoolmanagment.entity.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
public class Teacher extends User {

    @Column(name = "isAdvisor")
    private Boolean isAdvisor;

    @Column(unique = true)
    private String email;


}
