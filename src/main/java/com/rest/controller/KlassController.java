package com.rest.controller;

import com.rest.entity.Klass;
import com.rest.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(value = "findByCourseId")
    public List<Klass> findByCourseId(Long courseId){
        return klassService.findByCourseId(courseId);
    }
}
