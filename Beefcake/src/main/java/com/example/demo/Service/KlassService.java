package com.example.demo.Service;

import com.example.demo.Dao.KlassDao;
import com.example.demo.Dao.StudentDao;
import com.example.demo.Dao.TeamDao;
import com.example.demo.Entity.ClassEntity;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.Service.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class KlassService {
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private TeamService teamService;
    @Autowired
    private StudentDao studentDao;

    public ClassEntity getKlassById(Long klassId){
        ClassEntity classEntity = klassDao.getKlassById(klassId);
        classEntity.setTeams(teamService.getAllTeamByKlassId(klassId));
        return classEntity;
    }

    public List<ClassEntity> getKlassesByCourseId(Long courseId){
        List<Long> klassesId = klassDao.getAllKlassId(courseId);
        List<ClassEntity> classEntities = new ArrayList<>();
        for(Long klassId:klassesId){
            classEntities.add(getKlassById(klassId));
        }
        return classEntities;
    }

    public void createKlass(Long courseId,int grade,int klassSerial,String klassTime,String klassLocation){
        klassDao.createKlass(courseId,grade,klassSerial,klassTime,klassLocation);
    }

    public Long getKlassId(Long courseId,int grade,int klassSerial){
        return klassDao.getKlassId(courseId,grade,klassSerial);
    }

    public void inputStudent(MultipartFile file,Long courseId,Long classId) throws Exception {

        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            StudentEntity student = new StudentEntity();
            //student.setUserId(Integer.valueOf(String.valueOf(lo.get(0))));  // 主键是自增的
            String account=String.valueOf(lo.get(0));
            account=account.replaceAll("[^0-9]","");
            if(!account.matches("[0-9]+"))
                continue;
            student.setAccount(account);     // 表格的第一列
            StudentEntity temp=klassDao.selectStudentByAccount(student.getAccount());
            if(temp==null) {
                student.setStudent_name((String.valueOf(lo.get(1))));   // 表格的第二列
                student.setIs_active(0);
                student.setPassword("123456");
                klassDao.createStudent(student);
                temp=klassDao.selectStudentByAccount(student.getAccount());
            }
            Long studentId=temp.getId();
            klassDao.insertStudentIntoKlassStudent(classId,studentId,courseId);
        }

        in.close();
    }

}
