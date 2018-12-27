package com.example.demo.Controller;

import com.example.demo.DTO.changeRoundDTO;
import com.example.demo.DTO.changeRoundScoreDTO;
import com.example.demo.Entity.RoundEntity;
import com.example.demo.Entity.RoundscoreEntity;
import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Mapper.RoundMapper;
import com.example.demo.Service.RoundService;
import com.example.demo.VO.RoundInfoVO;
import com.example.demo.VO.RoundScoreInfoVO;
import com.example.demo.VO.RoundSeminarInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RoundController {

    @Autowired
    RoundService roundService;
    @Autowired
    RoundMapper roundMapper;

    @RequestMapping(value = "/round/{roundId}/seminar",method = RequestMethod.GET)//获取某轮次的所有讨论课（通过）
    public List<RoundSeminarInfoVO> getSeminar(@PathVariable("roundId")Long roundId)
    {
        List<SeminarEntity> seminarList=roundService.findAllSeminarByRoundId(roundId);
        List<RoundSeminarInfoVO> VoList=new ArrayList<>();
        for(SeminarEntity temp:seminarList){
            RoundSeminarInfoVO vo=new RoundSeminarInfoVO();
            vo.setId(temp.getId());
            vo.setName(temp.getSeminar_name());
            vo.setOrder(temp.getSeminar_serial());
            VoList.add(vo);
        }
        return VoList;

    }

    @RequestMapping(value = "/round/{roundId}",method = RequestMethod.GET)//获取某轮次（通过）
    public RoundInfoVO getRound(@PathVariable("roundId")Long roundId)
    {
        RoundInfoVO vo=new RoundInfoVO();
        RoundEntity round=roundService.getRoundByRoundId(roundId);
        vo.setId(round.getId());
        vo.setOrder(round.getRound_serial());
        vo.setPresentation_score_method(round.getPresentation_score_method());
        vo.setQuestion_score_method(round.getQuestion_score_method());
        vo.setReport_score_method(round.getReport_score_method());
        return vo;
    }

    @RequestMapping(value = "/test",method = RequestMethod.PUT)
    public boolean test(@RequestParam("roundId")Long roundId,@RequestParam("presentation")int presentation,@RequestParam("report")int report,@RequestParam("question")int question)
    {
        return roundMapper.changeRoundInfo(roundId, presentation, report, question);
    }

    @RequestMapping(value = "/round/{roundId}",method = RequestMethod.PUT)
    public boolean changeRound(@PathVariable("roundId")Long roundId, @RequestBody changeRoundDTO changeRoundDTO)
    {
        return roundService.changeRound(roundId, changeRoundDTO);
    }

    @RequestMapping(value = "/round",method = RequestMethod.POST)//未测试
    public boolean createRound(@RequestParam("round_serial")int round_serial, @RequestParam("courseId")Long courseId)
    {
        return roundService.createRound(round_serial,courseId);
    }

    @RequestMapping(value = "/round/{roundId}/roundscore",method = RequestMethod.GET)//获取某轮的所有分数（通过）
    public List<RoundScoreInfoVO> getAllRoundScoreByRoundId(@PathVariable("roundId")Long roundId){

        List<RoundscoreEntity> scoreList=roundService.getAllRoundScoreByRoundId(roundId);
        List<RoundScoreInfoVO> VoList=new ArrayList<>();
        for(RoundscoreEntity score:scoreList){
            RoundScoreInfoVO vo=new RoundScoreInfoVO();
            vo.setPresentation_score(score.getPresentation_score());
            vo.setQuestion_score(score.getQuestion_score());
            vo.setReport_score(score.getReport_score());
            vo.setTotal_score(score.getTotal_score());
            vo.setRound_id(score.getRound().getId());
            vo.setRound_order(score.getRound().getRound_serial());
            vo.setTeam_id(score.getTeam().getId());
            vo.setTeam_name(score.getTeam().getTeam_name());
            int team_serial=score.getTeam().getTeam_serial();
            int class_serial=score.getTeam().getClass_serial();
            vo.setTeam_serial_name(String.valueOf(class_serial)+"-"+String.valueOf(team_serial));
            VoList.add(vo);
        }
        return VoList;

    }
    @RequestMapping(value = "/round/{roundId}/team/{teamId}/roundscore",method = RequestMethod.GET)//获取某个组的轮次成绩（通过）
    public RoundScoreInfoVO getAllRoundScoreByRoundIdAndTeamId(@PathVariable("roundId")Long roundId, @PathVariable("teamId")Long teamId){
        RoundscoreEntity score=roundService.getAllRoundScoreByRoundIdAndTeamId(roundId,teamId);
        RoundScoreInfoVO vo=new RoundScoreInfoVO();
        vo.setPresentation_score(score.getPresentation_score());
        vo.setQuestion_score(score.getQuestion_score());
        vo.setReport_score(score.getReport_score());
        vo.setTotal_score(score.getTotal_score());
        vo.setRound_id(score.getRound().getId());
        vo.setRound_order(score.getRound().getRound_serial());
        vo.setTeam_id(score.getTeam().getId());
        vo.setTeam_name(score.getTeam().getTeam_name());
        return vo;
    }

    @RequestMapping(value = "/round/{roundId}/team/{teamId}/roundscore",method = RequestMethod.PUT)
    public boolean changeScore(@PathVariable("roundId")Long roundId,@PathVariable("teamId")Long teamId,@RequestBody changeRoundScoreDTO dto){
        return roundService.changeRoundScore(roundId,teamId,dto);
    }
}
