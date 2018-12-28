package com.rest.service;

import com.rest.dao.KlassDao;
import com.rest.dao.KlassStudentDao;
import com.rest.dao.KlassTeamDao;
import com.rest.dao.TeamStudentDao;
import com.rest.entity.Klass;
import com.rest.entity.KlassStudent;
import com.rest.entity.KlassTeam;
import com.rest.entity.TeamStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KlassService {
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private KlassStudentDao klassStudentDao;
    @Autowired
    private KlassTeamDao klassTeamDao;
    @Autowired
    private TeamStudentDao teamStudentDao;
    public Long findKlassIdByCourseIdAndStudentId(Long studentId,Long courseId){
        Long id=klassStudentDao.findKlass(courseId,studentId);
        System.out.println(id);
        Long id2=klassStudentDao.findKlass(studentId,courseId);
        System.out.println(id2);
        return klassStudentDao.findKlass(studentId,courseId);
    }
    public KlassStudent findByCourseIdAndStudentId(Long courseId,Long studentId){
        return klassStudentDao.findByCourseIdAndStudentId(courseId,studentId);
    }
    public int deleteById(Long id){
        return klassDao.deleteById(id);
    }
    public List<Klass> findByCourseId(Long courseId)
    {
        return klassDao.findByCourseId(courseId);
    }
    public int saveKlass(Klass klass){
        return klassDao.saveKlass(klass);
    }
    public Long findTeam(Long courseId,Long studentId)
    {
        return klassStudentDao.findTeam(courseId,studentId);
    }
    public Long findSubCourseTeamKlassId(Long courseId, Long teamId){
        System.out.println("start ");
        List<TeamStudent> teamStudentList=teamStudentDao.findByTeamId(teamId);
        List<Long> klassList=new ArrayList<Long>();
        for(TeamStudent teamStudent:teamStudentList){
            Long studentId=teamStudent.getStudentId();
            System.out.println(klassStudentDao.findByCourseIdAndStudentId(courseId,studentId).getKlassId());
            klassList.add(klassStudentDao.findKlass(courseId,studentId));
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
            List<Klass> klasses=klassDao.findByCourseId(courseId);
            HashMap<Long,Integer> hashMap1=new HashMap<>();
            for (Klass klass:klasses){
                Long id=klass.getId();
                List<KlassTeam> klassTeamList=klassTeamDao.findByKlassId(id);
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
        List<KlassTeam> klassTeamList=klassTeamDao.findByKlassId(klassId);
        for(KlassTeam klassTeam:klassTeamList){
            Long teamId=klassTeam.getTeamId();
            teamList.add(teamId);
        }
        return teamList;
    }
}
