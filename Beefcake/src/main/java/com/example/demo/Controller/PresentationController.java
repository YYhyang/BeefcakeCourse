package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class PresentationController {

    @RequestMapping(value="/presentation/{presentationId}",method = RequestMethod.GET)   //查看自己小组报名的展示
    public void getPresentation(@RequestParam("presentationId")Integer presentationId )
    {

    }

    @RequestMapping(value="/presentation/{presentationId}",method = RequestMethod.PUT)   //修改报名展示
    public void putPresentation(@RequestParam("presentationId")Integer presentationId,@RequestParam("presentationOrder")Integer presentationOrder )
    {

    }

    @RequestMapping(value="/presentation/{presentationId}",method = RequestMethod.DELETE)   //取消报名展示
    public void deletePresentation(@RequestParam("presentationId")Integer presentationId )
    {

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
    public void getAllpresentation(@RequestParam("seminarId")Integer seminarId, @RequestParam("classId")Integer classId,@RequestParam("status")String status)
    {

    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/presentation",method = RequestMethod.POST)   //报名展示
    public void postpresentation(@RequestParam("seminarId")Integer seminarId, @RequestParam("classId")Integer classId,@RequestParam("presentationOrder")Integer presentationOrder,@RequestParam("teamId")Integer teamId)
    {

    }

}


