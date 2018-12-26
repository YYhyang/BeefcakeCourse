package com.example.demo.Controller;

import com.example.demo.DTO.CreateSeminarDTO;
import com.example.demo.DTO.changeReportDTO;
import com.example.demo.DTO.changeSeminarRoundDTO;
import com.example.demo.DTO.changeSeminarStatusDTO;
import com.example.demo.Entity.ClassEntity;
import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Entity.SeminarScoreEntity;
import com.example.demo.Service.SeminarService;
import com.example.demo.VO.KlassInTeamVO;
import com.example.demo.VO.SeminarInfoVO;
import com.example.demo.VO.SeminarScoreInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeminarController {
    @Autowired
    private SeminarService seminarService;
    @RequestMapping(value = "/seminar",method = RequestMethod.POST)
    public boolean createSeminar(@RequestBody CreateSeminarDTO dto)
    {
        return seminarService.createSeminar(dto);
    }

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

    @RequestMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore",method = RequestMethod.POST)//给一个小组的展示打分
    public boolean setPresentationScore(@PathVariable("seminarId")Long seminarId,@PathVariable("teamId")Long teamId,@RequestParam("presentationScore")double presentationScore)
    {
        return seminarService.setPresentationScore(seminarId, teamId, presentationScore);
    }

    @RequestMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore",method = RequestMethod.PUT)//给一个小组的报告打分
    public boolean setReportScore(@PathVariable("seminarId")Long seminarId,@PathVariable("teamId")Long teamId,@RequestParam("reportScore")double reportScore)
    {
        return seminarService.setReportScore(seminarId, teamId, reportScore);
    }

    @RequestMapping(value = "/seminar/end",method = RequestMethod.POST)
    public boolean setQuestionScore(@RequestParam("klassSeminarId")Long klassSeminarId)
    {
        return seminarService.setQuestionScore(klassSeminarId);
    }
}
