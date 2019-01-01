package com.rest.service;

import com.rest.dao.KlassStudentDao;
import com.rest.dao.StudentDao;
import com.rest.entity.Student;
import com.rest.exception.ImportExcelException;
import com.rest.exception.ResultEnum;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

@Service
public class ImportExcelService {
    private final static String XLS = "xls";
    public static final String XLSX = "xlsx";
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private KlassStudentDao klassStudentDao;

    private final static Logger logger = LoggerFactory.getLogger(ImportExcelService.class);



    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public Integer importExcel(MultipartFile myFile,Long klassId,Long courseId) {
        //1.  使用HSSFWorkbook 打开或者创建 “Excel对象”
        //2.  用HSSFWorkbook返回对象或者创建sheet对象
        //3.  用sheet返回行对象，用行对象得到Cell对象
        //4.  对Cell对象进行读写
        /*
        * 还未完成：（数据库未定）
        * 系统同时删除该班级与未出现在新名单的学生账户的所有关联
        * */

        System.out.println("myFile:"+myFile+" klassId:"+klassId+"  courseId:"+courseId);
        studentDao.deleteAll();

        List<Student> studentList = new ArrayList<>();
        Workbook workbook = null;
        String fileName = myFile.getOriginalFilename();//  获取文件名
        logger.info("【fileName】{}",fileName);
        if (fileName.endsWith(XLS))
        {
            try {
                workbook = new HSSFWorkbook(myFile.getInputStream());//  2003版本

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(fileName.endsWith(XLSX)){
            try {
                workbook = new XSSFWorkbook(myFile.getInputStream());//  2007版本
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new ImportExcelException(ResultEnum.FILE_IS_NOT_EXCEL); // 文件不是Excel文件
        }
        Sheet sheet = workbook.getSheet("sheet1");
        int rows = sheet.getLastRowNum();
        logger.info("【rows】{}",rows);
        if(rows == 0){
            throw  new ImportExcelException(ResultEnum.DATA_IS_NULL);// 数据为空 请填写数据
        }
        long startTime = System.currentTimeMillis();
        for(int i = 2;i<= rows+1;i++){
            Row row = sheet.getRow(i);

            if(row !=null){

                Student student=new Student();

                //  account
                String account = getCellValue(row.getCell(0));
                student.setAccount(account);

                // studentName
                String studentName = getCellValue(row.getCell(1));
                student.setStudentName(studentName);

                //System.out.println(JSON.toJSON(tbagent));
                //tbagentMapper.insert(tbagent);
                studentList.add(student);
                //logger.info("插入数据完成");
            }

        }
        if(studentList.size()==0) return 0;
        studentDao.batchSaveStudent(studentList);  //  批量插入 五秒完成

        List<Long> studentIdList=new ArrayList<>();//查出所有插入学生的ID
        int num=studentList.size();
        for(Student student:studentList){
            studentIdList.add(studentDao.queryStudentIdByAccount(student.getAccount()));
        }
        for(Long studentId:studentIdList){//更新插入klass—student表
            klassStudentDao.insertStudent(klassId,studentId,courseId);
        }

        long endTime = System.currentTimeMillis();
        long totaltime = endTime - startTime;
        logger.info("【消耗时间为】{}",totaltime);  //  将近两万条数据 3秒解析完成
        //logger.info("【第一条数据为】{}",JSON.toJSON(studentList.get(0)));
        return rows;
    }

    public String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            //  字符串
            value = cell.getStringCellValue();
        }
        return value.trim();
    }

}
