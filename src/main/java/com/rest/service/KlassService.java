package com.rest.service;

import com.rest.dao.KlassDao;
import com.rest.entity.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlassService {
    @Autowired
    private KlassDao klassDao;
    public int deleteById(Long id){
        return klassDao.deleteById(id);
    }
    public List<Klass> findByCourseId(Long courseId){
        return klassDao.findByCourseId(courseId);
    }
}
