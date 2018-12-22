package com.rest.controller;

import com.rest.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "class")
public class KlassController {
    @Autowired
    private KlassService klassService;
    @DeleteMapping(value = "{classId}")
    public HttpStatus deleteById(@PathVariable("classId") Long id){
        if(klassService.deleteById(id)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
