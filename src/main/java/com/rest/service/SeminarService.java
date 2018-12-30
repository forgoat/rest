package com.rest.service;

import com.rest.mapper.*;
import com.rest.po.Klass;
import com.rest.po.KlassSeminar;
import com.rest.po.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SeminarService {
    @Autowired
    private SeminarMapper seminarMapper;
    @Autowired
    private TeamStudentMapper teamStudentMapper;
    @Autowired
    private KlassSeminarMapper klassSeminarMapper;
    @Autowired
    private KlassTeamMapper klassTeamMapper;
    @Autowired
    private KlassMapper klassMapper;

    public KlassSeminar findKlassSeminarById(Long klassSeminarId){
        return klassSeminarMapper.findKlassSeminarById(klassSeminarId);
    }
    public int save(Seminar seminar){
        return seminarMapper.save(seminar);
    }
    public List<KlassSeminar> findClass(Long seminarId){
        return klassSeminarMapper.findBySeminar(seminarId);
    }
    public int saveKlassSeminar(KlassSeminar klassSeminar){
        return klassSeminarMapper.save(klassSeminar);
    }
    public Seminar findById(Long id){
        return seminarMapper.findById(id);
    }
    public int deleteSeminar(Long id){
        return seminarMapper.delete(id);
    }
    public int updateInfo(Long seminarId, Long courseId, Long roundId, String seminarName, String introducation, Integer maxTeam, Integer isVisible, Integer seminarSerial, Date enrollStartTime, Date enrollEndTime){
        return seminarMapper.updateSelective(seminarId,courseId,roundId,seminarName,introducation,maxTeam,isVisible,seminarSerial,enrollStartTime,enrollEndTime);
    }
    public int deleteKlassSeminar(Long id){
        return klassSeminarMapper.delete(id);
    }
    public int changeStatus(Long klassSeminarId,Integer status){
        return klassSeminarMapper.changeStatus(klassSeminarId,status);
    }
    public int changeddl(Long seminarId,Long classId,Date reportDdl){
        return klassSeminarMapper.changeddl(seminarId,classId,reportDdl);
    }
    public List<Seminar> findByRoundId(Long roundId)
    {
        return seminarMapper.findByRoundId(roundId);
    }
    public KlassSeminar findKlassSeminar(Long klassId,Long seminarId){
        return klassSeminarMapper.findByKlassIdAndSeminarId(klassId,seminarId);
    }
    public List<Seminar> findAll(){
        return seminarMapper.findAll();
    }
    public List<Seminar> findByCourseId(Long courseId){
        return seminarMapper.findByCourseId(courseId);
    }
    public List<Seminar> findByCourseIdAndRoundId(Long courseId,Long roundId){
        return seminarMapper.findByCourseIdAndRoundId(courseId,roundId);
    }
    public int startKlassSeminar(Long klassSeminarId){
        System.out.println(klassSeminarId+" "+ klassSeminarMapper.startSeminar(klassSeminarId));
        return klassSeminarMapper.startSeminar(klassSeminarId);
    }
    public int deleteBySeminarId(Long seminarId){
        return klassSeminarMapper.deleteBySeminarId(seminarId);
    }


    /**
     * 通过studentId courseId seminarId 查找 KlassSeminarId
     * @param studentId
     * @param courseId
     * @param seminarId
     * @return
     */
    public Long queryKlassSeminarId(Long studentId,Long courseId,Long seminarId){
        System.out.println(studentId+"  "+courseId+"  "+seminarId);
        Long teamId= teamStudentMapper.findByStudentId(studentId);
        System.out.println("------teamId:"+teamId);

        List<Long> klassIdList= klassTeamMapper.findByTeamId(teamId);
        System.out.println("------klassIdList:"+klassIdList);

        List<Klass> klassList= klassMapper.findByCourseId(courseId);
        System.out.println("------klassList:"+klassList);

        Long klassId=null;
        for(Long a: klassIdList){
            System.out.println("-----a:"+a);
            for(Klass b:klassList){
                System.out.println("-----b:"+b);
                if(a==b.getId()){
                    klassId=a;
                    System.out.println("------klassId"+a);
                    break;
                }
            }
            if(klassId!=null) break;
        }
        Long klassSeminarId= klassSeminarMapper.queryKlassSeminarIdByKlassIdAndSeminarId(klassId,seminarId);
        return klassSeminarId;
    }

}


