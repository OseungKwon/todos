package me.chanwookim.todos.domain;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ApiModel(value = "Todo Partial")
public class TodoPartial {
    @NotNull
    private String name;
    @NotNull
    private boolean completed;
}
