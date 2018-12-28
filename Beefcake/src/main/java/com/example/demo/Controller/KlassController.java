package com.example.demo.Controller;

import com.example.demo.Dao.KlassDao;
import com.example.demo.Service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class KlassController {
    @Autowired
    KlassDao klassDao;
    @Autowired
    KlassService klassService;

    @RequestMapping(value = "/class/{classId}" ,method = RequestMethod.PUT)  //导入学生名单
    public void inputStudent(@PathVariable("classId")Long klassId,@RequestParam("courseId")Long courseId,@RequestParam("student") MultipartFile file)throws Exception
    {
        klassService.inputStudent(file,courseId,klassId);
    }
    @RequestMapping(value = "/class/{classId}",method = RequestMethod.DELETE)
    public void deleteKlass(@PathVariable("classId")Long klassId){
        klassDao.deleteKlass(klassId);
    }
}
