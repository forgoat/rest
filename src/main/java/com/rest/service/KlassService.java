package com.rest.service;

import com.rest.dao.KlassDao;
import com.rest.dao.KlassStudentDao;
import com.rest.entity.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlassService {
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private KlassStudentDao klassStudentDao;

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
    public Long findTeam(Long courseId,Long studentId){
        return klassStudentDao.findTeam(courseId,studentId);
    }
}
