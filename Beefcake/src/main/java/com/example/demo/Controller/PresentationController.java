package com.example.demo.Controller;

import com.example.demo.Entity_renew.AttendanceEntity;
import com.example.demo.Mapper.AttendanceMapper;
import com.example.demo.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PresentationController {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @RequestMapping(value="/attendance/{attendanceId}",method = RequestMethod.PUT)   //修改报名展示
    public boolean putPresentation(@PathVariable("attendanceId")Long attendanceId,@RequestParam("presentationOrder")Integer presentationOrder )
    {
        return attendanceMapper.changeOrder(attendanceId,presentationOrder);
    }

    @RequestMapping(value="/attendance/{attendanceId}",method = RequestMethod.DELETE)   //取消报名展示
    public boolean deletePresentation(@PathVariable("attendanceId")Long attendanceId )
    {
        return attendanceMapper.deleteSignUp(attendanceId);
    }

    @RequestMapping(value="/presentation/{presentationId}/report",method = RequestMethod.POST)   //上传讨论课报告
    public void postReport(@RequestParam("presentationId")Integer presentationId,@RequestParam("file")String file )
    {

    }

    @RequestMapping(value="/presentation/{presentationId}/report",method = RequestMethod.PUT)   //重传讨论课报告
    public void putReport(@RequestParam("presentationId")Integer presentationId,@RequestParam("file")String file )
    {

    }

    @RequestMapping(value="/presentation/{presentationId}/report",method = RequestMethod.GET)   //下载讨论课报告
    public void downReport(@RequestParam("presentationId")Integer presentationId )
    {

    }

    @RequestMapping(value="/presentation/{presentationId}/powerpoint",method = RequestMethod.POST)   //上传讨论课PPT
    public void postPPt(@RequestParam("presentationId")Integer presentationId,@RequestParam("file")String file )
    {

    }

    @RequestMapping(value="/presentation/{presentationId}/powerpoint",method = RequestMethod.PUT)   //重传讨论课PPT
    public void putPPt(@RequestParam("presentationId")Integer presentationId,@RequestParam("file")String file )
    {

    }

    @RequestMapping(value="/presentation/{presentationId}/powerpoint",method = RequestMethod.DELETE)   //下载讨论课PPT
    public void downPPt(@RequestParam("presentationId")Integer presentationId )
    {

    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/powerpoint/downAll",method = RequestMethod.GET)   //下载此次讨论课所有的PPT
    public void downAllPPt(@RequestParam("seminarId")Integer seminarId, @RequestParam("classId")Integer classId)
    {

    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/report/downAll",method = RequestMethod.GET)   //下载此次讨论课所有的报告
    public void downAllreport(@RequestParam("seminarId")Integer seminarId, @RequestParam("classId")Integer classId)
    {

    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/presentation",method = RequestMethod.GET)   //获得讨论课展示信息
    public List<AttendanceEntity> getAllpresentation(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId)
    {
        return attendanceService.findAllTeam(seminarId, classId);
    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/presentation",method = RequestMethod.POST)   //报名展示
    public boolean postpresentation(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId,@RequestParam("presentationOrder")int presentationOrder,@RequestParam("teamId")Long teamId)
    {
        return attendanceService.signUpAttendence(seminarId, classId, presentationOrder, teamId);
    }

}


