package com.example.demo.controller;

import com.example.demo.dto.CreateSeminarDTO;
import com.example.demo.dto.changeReportDTO;
import com.example.demo.dto.changeSeminarRoundDTO;
import com.example.demo.dto.changeSeminarStatusDTO;
import com.example.demo.dao.SeminarDao;
import com.example.demo.entity.ClassEntity;
import com.example.demo.entity.SeminarEntity;
import com.example.demo.entity.SeminarScoreEntity;
import com.example.demo.service.SeminarService;
import com.example.demo.vo.KlassInTeamVO;
import com.example.demo.vo.KlassSeminarInfo;
import com.example.demo.vo.SeminarInfoVO;
import com.example.demo.vo.SeminarScoreInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeminarController {
    @Autowired
    private SeminarService seminarService;
    @Autowired
    private SeminarDao seminarDao;
    @RequestMapping(value = "/seminar",method = RequestMethod.POST)
    public boolean createSeminar(@RequestBody CreateSeminarDTO dto)
    {
        return seminarService.createSeminar(dto);
    }//过了

    @RequestMapping(value = "/seminar/{seminarId}/class",method = RequestMethod.GET)//获取讨论课所属的班级(测试通过)
    public List<KlassInTeamVO> getClass(@PathVariable("seminarId")Long seminarId)
    {
        List<ClassEntity> klasses=seminarService.getClassBySeminarId(seminarId);
        List<KlassInTeamVO> vos=new ArrayList<>();
        for(ClassEntity klass:klasses) {
            KlassInTeamVO vo = new KlassInTeamVO();
            vo.setId(klass.getId());
            vo.setName(String.valueOf(klass.getGrade()) + "-" + String.valueOf(klass.getKlass_serial()));
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(value = "/seminar/{seminarId}",method = RequestMethod.PUT)//修改讨论课
    public boolean changeSeminar(@PathVariable("seminarId")Long seminarId,@RequestBody CreateSeminarDTO dto)
    {
        return seminarService.changeSeminar(seminarId,dto);
    }

    @RequestMapping(value = "/seminar/{seminarId}",method = RequestMethod.DELETE)
    public boolean deleteSeminar(@PathVariable("seminarId")Long seminarId)
    {
        return seminarService.deleteSeminar(seminarId);
    }

    @RequestMapping(value = "/seminar/{seminarId}",method = RequestMethod.GET)//获取某节讨论课（测试成功）
    public SeminarInfoVO getSeminar(@PathVariable("seminarId")Long seminarId)
    {
        SeminarEntity seminarEntity=seminarService.getSeminarBySeminarId(seminarId);
        SeminarInfoVO vo=new SeminarInfoVO();
        vo.setId(seminarEntity.getId());
        vo.setSeminarName(seminarEntity.getSeminar_name());
        vo.setIntroduction(seminarEntity.getIntroduction());
        vo.setOrder(seminarEntity.getSeminar_serial());
        vo.setMaxTeam(seminarEntity.getMax_team());
        vo.setStart(seminarEntity.getEnroll_start_time());
        vo.setEnd(seminarEntity.getEnroll_end_time());
        vo.setVisible(seminarEntity.getIs_visible());
        vo.setRoundOrder(seminarEntity.getRound().getRound_serial());
        vo.setRoundId(seminarEntity.getRound().getId());
        return vo;
    }

    @RequestMapping(value = "/seminar/{seminarId}/class/{classId}",method = RequestMethod.GET)
    public KlassSeminarInfo getSeminarByKlassSeminarId(@PathVariable("seminarId")Long seminarId,@PathVariable("classId")Long classId)
    {
        KlassSeminarInfo vo=new KlassSeminarInfo();
        SeminarEntity seminarEntity=seminarService.getSeminarBySeminarId(seminarId);
        vo.setSeminarName(seminarEntity.getSeminar_name());
        vo.setIntroduction(seminarEntity.getIntroduction());
        vo.setRound(seminarEntity.getRound().getRound_serial());
        vo.setSeminarSerial(seminarEntity.getSeminar_serial());
        vo.setKlassSeminarId(seminarDao.getKlassSeminarIdByClassIdAndSeminarId(classId,seminarId));
        vo.setStatus(seminarDao.getStatus(seminarId, classId));
        return vo;
    }

    @RequestMapping(value = "/seminar/{seminarId}/class/{classId}",method = RequestMethod.PUT)
    public boolean changeDDL(@PathVariable("seminarId")Long seminarId,@PathVariable("classId")Long classId,@RequestBody changeReportDTO dto){
        return seminarService.changeReportDDL(seminarId, classId, dto.getReportDDL());
    }

    @RequestMapping(value = "/seminar/{seminarId}/round",method = RequestMethod.PUT)//设置讨论课轮次
    public boolean setRound(@PathVariable("seminarId")Long seminarId, @RequestBody changeSeminarRoundDTO dto)
    {
        return seminarService.setRound(seminarId,dto);
    }

    @RequestMapping(value = "/seminar/{seminarId}/status",method = RequestMethod.PUT)
    public boolean setStatus(@PathVariable("seminarId")Long seminarId,@RequestBody changeSeminarStatusDTO dto){
        return seminarService.setStatus(seminarId, dto);
    }

    @RequestMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore",method = RequestMethod.GET)
    public SeminarScoreInfoVO getScore(@PathVariable("seminarId")Long seminarId, @PathVariable("teamId")Long teamId)
    {
        SeminarScoreEntity score= seminarService.getScoreBySeminarIdAndTeamId(seminarId, teamId);
        SeminarScoreInfoVO vo=new SeminarScoreInfoVO();
        vo.setPresentation_score(score.getPresentation_score());
        vo.setQuestion_score(score.getQuestion_score());
        vo.setReport_score(score.getReport_score());
        vo.setTotal_score(score.getTotal_score());
        vo.setTeam_id(score.getTeam().getId());
        vo.setTeamName(score.getTeam().getTeam_name());
        return vo;
    }

    @RequestMapping(value = "/seminar/{seminarId}/class/{classId}/seminarscore",method = RequestMethod.POST)//给一个小组的展示打分
    public String createPresentationScore(@PathVariable("seminarId")Long seminarId,@PathVariable("classId")Long classId,@RequestParam("teamId")Long teamId,@RequestParam("presentationScore")double presentationScore)
    {
        return seminarService.setPresentationScore(seminarId, classId, teamId,presentationScore)?"success":"fail";
    }

    @RequestMapping(value = "/seminar/{seminarId}/class/{classId}/seminarscore",method = RequestMethod.PUT)//给一个小组的展示打分
    public boolean setPresentationScore(@PathVariable("seminarId")Long seminarId,@PathVariable("classId")Long classId,@RequestParam("teamId")Long teamId,@RequestParam("presentationScore")double presentationScore)
    {
        return seminarService.updatePresentationScore(seminarId, classId, teamId,presentationScore);
    }



    @RequestMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore",method = RequestMethod.PUT)//给一个小组的报告打分
    public boolean setReportScore(@PathVariable("seminarId")Long seminarId,@PathVariable("teamId")Long teamId,@RequestParam("reportScore")double reportScore)
    {
        return seminarService.setReportScore(seminarId, teamId, reportScore);
    }

    @RequestMapping(value = "/seminar/end",method = RequestMethod.POST)
    public String setQuestionScore(@RequestParam("seminarId")Long seminarId,@RequestParam("klassId")Long klassId)
    {
        Long klassSeminarId=seminarDao.getKlassSeminarIdByClassIdAndSeminarId(klassId,seminarId);
         seminarService.setQuestionScore(klassSeminarId);
         boolean b=seminarDao.setStatus(seminarId,klassId,2);
         if(b) {
             return "success";
         } else {
             return "fail";
         }
    }
}
