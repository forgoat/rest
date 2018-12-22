package com.rest.service;

import com.rest.dao.KlassDao;
import com.rest.dao.KlassSeminarDao;
import com.rest.dao.SeminarDao;
import com.rest.entity.Klass;
import com.rest.entity.Klass_seminar;
import com.rest.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
