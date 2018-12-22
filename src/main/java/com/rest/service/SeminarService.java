package com.rest.service;

import com.rest.dao.KlassDao;
import com.rest.dao.KlassSeminarDao;
import com.rest.dao.SeminarDao;
import com.rest.entity.Klass;
import com.rest.entity.Klass_seminar;
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
    private KlassSeminarDao klassSeminarDao;
    public int save(Seminar seminar){
        return seminarDao.save(seminar);
    }
    public List<Klass_seminar> findClass(Long seminarId){
        return klassSeminarDao.findBySeminar(seminarId);
    }
    public int saveKlassSeminar(Klass_seminar klass_seminar){
        return klassSeminarDao.save(klass_seminar);
    }
    public Seminar findById(Long id){
        return seminarDao.findById(id);
    }
    public int deleteSeminar(Long id){
        return seminarDao.delete(id);
    }
    public int updateInfo(Long seminarId, Long course_id, Long round_id, String seminar_name, String introducation, Integer max_team, Integer is_visible, Integer seminar_serial, Date enroll_start_time, Date enroll_end_time){
        return seminarDao.updateSelective(seminarId,course_id,round_id,seminar_name,introducation,max_team,is_visible,seminar_serial,enroll_start_time,enroll_end_time);
    }
    public int deleteKlassSeminar(Long id){
        return klassSeminarDao.delete(id);
    }
    public int changeStatus(Long seminar_id,Long class_id,Integer status){
        return klassSeminarDao.changeStatus(seminar_id,class_id,status);
    }
    public int changeddl(Long seminar_id,Long class_id,Date report_ddl){
        return klassSeminarDao.changeddl(seminar_id,class_id,report_ddl);
    }
}
