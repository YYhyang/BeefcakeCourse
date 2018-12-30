package com.example.demo.controller;

import com.example.demo.dao.KlassDao;
import com.example.demo.entity.ClassEntity;
import com.example.demo.service.KlassService;
import com.example.demo.vo.ClassInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class KlassController {
    @Autowired
    KlassDao klassDao;
    @Autowired
    KlassService klassService;

    @RequestMapping(value = "/class/{classId}" ,method = RequestMethod.PUT)  //导入学生名单
    public boolean inputStudent(@PathVariable("classId")Long klassId,@RequestParam("courseId")Long courseId,@RequestParam("student") MultipartFile file)throws Exception
    {
        klassService.inputStudent(file,courseId,klassId);
        return true;
    }
    @RequestMapping(value = "/class/{classId}",method = RequestMethod.DELETE)
    public void deleteKlass(@PathVariable("classId")Long klassId){
        klassDao.deleteKlass(klassId);
    }
    @RequestMapping(value = "/class/{classId}",method = RequestMethod.GET)
    public ClassInfoVO getClass(@PathVariable("classId")Long classId){
        ClassEntity klass=klassDao.getKlassById(classId);
        ClassInfoVO vo=new ClassInfoVO();
        vo.setKlass_location(klass.getKlass_location());
        vo.setKlass_time(klass.getKlass_time());
        vo.setTeacher_name(klass.getCourse().getTeacher().getTeacher_name());
        return vo;
    }
}
