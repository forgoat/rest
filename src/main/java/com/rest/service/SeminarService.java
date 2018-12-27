package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.Klass;
import com.rest.entity.KlassSeminar;
import com.rest.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SeminarService {
    @Autowired
    private SeminarDao seminarDao;
    @Autowired
    private TeamStudentDao teamStudentDao;
    @Autowired
    private KlassSeminarDao klassSeminarDao;
    @Autowired
    private KlassTeamDao klassTeamDao;
    @Autowired
    private KlassDao klassDao;

    public KlassSeminar findKlassSeminarById(Long klassSeminarId){
        return klassSeminarDao.findKlassSeminarById(klassSeminarId);
    }
    public int save(Seminar seminar){
        return seminarDao.save(seminar);
    }
    public List<KlassSeminar> findClass(Long seminarId){
        return klassSeminarDao.findBySeminar(seminarId);
    }
    public int saveKlassSeminar(KlassSeminar klassSeminar){
        return klassSeminarDao.save(klassSeminar);
    }
    public Seminar findById(Long id){
        return seminarDao.findById(id);
    }
    public int deleteSeminar(Long id){
        return seminarDao.delete(id);
    }
    public int updateInfo(Long seminarId, Long courseId, Long roundId, String seminarName, String introducation, Integer maxTeam, Integer isVisible, Integer seminarSerial, Date enrollStartTime, Date enrollEndTime){
        return seminarDao.updateSelective(seminarId,courseId,roundId,seminarName,introducation,maxTeam,isVisible,seminarSerial,enrollStartTime,enrollEndTime);
    }
    public int deleteKlassSeminar(Long id){
        return klassSeminarDao.delete(id);
    }
    public int changeStatus(Long seminarId,Long classId,Integer status){
        return klassSeminarDao.changeStatus(seminarId,classId,status);
    }
    public int changeddl(Long seminarId,Long classId,Date reportDdl){
        return klassSeminarDao.changeddl(seminarId,classId,reportDdl);
    }
    public List<Seminar> findByRoundId(Long roundId)
    {
        return seminarDao.findByRoundId(roundId);
    }
    public KlassSeminar findKlassSeminar(Long klassId,Long seminarId){
        return klassSeminarDao.findByKlassIdAndSeminarId(klassId,seminarId);
    }
    public List<Seminar> findAll(){
        return seminarDao.findAll();
    }
    public List<Seminar> findByCourseId(Long courseId){
        return seminarDao.findByCourseId(courseId);
    }
    public List<Seminar> findByCourseIdAndRoundId(Long courseId,Long roundId){
        return seminarDao.findByCourseIdAndRoundId(courseId,roundId);
    }
    public int startKlassSeminar(Long klassSeminarId){
        System.out.println(klassSeminarId+" "+klassSeminarDao.startSeminar(klassSeminarId));
        return klassSeminarDao.startSeminar(klassSeminarId);
    }
    public int deleteBySeminarId(Long seminarId){
        return klassSeminarDao.deleteBySeminarId(seminarId);
    }


    /**
     * 通过studentId courseId seminarId 查找 KlassSeminarId
     * @param studentId
     * @param courseId
     * @param seminarId
     * @return
     */
    public Long queryKlassSeminarId(Long studentId,Long courseId,Long seminarId){
        Long teamId=teamStudentDao.findByStudentId(studentId);
        System.out.println("teamId:"+teamId);

        List<Long> klassIdList=klassTeamDao.findByTeamId(teamId);
        System.out.println("klassIdList:"+klassIdList);

        List<Klass> klassList=klassDao.findByCourseId(courseId);
        System.out.println("klassList:"+klassList);

        Long klassId=null;
        for(Long a: klassIdList){
            System.out.println("a:"+a);
            for(Klass b:klassList){
                System.out.println("b:"+b);
                if(a==b.getId()){
                    klassId=a;
                    System.out.println("klassId"+a);
                    break;
                }
            }
            if(klassId!=null) break;
        }
        Long klassSeminarId=klassSeminarDao.queryKlassSeminarIdByKlassIdAndSeminarId(klassId,seminarId);
        return klassSeminarId;
    }

}


