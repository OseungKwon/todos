package me.chanwookim.todos.domain;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@ApiModel(value = "Todo Full")
public class Todo implements Serializable {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private boolean completed;
    private String imgUrl;

    private LocalDateTime completed_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

//    @OneToOne
//    private User user;
    public void create(TodoPartial todo) {
        this.name = todo.getName();
        this.completed = todo.isCompleted();
        this.created_at = LocalDateTime.now();
        this.completed_at = completed_at(todo);

    }

    public void update(TodoPartial todo) {
        this.name = todo.getName();
        this.completed = todo.isCompleted();
        this.updated_at = LocalDateTime.now();
        this.completed_at = completed_at(todo);
    }

    public LocalDateTime completed_at(TodoPartial todo) {
        LocalDateTime complteTime;
        if (todo.isCompleted()) {
            complteTime = LocalDateTime.now();
        } else {
            complteTime = null;
        }
        return complteTime;
    }
}
