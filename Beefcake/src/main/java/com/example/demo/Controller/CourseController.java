package com.example.demo.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @RequestMapping(value = "/couse",method = RequestMethod.POST)//创建课程
    public void createCourse()//参数没写
    {

    }

    @RequestMapping(value = "/course",method = RequestMethod.GET)
    public void getCourse()
    {

    }

    @RequestMapping(value = "/course/{courseId}",method = RequestMethod.GET)
    public void getCourseById(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}",method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/score",method = RequestMethod.GET)
    public void getScore(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/team",method = RequestMethod.GET)
    public void getTeam(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/noTeam",method = RequestMethod.GET)
    public void getNoTeam(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/class",method = RequestMethod.GET)
    public void getClass(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/class/{classId}",method = RequestMethod.POST)//创建班级
    public void createClass(@PathVariable("courseId")int courseId,@PathVariable("classId")int classId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/class/{classId}",method = RequestMethod.PUT)//上传学生名单
    public void loadStudent(@PathVariable("courseId")int courseId,@PathVariable("classId")int classId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/share",method = RequestMethod.GET)//获取所有共享
    public void getShare(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/share/{shareId}",method = RequestMethod.DELETE)//获取所有共享
    public void deleteShare(@PathVariable("courseId")int courseId,@PathVariable("shareId")int shareId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/sharerequest",method = RequestMethod.POST)//获取所有共享
    public void createShare(@PathVariable("courseId")int courseId)
    {

    }


}
