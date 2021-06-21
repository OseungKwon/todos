package me.chanwookim.todos;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.chanwookim.todos.domain.Todo;
import me.chanwookim.todos.domain.TodoPartial;
import me.chanwookim.todos.domain.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getTodo() throws Exception {
        Todo todo = new Todo();
        todo.setName("my test todo");
        Todo newTodo = todoRepository.save(todo);

        mockMvc.perform(get("/todos/{id}", newTodo.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postTodo() throws Exception {
        TodoPartial todo = new TodoPartial("post test todo", true);

        String requestJson = objectMapper.writeValueAsString(todo);
        mockMvc.perform(post("/todos")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

}
