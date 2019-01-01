package com.rest.controller;

import com.rest.service.AttendanceService;
import com.rest.service.ImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

@RestController
public class FileUploadDownloadController {
    @Autowired
    ImportExcelService importExcelService;
    @Autowired
    AttendanceService attendanceService;

//    //  Excel导入数据到数据库
//    @RequestMapping("/fileImport")
//    @ResponseBody
//    public String importExcel(@RequestParam("fileName1") MultipartFile myFile){
//
//        Integer nums = importExcelService.importExcel(myFile);
//
//        return "导入成功";
//    }


    /*
     * 获取s_SemSubmit.html页面
     */
    @RequestMapping("/file")
    public String file(){
        return "s_SemSubmit.html";
    }

    /**
     * 实现PPT上传
     * */
    @PostMapping("/PPTUpload")
    public void PPTUpload(@RequestParam("fileName1") MultipartFile file1,@RequestParam("attendanceId")Long attendanceId,HttpServletResponse response)throws IOException{
        System.out.println("hello");
        System.out.println(attendanceId);
        if(file1.isEmpty()){
            response.sendRedirect("s_SemInfo.html");
        }
        String fileName1 = file1.getOriginalFilename();
        int size = (int) file1.getSize();
        System.out.println(fileName1 + "-->" + size);

        Random ra =new Random();
        //之后换成服务器文件上传的目录
        String path = "/root/sysFile" ;
        String fileName=ra.toString()+fileName1;
        System.out.println(path + "/" + fileName);
        File dest = new File(path + "/" + fileName);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {

            String a= "/root/sysFile";
            //保存文件
            file1.transferTo(dest);
            attendanceService.savePPT(attendanceId,fileName,path);
            response.sendRedirect("s_SemInfoW.html");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.sendRedirect("s_SemInfoW.html");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.sendRedirect("s_SemInfoW.html");
        }
    }

    /**
     * 实现报告上传
     * */
    @RequestMapping("/ReportUpload")
    @ResponseBody
    public String ReportUpload(@RequestParam("fileName2") MultipartFile file2,Long attendanceId){
        if(file2.isEmpty()){
            return "false";
        }
        String fileName2 = file2.getOriginalFilename();
        int size = (int) file2.getSize();
        Random ra =new Random();
        String fileName=ra.toString()+fileName2;
        System.out.println(fileName + "-->" + size);

        //之后换成服务器文件上传的目录
        String path = "/root/sysFile";
        File dest = new File(path + "/" + fileName);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file2.transferTo(dest);
            attendanceService.saveReport(attendanceId,fileName,path);
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }


    /*
     * 获取t_pc_download_main.html页面
     */
    @RequestMapping("/downloadPage")
    public String downloadPage(){
        return "t_pc_download.html";
    }

    /**
     * 实现Spring Boot 的PPT下载功能，映射网址为/downloadPPT
     * */
    @RequestMapping("/downloadPPT")
    public String downloadPPT(HttpServletRequest request,
                               HttpServletResponse response,
                               Long attendanceId) throws UnsupportedEncodingException {

        // 获取指定目录（服务器文件地址）
        File scFileDir = new File("/root/sysFile");
        File TrxFiles[] = scFileDir.listFiles();
        // 指定目录下的第一个文件
        int xx;
        for(xx=0;xx<TrxFiles.length;xx++){
            if(TrxFiles[xx].getName().equals(attendanceService.getFile(attendanceId)))break;
        }
        //下载的文件名
        String fileName = TrxFiles[xx].getName();

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径（同上路径）
            String realPath = "C:/Users/74051/Desktop/sys";
            File file = new File(realPath, fileName);

            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the song failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     * 实现Spring Boot 的report下载功能，映射网址为/downloadPPT
     * */
    @RequestMapping("/downloadReport")
    public String downloadReport(HttpServletRequest request,
                               HttpServletResponse response,
                               Long attendanceId) throws UnsupportedEncodingException {

        // 获取指定目录（服务器文件地址）
        File scFileDir = new File("/root/sysFile");
        File TrxFiles[] = scFileDir.listFiles();
        // 指定目录下的第一个文件
        int xx;
        for(xx=0;xx<TrxFiles.length;xx++){
            if(TrxFiles[xx].getName().equals(attendanceService.getFile(attendanceId)))break;
        }
        //下载的文件名
        String fileName = TrxFiles[xx].getName();

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径（同上路径）
            String realPath = "/root/sysFile1";
            File file = new File(realPath, fileName);

            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the song failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
