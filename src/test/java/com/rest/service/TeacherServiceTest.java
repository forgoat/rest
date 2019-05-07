package com.rest.service;

import com.rest.dao.TeacherDao;
import com.rest.entity.Teacher;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
    @MockBean
    private TeacherDao teacherDao;


    @Test
    public void createTeacher() {
        //正例
        Teacher teacher=new Teacher();
        teacher.setAccount("324578");
        teacher.setTeacherName("test");
        teacher.setEmail("24564");
        int resultPos=teacherService.createTeacher(teacher);
        System.out.println(teacherDao.save(teacher));
        //反例
        int resultNeg=teacherService.createTeacher(null);

        Assertions.assertThat(resultPos).isEqualTo(1);
        Assertions.assertThat(resultNeg).isEqualTo(0);
    }


    @Test
    public void updatePassword() {
        long idPos=3;
        long idNeg=20;
        int resultPos=teacherService.updatePassword(idPos,"1234567");
        int resultNeg=teacherService.updatePassword(idNeg,"12453465");
        Assertions.assertThat(resultPos).isEqualTo(1);
        Assertions.assertThat(resultNeg).isEqualTo(0);
    }

    @Test
    public void updateEmail() {
        long idPos=3;
        long idNeg=20;
        int resultPos=teacherService.updateEmail(idPos,"245646");
        int resultNeg=teacherService.updateEmail(idNeg,"54637");
        Assertions.assertThat(resultPos).isEqualTo(1);
        Assertions.assertThat(resultNeg).isEqualTo(0);
    }

    @Test
    public void actival() {
        long idPos=3;
        long idNeg=20;
        int resultPos=teacherService.actival(idPos,"12234");
        int resultNeg=teacherService.actival(idNeg,"3567");
        Assertions.assertThat(resultPos).isEqualTo(1);
        Assertions.assertThat(resultNeg).isEqualTo(-1);
    }

    @Test
    public void delete() {
        long idPos=4;
        long idNeg=20;
        int resultPos=teacherService.delete(idPos);
        int resultNeg=teacherService.delete(idNeg);
        Assertions.assertThat(resultPos).isEqualTo(1);
        Assertions.assertThat(resultNeg).isEqualTo(0);
    }

    @Test
    public void search() {
        List<Teacher> teacherListPos=teacherService.search(null,"邱明");
        int resultPos=teacherListPos.size();
        List<Teacher> teacherListNeg=teacherService.search(null,null);
        int resultNeg=teacherListNeg.size();
        Assertions.assertThat(resultPos).isEqualTo(1);
        Assertions.assertThat(resultNeg).isEqualTo(0);
    }

    @Test
    public void updateInfo() {
        long idPos=3;
        long idNeg=20;
        int resultPos=teacherService.updateInfo(idPos,"5364","547","457");
        int resultNeg=teacherService.updateInfo(idNeg,"574","74","47");
        Assertions.assertThat(resultPos).isEqualTo(1);
        Assertions.assertThat(resultNeg).isEqualTo(0);
    }
}