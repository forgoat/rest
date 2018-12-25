package com.rest.controller;

import com.rest.service.ImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class FileUploadDownloadController {
    @Autowired
    ImportExcelService importExcelService;
//
//    //  Excel导入数据到数据库
//    @RequestMapping("/PPTUpload")
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
    @RequestMapping("/PPTUpload")
    @ResponseBody
    public String PPTUpload(@RequestParam("fileName1") MultipartFile file1){
        if(file1.isEmpty()){
            return "false";
        }
        String fileName1 = file1.getOriginalFilename();
        int size = (int) file1.getSize();
        System.out.println(fileName1 + "-->" + size);

        //之后换成服务器文件上传的目录
        String path = "C:/Users/zjlnc/Desktop/新建文件夹 (5)" ;
        File dest = new File(path + "/" + fileName1);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {

            //保存文件
            file1.transferTo(dest);
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

    /**
     * 实现报告上传
     * */
    @RequestMapping("/ReportUpload")
    @ResponseBody
    public String ReportUpload(@RequestParam("fileName2") MultipartFile file2){
        if(file2.isEmpty()){
            return "false";
        }
        String fileName2 = file2.getOriginalFilename();
        int size = (int) file2.getSize();
        System.out.println(fileName2 + "-->" + size);

        //之后换成服务器文件上传的目录
        String path = "C:/Users/zjlnc/Desktop/新建文件夹 (5)";
        File dest = new File(path + "/" + fileName2);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file2.transferTo(dest);
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
     * 实现Spring Boot 的文件下载功能，映射网址为/download
     * */
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {

        // 获取指定目录（服务器文件地址）
        File scFileDir = new File("C:/Users/zjlnc/Desktop/新建文件夹 (5)");
        File TrxFiles[] = scFileDir.listFiles();
        // 指定目录下的第一个文件
        System.out.println(TrxFiles[0]);
        //下载的文件名
        String fileName = TrxFiles[0].getName();

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径（同上路径）
            String realPath = "C:/Users/zjlnc/Desktop/新建文件夹 (5)";
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
