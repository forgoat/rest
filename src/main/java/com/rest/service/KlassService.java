package com.rest.service;

import com.rest.mapper.KlassMapper;
import com.rest.mapper.KlassStudentMapper;
import com.rest.mapper.KlassTeamMapper;
import com.rest.mapper.TeamStudentMapper;
import com.rest.po.Klass;
import com.rest.po.KlassStudent;
import com.rest.po.KlassTeam;
import com.rest.entity.TeamStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KlassService {
    @Autowired
    private KlassMapper klassMapper;
    @Autowired
    private KlassStudentMapper klassStudentMapper;
    @Autowired
    private KlassTeamMapper klassTeamMapper;
    @Autowired
    private TeamStudentMapper teamStudentMapper;
    public Long findKlassIdByCourseIdAndStudentId(Long studentId,Long courseId){
        Long id= klassStudentMapper.findKlass(courseId,studentId);
        System.out.println(id);
        Long id2= klassStudentMapper.findKlass(studentId,courseId);
        System.out.println(id2);
        return klassStudentMapper.findKlass(studentId,courseId);
    }
    public KlassStudent findByCourseIdAndStudentId(Long courseId,Long studentId){
        return klassStudentMapper.findByCourseIdAndStudentId(courseId,studentId);
    }
    public int deleteById(Long id){
        return klassMapper.deleteById(id);
    }
    public List<Klass> findByCourseId(Long courseId)
    {
        return klassMapper.findByCourseId(courseId);
    }
    public int saveKlass(Klass klass){
        return klassMapper.saveKlass(klass);
    }
    public Long findTeam(Long courseId,Long studentId)
    {
        return klassStudentMapper.findTeam(courseId,studentId);
    }
    public Long findSubCourseTeamKlassId(Long courseId, Long teamId){
        System.out.println("start ");
        List<TeamStudent> teamStudentList= teamStudentMapper.findByTeamId(teamId);
        List<Long> klassList=new ArrayList<Long>();
        for(TeamStudent teamStudent:teamStudentList){
            Long studentId=teamStudent.getStudentId();
            System.out.println(klassStudentMapper.findByCourseIdAndStudentId(courseId,studentId).getKlassId());
            klassList.add(klassStudentMapper.findKlass(courseId,studentId));
        }
        System.out.println("Find students' classId");
        HashMap<Long,Integer> hashMap=new HashMap<Long, Integer>();
        for (Long id:klassList){
            hashMap.put(id,0);
        }
        for (Map.Entry<Long,Integer> b:hashMap.entrySet()){
            System.out.println("The key is "+b.getKey()+" the value is "+b.getValue());
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
            List<Klass> klasses= klassMapper.findByCourseId(courseId);
            HashMap<Long,Integer> hashMap1=new HashMap<>();
            for (Klass klass:klasses){
                Long id=klass.getId();
                List<KlassTeam> klassTeamList= klassTeamMapper.findByKlassId(id);
                Integer num=klassTeamList.size();
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

    /**
     * 通过klass找所有队伍
     * @param klassId
     * @return
     */
    public List<Long> findTeamList(Long klassId){
        List<Long> teamList=new ArrayList<Long>();
        List<KlassTeam> klassTeamList= klassTeamMapper.findByKlassId(klassId);
        for(KlassTeam klassTeam:klassTeamList){
            Long teamId=klassTeam.getTeamId();
            teamList.add(teamId);
        }
        return teamList;
    }
}
