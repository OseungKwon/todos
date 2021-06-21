package me.chanwookim.todos.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
class User{
    @Id
    Integer id;
    String name;
    Integer age;
}
