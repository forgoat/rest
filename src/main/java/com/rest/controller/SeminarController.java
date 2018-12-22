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

import java.sql.Date;
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
//    @PostMapping(value = "create")
//    public int save(Klass_seminar klass_seminar){
//        return seminarService.saveKlassSeminar(klass_seminar);
//    }

    @GetMapping(value = "{seminarId}")
    public ResponseEntity<Seminar> findSeminar(@PathVariable(value = "seminarId") Long id){
        return new ResponseEntity<Seminar>(seminarService.findById(id),HttpStatus.OK);
    }
    @DeleteMapping(value = "{seminarId}")
    public HttpStatus deleteById(@PathVariable("seminarId") Long id){
        if(seminarService.deleteSeminar(id)==1){
            List<Klass_seminar> klass_seminarList=seminarService.findClass(id);
            for(Klass_seminar klass_seminar:klass_seminarList){
                seminarService.deleteKlassSeminar(klass_seminar.getId());
            }
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
    @PutMapping(value = "{seminarId}")
    public HttpStatus updateInfo(@PathVariable("seminarId") Long seminarId,Long course_id,Long round_id,String seminar_name,String introducation,Integer max_team,Integer is_visible,Integer seminar_serial,Date enroll_start_time,Date enroll_end_time){
        if(seminarService.updateInfo(seminarId,course_id,round_id,seminar_name,introducation,max_team,is_visible,seminar_serial,enroll_end_time,enroll_end_time)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }
//    @DeleteMapping(value = "delete")
//    public int deleteKlassSeminar(Long id){
//        return seminarService.deleteKlassSeminar(id);
//    }
}
