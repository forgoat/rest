package com.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void teacherList()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createTeacher()throws Exception {
        String json="{\"account\":\"20056\",\"teacherName\":\"王鸿吉\",\"email\":\"245667586\"}";
        Teacher teacher=objectMapper.readValue(json,Teacher.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void actival() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/teacher/active")
        .contentType(MediaType.APPLICATION_JSON)
        .param("id","3")
        .param("password","123456")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception{
        long id=3;
        mockMvc.perform(MockMvcRequestBuilders.delete("teacher/{id}",id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void search()throws Exception {
        String name="邱明";
        mockMvc.perform(MockMvcRequestBuilders.get("teacher/searchteacher")
        .contentType(MediaType.APPLICATION_JSON)
        .param("teacherName",name)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateInfo()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/teacher/{id}/information",3)
        .contentType(MediaType.APPLICATION_JSON)
        .param("account","2343546")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}