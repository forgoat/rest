package com.rest.controller;

import com.rest.entity.Klass;
import com.rest.entity.KlassStudent;
import com.rest.entity.TeamStudent;
import com.rest.service.KlassService;
import com.rest.service.TeamService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        return klassService.findKlassIdByCourseIdAndStudentId(studentId,courseId);
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
            Long classId=klassService.findKlassIdByCourseIdAndStudentId(courseId,teamStudent.getStudentId());
            klassList.add(classId);
        }
        HashMap<Long,Integer> hashMap=new HashMap<Long, Integer>();
        for (Long id:klassList){
            hashMap.put(id,0);
        }
        for(Long id:klassList){
            for (Map.Entry<Long,Integer> arg:hashMap.entrySet()){
                if (id.equals(arg.getKey())){
                    hashMap.put(arg.getKey(),arg.getValue()+1);
                }
            }
        }
        for (Map.Entry<Long,Integer> arg:hashMap.entrySet()){
            System.out.println("klassId: "+arg.getKey()+" number: "+arg.getValue());
        }
        System.out.println();
        return klassList;
    }
    @GetMapping(value = "findByCourseId")
    public List<Klass> findByCourseId(Long courseId){
        return klassService.findByCourseId(courseId);
    }

    /**
     * 查找从课程的队伍所属班级
     * @param courseId
     * @param teamId
     * @return
     */
    @GetMapping(value = "course/{courseId}/klassId")
    public Long findSubCourseTeamKlassId(@PathVariable("courseId") Long courseId,Long teamId){
        List<TeamStudent> teamStudentList=teamService.findStudentByTeamId(teamId);
        List<Long> klassList=new ArrayList<Long>();
        for(TeamStudent teamStudent:teamStudentList){
            Long classId=klassService.findKlassIdByCourseIdAndStudentId(teamStudent.getStudentId(),courseId);
            klassList.add(classId);
        }
        HashMap<Long,Integer> hashMap=new HashMap<Long, Integer>();
        for (Long id:klassList){
            hashMap.put(id,0);
        }
        for(Long id:klassList){
            for (Map.Entry<Long,Integer> arg:hashMap.entrySet()){
                if (id.equals(arg.getKey())){
                    hashMap.put(arg.getKey(),arg.getValue()+1);
                }
            }
        }
        Long classId;
        Integer max;
        Iterator it=hashMap.keySet().iterator();
        String str=String.valueOf(it.next());
        classId=Long.valueOf(str);
        max=hashMap.get(classId);
        System.out.println("The First is "+classId+" And number is "+max);
        boolean sameFlag=true;
        for(Map.Entry<Long,Integer> arg:hashMap.entrySet()){
            if(arg.getValue()>max){
                sameFlag=false;
                classId=arg.getKey();
                max=arg.getValue();
            }
        }
        System.out.println("Now classId is "+classId+" the number is "+max+" And flag of AllSame is "+sameFlag);
        if (sameFlag){
            List<Klass> klasses=klassService.findByCourseId(courseId);
            HashMap<Long,Integer> hashMap1=new HashMap<>();
            for (Klass klass:klasses){
                Long id=klass.getId();
                Integer num=teamService.numberOfKlassTeam(id);
                hashMap1.put(id,num);
            }
            Long cId;
            Integer min;
            Iterator iterator=hashMap1.keySet().iterator();
            String temp=String.valueOf(iterator.next());
            cId=Long.valueOf(temp);
            min=hashMap1.get(cId);
            for (Map.Entry<Long,Integer> a:hashMap1.entrySet()){
                System.out.println("the key is "+a.getKey()+" the value is "+a.getValue());
                if(min>a.getValue()){
                    min=a.getValue();
                    cId=a.getKey();
                }
            }
            System.out.println("The classId is "+cId+" the number is "+min);
            classId=cId;
        }
        return classId;
    }
    @GetMapping(value = "{classId}")
    public int numberOfKlassTeam(@PathVariable("classId") Long klassId){
        return teamService.numberOfKlassTeam(klassId);
    }

    /**
     * 查找一个课程的各班小组数
     * @param courseId
     * @return
     */
    @GetMapping(value = "{courseId}/dis")
    public HashMap<Long,Integer> teamNumberOfAllKlass(@PathVariable("courseId") Long courseId){
        List<Klass> klassList=klassService.findByCourseId(courseId);
        HashMap<Long,Integer> hashMap=new HashMap<Long, Integer>();
        for (Klass klass:klassList){
            Long klassId=klass.getId();
            Integer num=teamService.numberOfKlassTeam(klassId);
            hashMap.put(klassId,num);
        }
        Long classId;
        Integer min;
        Iterator iterator=hashMap.keySet().iterator();
        String temp=String.valueOf(iterator.next());
        classId=Long.valueOf(temp);
        min=hashMap.get(classId);
        for (Map.Entry<Long,Integer> arg:hashMap.entrySet()){
            if(arg.getValue()<min){
                min=arg.getValue();
                classId=arg.getKey();
            }
        }
        System.out.println("The min class is "+classId+" the number is "+min);
        return hashMap;
    }

    /**
     * 查找班级
     * @param courseId
     * @param studentId
     * @return
     */
    @GetMapping(value = "findKlass")
    public Long findByCourseIdAndStudentId(Long courseId,Long studentId){
        return klassService.findKlassIdByCourseIdAndStudentId(courseId,studentId);
    }
}
