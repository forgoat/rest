package com.rest.service;

import com.rest.dao.CourseDao;
import com.rest.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public List<Course> queryCourseByStudentId(BigInteger id){
        return courseDao.queryCourseByStudentId(id);
    }

}
