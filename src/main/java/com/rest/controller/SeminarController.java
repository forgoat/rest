package com.rest.controller;

import com.rest.entity.Klass;
import com.rest.entity.KlassSeminar;
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

    /**
     * 保存讨论课
     * @param seminar
     * @return
     */
    @PostMapping(value = "")
    public ResponseEntity<Long> saveSeminar(Seminar seminar){
        List<Seminar> seminarList=seminarService.findByCourseIdAndRoundId(seminar.getCourseId(),seminar.getRoundId());
        int serial=0;
        if (seminarList.isEmpty()){
            serial=0;
        }
        else {
            for(Seminar s:seminarList){
                if(s.getSeminarSerial()>serial){
                    serial=s.getSeminarSerial();
                }
            }
        }
        seminar.setSeminarSerial(serial+1);
        if(seminarService.save(seminar)==1){
            List<Klass> klassList=klassService.findByCourseId(seminar.getCourseId());
            for(Klass klass:klassList){
                KlassSeminar klass_seminar=new KlassSeminar();
                klass_seminar.setKlassId(klass.getId());
                klass_seminar.setSeminarId(seminar.getId());
                seminarService.saveKlassSeminar(klass_seminar);
            }
            return new ResponseEntity<Long>(seminar.getId(), HttpStatus.ACCEPTED);
        }
        else {
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 获取讨论课所属的班级讨论课
     * @param seminarId
     * @return
     */
    @GetMapping(value = "{seminarId}/class")
    public ResponseEntity<List<KlassSeminar>> findClass(@PathVariable("seminarId") Long seminarId)
    {
        return new ResponseEntity<List<KlassSeminar>>(seminarService.findClass(seminarId),HttpStatus.OK);
    }

    @PostMapping(value = "create")
    public int save(KlassSeminar klassSeminar){
        return seminarService.saveKlassSeminar(klassSeminar);
    }

    /**
     * 获取讨论课
     * @param id
     * @return
     */
    @GetMapping(value = "{seminarId}")
    public ResponseEntity<Seminar> findSeminar(@PathVariable(value = "seminarId") Long id){
        return new ResponseEntity<Seminar>(seminarService.findById(id),HttpStatus.OK);
    }

    /**
     * 删除讨论课
     * @param id
     * @return
     */
    @DeleteMapping(value = "{seminarId}")
    public HttpStatus deleteById(@PathVariable("seminarId") Long id){
        if(seminarService.deleteSeminar(id)==1){
            List<KlassSeminar> klass_seminarList=seminarService.findClass(id);
            for(KlassSeminar klass_seminar:klass_seminarList){
                seminarService.deleteKlassSeminar(klass_seminar.getId());
            }
            Long roundId=seminarService.findById(id).getRoundId();
            List<Seminar> seminarList=seminarService.findByRoundId(roundId);
            if(seminarList.isEmpty()){

            }
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * 动态修改讨论课
     * @param seminarId
     * @param courseId
     * @param roundId
     * @param seminarName
     * @param introduction
     * @param maxTeam
     * @param isVisible
     * @param seminarSerial
     * @param enrollStartTime
     * @param enrollEndTime
     * @return
     */
    @PutMapping(value = "{seminarId}")
    public HttpStatus updateInfo(@PathVariable("seminarId") Long seminarId,Long courseId,Long roundId,String seminarName,String introduction,Integer maxTeam,Integer isVisible,Integer seminarSerial,Date enrollStartTime,Date enrollEndTime){
        if(seminarService.updateInfo(seminarId,courseId,roundId,seminarName,introduction,maxTeam,isVisible,seminarSerial,enrollStartTime,enrollEndTime)==1){
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

    /**
     * 设置讨论课所属轮次
     * @param id
     * @param roundId
     * @return
     */
    @PutMapping(value = "{seminarId}/round")
    public HttpStatus setRound(@PathVariable("seminarId")Long id,Long roundId){
        Seminar seminar=seminarService.findById(id);
        if(seminarService.updateInfo(id,seminar.getCourseId(),roundId,seminar.getSeminarName(),seminar.getIntroduction(),seminar.getMaxTeam(),seminar.getIsVisible(),seminar.getSeminarSerial(),seminar.getEnrollStartTime(),seminar.getEnrollEndTime())==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.FORBIDDEN;
        }
    }

    /**
     * 设置班级讨论课状态
     * @param id
     * @param classId
     * @param status
     * @return
     */
    @PutMapping(value = "{seminarId}/class/{classId}/status")
    public HttpStatus setStatus(@PathVariable("seminarId")Long id,@PathVariable("classId")Long classId,Integer status){
        if(seminarService.changeStatus(id,classId,status)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    @PutMapping(value = "{seminarId}/class/{classId}/reportddl")
    public HttpStatus setReportDDL(@PathVariable("seminarId")Long id,@PathVariable("classId")Long class_id,Date reportddl){
        if(seminarService.changeddl(id,class_id,reportddl)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping(value = "/round")
    public List<Seminar> findByRoundId(Long roundId){
        return seminarService.findByRoundId(roundId);
    }
    @GetMapping(value = "{seminarId}/klassSeminar")
    public KlassSeminar findKlassSeminar(@PathVariable("seminarId") Long seminarId,Long klassId){
        return seminarService.findKlassSeminar(klassId,seminarId);
    }
    @GetMapping(value = "")
    public List<Seminar> findAll(){
        return seminarService.findAll();
    }
    @GetMapping(value = "courseAndRound")
    public List<Seminar> findByCourseIdAndRoundId(Long courseId,Long roundId){
        return seminarService.findByCourseIdAndRoundId(courseId,roundId);
    }
}
