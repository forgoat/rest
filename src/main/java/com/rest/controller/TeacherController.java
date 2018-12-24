package com.rest.controller;
import com.rest.entity.Teacher;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 获取老师
     * @return
     */
    @GetMapping(value = "")
    public List<Teacher> teacherList() {
        return teacherService.teacherList();
    }

    /**
     * 创建老师
     * @param teacher
     * @return
     */
    @PostMapping(value = "")
    public ResponseEntity<Object> createTeacher(Teacher teacher) {
        if(teacherService.createTeacher(teacher)==1){
            return new ResponseEntity<Object>(teacher, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 激活老师账号
     * @param id
     * @param password
     * @return
     */
    @PutMapping(value = "active")
    public ResponseEntity<Object> actival(Long id,String password){
        if(teacherService.actival(id,password)==1){
            Teacher teacher=teacherService.findById(id);
            return new ResponseEntity<Object>(teacher,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 删除老师（不完全版本）
     * @param teacherId
     * @return
     */
    @DeleteMapping(value = "{teacherId}")
    public HttpStatus delete(@PathVariable("teacherId")Long teacherId){
        if(teacherService.delete(teacherId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * 老师修改密码
     * @param teacherId
     * @param password
     * @return
     */
    @PutMapping(value = "{teacherId}/password")
    public ResponseEntity<Object> changePassword(@PathVariable("teacherId")Long teacherId,String password){
        if(teacherService.updatePassword(teacherId,password)==1){
            Teacher teacher=teacherService.findById(teacherId);
            return new ResponseEntity<Object>(teacher,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 动态查找老师
     * @param account
     * @param teacherName
     * @return
     */
    @GetMapping(value = "searchteacher")
    public ResponseEntity<List<Teacher>> search(String account,String teacherName){
        List<Teacher> teacherList=teacherService.search(account,teacherName);
        if(!teacherList.isEmpty()){
            return new ResponseEntity<List<Teacher>>(teacherList,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<Teacher>>(teacherList,HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 动态修改老师
     * @param teacherId
     * @param account
     * @param email
     * @param teacherName
     * @return
     */
    @PutMapping(value = "{teacherId}/information")
    public ResponseEntity<Object> updateInfo(@PathVariable("teacherId")Long teacherId,String account,String email,String teacherName){
        if(teacherService.updateInfo(teacherId,account,email,teacherName)==1){
            Teacher teacher=teacherService.findById(teacherId);
            return new ResponseEntity<Object>(teacher,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
        }
    }
}
