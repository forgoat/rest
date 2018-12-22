package com.rest.service;

import com.rest.dao.KlassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KlassService {
    @Autowired
    private KlassDao klassDao;
    public int deleteById(Long id){
        return klassDao.deleteById(id);
    }
}
