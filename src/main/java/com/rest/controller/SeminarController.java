package com.rest.controller;

import com.rest.entity.Klass;
import com.rest.entity.Klass_seminar;
import com.rest.entity.Seminar;
import com.rest.service.KlassService;
import com.rest.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "seminar")
public class SeminarController {
    @Autowired
    private SeminarService seminarService;
    @Autowired
    private KlassService klassService;
    @PostMapping(value = "")
    public ResponseEntity<Long> saveSeminar(Seminar seminar){
        if(seminarService.save(seminar)==1){
            List<Klass> klassList=klassService.findByCourseId(seminar.getCourse_id());
            for(Klass klass:klassList){
                Klass_seminar klass_seminar=new Klass_seminar();
                klass_seminar.setKlass_id(klass.getId());
                klass_seminar.setSeminar_id(seminar.getId());
                seminarService.saveKlassSeminar(klass_seminar);
            }
            return new ResponseEntity<Long>(seminar.getId(), HttpStatus.ACCEPTED);
        }
        else {
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "{seminarId}/class")
    public ResponseEntity<List<Klass_seminar>> findClass(@PathVariable("seminarId") Long seminarId)
    {
        return new ResponseEntity<List<Klass_seminar>>(seminarService.findClass(seminarId),HttpStatus.OK);
    }
    @PostMapping(value = "create")
    public int save(Klass_seminar klass_seminar){
        return seminarService.saveKlassSeminar(klass_seminar);
    }

}
