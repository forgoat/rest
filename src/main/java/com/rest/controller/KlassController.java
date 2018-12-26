package com.rest.controller;

import com.rest.entity.Klass;
import com.rest.entity.TeamStudent;
import com.rest.service.KlassService;
import com.rest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "class")
public class KlassController {
    @Autowired
    private KlassService klassService;
    @Autowired
    private TeamService teamService;

    /**
     * 删除班级
     * @param id
     * @return
     */
    @DeleteMapping(value = "{classId}")
    public HttpStatus deleteById(@PathVariable("classId") Long id){
        if(klassService.deleteById(id)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * 查找在某课程下学生的所属班级
     * @param studentId
     * @param courseId
     * @return
     */
    @GetMapping(value = "klassTeam")
    public Long findKlassIdByCourseIdAndStudentId(Long studentId,Long courseId){
        return klassService.findKlassIdByCourseIdAndStudentId(courseId,studentId);
    }

    /**
     * 查看某课程的某队伍同学班级分布
     * @param teamId
     * @param courseId
     * @return
     */
    @GetMapping(value = "course/{courseId}")
    public List<Long> findKlassDis(Long teamId,@PathVariable("courseId") Long courseId){
        List<TeamStudent> teamStudentList=teamService.findStudentByTeamId(teamId);
        List<Long> klassList=new ArrayList<Long>();
        for(TeamStudent teamStudent:teamStudentList){
            klassList.add(klassService.findKlassIdByCourseIdAndStudentId(courseId,teamStudent.getStudentId()));
        }
        return klassList;
    }
    @GetMapping(value = "findByCourseId")
    public List<Klass> findByCourseId(Long courseId){
        return klassService.findByCourseId(courseId);
    }
}
