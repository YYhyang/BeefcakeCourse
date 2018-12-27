package com.example.demo.Controller;

import com.example.demo.Entity.AttendanceEntity;
import com.example.demo.Mapper.AttendanceMapper;
import com.example.demo.Service.AttendanceService;
import com.example.demo.Service.utils.FileUtils;
import com.example.demo.VO.AttendanceInfoVO;
import com.example.demo.VO.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PresentationController {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @RequestMapping(value="/attendance/{attendanceId}",method = RequestMethod.PUT)   //修改报名展示（测试通过）
    public boolean putPresentation(@PathVariable("attendanceId")Long attendanceId,@RequestParam("presentationOrder")Integer presentationOrder )
    {
        return attendanceMapper.changeOrder(attendanceId,presentationOrder);
    }

    @RequestMapping(value="/attendance/{attendanceId}",method = RequestMethod.DELETE)   //取消报名展示（测试通过）
    public boolean deletePresentation(@PathVariable("attendanceId")Long attendanceId )
    {
        return attendanceMapper.deleteSignUp(attendanceId);
    }

    @RequestMapping(value="/attendance/{attendanceId}/report",method = RequestMethod.POST)   //上传讨论课报告
    public FileVO postReport(@PathVariable("attendanceId")Long attendanceId, @RequestParam("fileUpload") MultipartFile file) throws IOException {
        return attendanceService.uploadFile(attendanceId,file);
    }

    @RequestMapping(value="/attendance/{attendanceId}/report",method = RequestMethod.PUT)   //重传讨论课报告
    public FileVO putReport(@PathVariable("attendanceId")Long attendanceId, @RequestParam("fileUpload") MultipartFile file )throws IOException
    {
        return attendanceService.uploadFile(attendanceId,file);
    }

    @RequestMapping(value="/attendance/{attendanceId}/report",method = RequestMethod.GET)   //下载讨论课报告
    public void downReport(@RequestParam("attendanceId")Long attendanceId,HttpServletResponse response )throws IOException
    {
        AttendanceEntity attendanceEntity=attendanceMapper.getAttendanceById(attendanceId);
        if(attendanceEntity.getReport_url()==null){

        }
        String path=attendanceEntity.getReport_url();
        String name=attendanceEntity.getReport_name();
        FileUtils.downloadFile(response,path,name);
    }

    @RequestMapping(value="/attendance/{attendanceId}/powerpoint",method = RequestMethod.POST)   //上传讨论课PPT
    public FileVO postPPt(@PathVariable("attendanceId")Long attendanceId, @RequestParam("fileUpload") MultipartFile file )throws IOException
    {
        return attendanceService.uploadFile(attendanceId,file);
    }

    @RequestMapping(value="/presentation/{presentationId}/powerpoint",method = RequestMethod.PUT)   //重传讨论课PPT
    public FileVO putPPt(@PathVariable("attendanceId")Long attendanceId, @RequestParam("fileUpload") MultipartFile file)throws IOException
    {
        return attendanceService.uploadFile(attendanceId,file);
    }

    @RequestMapping(value="/attendance/{attendanceId}/powerpoint",method = RequestMethod.GET)   //下载讨论课PPT
    public void downPPt(@RequestParam("attendanceId")Long attendanceId,HttpServletResponse response)throws IOException
    {
        AttendanceEntity attendanceEntity=attendanceMapper.getAttendanceById(attendanceId);
        if(attendanceEntity.getPpt_url()==null){

        }
        String path=attendanceEntity.getPpt_url();
        String name=attendanceEntity.getPpt_name();
        FileUtils.downloadFile(response,path,name);
    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/powerpoint/downAll",method = RequestMethod.GET)   //下载此次讨论课所有的PPT
    public void downAllPPt(@RequestParam("seminarId")Integer seminarId, @RequestParam("classId")Integer classId)
    {

    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/report/downAll",method = RequestMethod.GET)   //下载此次讨论课所有的报告
    public void downloadAllReport(HttpServletRequest request, HttpServletResponse response, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId){

        }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/attendance",method = RequestMethod.GET)   //获得讨论报名的小组（测试通过）
    public List<AttendanceInfoVO> getAllpresentation(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId)
    {
        List<AttendanceEntity> attendanceEntities=attendanceService.findAllTeam(seminarId, classId);
        List<AttendanceInfoVO> vos=new ArrayList<>();
        for(AttendanceEntity attendanceEntity:attendanceEntities){
            AttendanceInfoVO vo=new AttendanceInfoVO();
            vo.setId(attendanceEntity.getId());
            vo.setTeamId(attendanceEntity.getTeam().getId());
            int teamSerial=attendanceEntity.getTeam().getTeam_serial();
            int classSerial=attendanceEntity.getTeam().getClass_serial();
            if(attendanceEntity.getPpt_name()==null)
            {
                vo.setTeamSerialName(String.valueOf(classSerial)+"-"+String.valueOf(teamSerial));
                vo.setPptStatus("未提交");
            }
            else {
                vo.setTeamSerialName(String.valueOf(classSerial) + "-" + String.valueOf(teamSerial));
                vo.setPptStatus("");
            }
            vo.setOrder(attendanceEntity.getTeam_order());
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(value = "/attendance/nextTeam",method = RequestMethod.PUT)
    public String nextTeam(@RequestParam("attendanceId1")Long attendanceId1,@RequestParam("attendanceId2")Long attendanceId2)
    {
        boolean b=attendanceService.nextTeam(attendanceId1, attendanceId2);
        if(b)
            return"success";
        else
            return "fail";
    }

    @RequestMapping(value = "/attendance/start",method = RequestMethod.PUT)
    public String startSeminar(@RequestParam("attendanceId")Long attendanceId)
    {
        boolean set=attendanceMapper.setStatus(attendanceId,1);
        if(set)
            return "success";
        else return "fail";
    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/presentation",method = RequestMethod.POST)   //报名展示（测试通过）
    public boolean postpresentation(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId,@RequestParam("presentationOrder")int presentationOrder,@RequestParam("teamId")Long teamId)
    {
        return attendanceService.signUpAttendence(seminarId, classId, presentationOrder, teamId);
    }

}


