package com.example.demo.Controller;

import com.example.demo.Dao.KlassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KlassController {
    @Autowired
    KlassDao klassDao;

    //@RequestMapping(value = "/class/{classId}" ,method = RequestMethod.PUT)  //导入学生名单

    @RequestMapping(value = "/class/{classId}",method = RequestMethod.DELETE)
    public void deleteKlass(@PathVariable("classId")Long klassId){
        klassDao.deleteKlass(klassId);
    }
}
