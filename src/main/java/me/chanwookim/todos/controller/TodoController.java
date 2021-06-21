package me.chanwookim.todos.controller;


import com.sun.istack.Nullable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanwookim.todos.domain.Todo;
import me.chanwookim.todos.domain.TodoPartial;
import me.chanwookim.todos.domain.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@RestController
@RequestMapping("/todos")
@Api(tags = "Todos")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;


    @GetMapping
    @ApiOperation(value = "List Todo")
    ResponseEntity<?> listTodo(@RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit,
                               @RequestParam(value = "skip", required = false, defaultValue = "0") Integer skip) {

        Stream<Todo> todos = todoRepository.findAll(PageRequest.of(skip, limit)).get();

        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    @PostMapping
    @ApiOperation(value = "Create Todo")
    ResponseEntity<Todo> postTodo(@RequestBody TodoPartial todoPartial) {
        Todo todo = new Todo();
        todo.create(todoPartial);
        return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.CREATED);
    }

    @GetMapping("/{todoId}")
    @ApiOperation(value = "Get Todo")
    ResponseEntity<Todo> getTodo(@PathVariable("todoId") Long todoId) {
        Todo todo;
        try {
            todo = todoRepository.findById(todoId).get();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }


    @PutMapping("/{todoId}")
    @ApiOperation(value = "Update Todo")
    ResponseEntity<Todo> putTodo(@PathVariable("todoId") Long todoId, @RequestBody TodoPartial todoPartial) {
        Todo editTodo;
        try {
            editTodo = todoRepository.findById(todoId).get();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        editTodo.update(todoPartial);
        todoRepository.save(editTodo);

        return new ResponseEntity<>(todoRepository.findById(todoId).get(), HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    @ApiOperation(value = "Delete Todo")
    ResponseEntity<?> deleteTodo(@PathVariable("todoId") Long todoId) {
        try {
            todoRepository.deleteById(todoId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
