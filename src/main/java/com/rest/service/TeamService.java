package com.rest.service;

import com.rest.dao.*;
import com.rest.dao.TeamValidApplicationDao;
import com.rest.entity.*;
import com.rest.dao.KlassStudentDao;
import com.rest.dao.KlassTeamDao;
import com.rest.dao.TeamDao;
import com.rest.dao.TeamStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private KlassStudentDao klassStudentDao;
    @Autowired
    private TeamStudentDao teamStudentDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private KlassTeamDao klassTeamDao;
    @Autowired
    private ConflictCourseStrategyDao conflictCourseStrategyDao;
    @Autowired
    private TeamValidApplicationDao teamValidApplicationDao;
    @Autowired
    private SeminarScoreDao seminarScoreDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private KlassDao klassDao;

    public Long findId(Long courseId){
        return conflictCourseStrategyDao.findId(courseId);
    }

    public List<Team> queryAllTeam(Long courseId){
        return teamDao.queryAllTeam(courseId);
    }

    public List<Student> queryStudentNoTeam(Long courseId){
        return teamDao.queryStudentNoTeam(courseId);
    }

    public Team findTeamByTeamId(Long teamId){
        return teamDao.findById(teamId);
    }
    public int setValid(Long teamId){
        return teamDao.setValid(teamId);
    }
    public int updateInfo(Long teamId,String team_name,Integer team_serial){
        return teamDao.updateInfo(teamId,team_name,team_serial);
    }
    public int deleteTeam(Long teamId){
        if (teamDao.deleteTeam(teamId)==1){
            teamStudentDao.deleteByTeamId(teamId);
            klassTeamDao.deleteKlassTeamsByTeamId(teamId);
            return 1;
        }
        else {
            return 0;
        }
    }
    public KlassStudent findByStudentId(Long studentId){
        return klassStudentDao.findByStudentId(studentId);
    }
    public int quitTeam(Long studentId){
        return klassStudentDao.quitTeam(studentId);
    }
    public int updateTeam(Long studentId,Long teamId)
    {
        return klassStudentDao.updateTeam(studentId,teamId);
    }
    public Long findTeamByStudentId(Long studentId){
        return teamStudentDao.findByStudentId(studentId);
    }
    public List<Long> findAllKlass(Long teamId){
        return klassTeamDao.findByTeamId(teamId);
    }
    public List<Long> findConflict(Long id){
        return conflictCourseStrategyDao.findConflict(id);
    }
    public List<Long> findConflictCourse(Long courseId){
        Long id=conflictCourseStrategyDao.findId(courseId);
        List<Long> conflict=conflictCourseStrategyDao.findConflict(id);
        return conflict;
    }
    public List<Team> findTeamByCourseId(Long courseId){
        return teamDao.findByCourseId(courseId);
    }
    public int deleteTeamStudentByTeamId(Long teamId){
        return teamStudentDao.deleteByTeamId(teamId);
    }
    public int deleteKlassTeamByTeamId(Long teamId){
        return klassTeamDao.deleteKlassTeamsByTeamId(teamId);
    }
    public int saveTeam(Team team){
        Integer teamSerial=new Integer(0);
        List<Team> teamList=teamDao.findByCourseId(team.getCourseId());
        if(!teamList.isEmpty()){
            for(Team team1:teamList){
                if(team1.getTeamSerial()>teamSerial){
                    teamSerial=team1.getTeamSerial();
                }
            }
        }
        team.setTeamSerial(teamSerial+1);
        if(teamDao.save(team)==1){
            KlassTeam klassTeam=new KlassTeam();
            klassTeam.setKlassId(team.getKlassId());
            klassTeam.setTeamId(team.getId());
            klassTeamDao.save(klassTeam);
            return 1;
        }
        else {
            return 0;
        }
    }
    public int saveKlassTeam(KlassTeam klassTeam){
        return klassTeamDao.save(klassTeam);
    }
    public List<TeamStudent> findStudentByTeamId(Long teamId){
        return teamStudentDao.findByTeamId(teamId);
    }
//    public Long findKlassId(Long teamId,Long courseId){
//        List<TeamStudent> teamStudentList=teamStudentDao.findByTeamId(teamId);
//        List<Integer> num=new ArrayList<Integer>();
//        List<Long> klassIdList=new ArrayList<Long>();
//        Long classId=new Long(0);
//        for(TeamStudent teamStudent:teamStudentList){
//            Long klassId=klassStudentDao.findKlass(teamStudent.getStudentId(),courseId);
//            boolean flag=true;
//            int i=0;
//            for(i=0;i<klassIdList.size();i++){
//                flag=true;
//                if(klassId.equals(klassIdList.get(i))){
//                    flag=false;
//                    break;
//                }
//            }
//            if (flag){
//                num.add(new Integer(1));
//            }
//            else {
//                num.set(i,num.get(i)+1);
//            }
//        }
//        Integer max=num.get(0);
//        Integer maxI=0;
//        boolean same=true;
//        for(int i=1;i<num.size();i++){
//            if(!num.get(i).equals(max)){
//                same=false;
//                if (num.get(i)>max){
//                    max=num.get(i);
//                    maxI=i;
//                }
//            }
//        }
//        if(!same) {
//            classId = klassIdList.get(maxI);
//        }
//        else {
//            Integer minI=new Integer(1000);
//            for(Long klassId:klassIdList){
//                List<KlassTeam> klassTeamList=klassTeamDao.findByKlassId(klassId);
//                if(minI>klassTeamList.size()){
//                    classId=klassId;
//                }
//            }
//        }
//        return classId;
//    }
    public int deleteTeamByCourseId(Long courseId){
        List<Team> teamList=teamDao.findByCourseId(courseId);
        if(!teamList.isEmpty()) {
            for(Team team:teamList){
                klassTeamDao.deleteKlassTeamsByTeamId(team.getId());
            }
            return teamDao.deleteTeamByCourseId(courseId);
        }
        else {
            return 1;
        }
    }
    public int deleteKlassTeam(Long teamId){
        return klassTeamDao.deleteKlassTeamsByTeamId(teamId);
    }

    /**
     * 提交申请
     * @param teamValidApplication
     * @return
     */
    public  int saveTeamValidApplication(TeamValidApplication teamValidApplication){
        return teamValidApplicationDao.saveTeamValidApplication(teamValidApplication);
    }

    public Integer numberOfKlassTeam(Long klassId){
        List<KlassTeam> klassTeamList=klassTeamDao.findByKlassId(klassId);
        Integer num=klassTeamList.size();
        if (klassTeamList.isEmpty()){
            System.out.println("Not Found class");
        }
        System.out.println("The number of klass is "+num);
        return num;
    }
    public Long findSubCourseTeamKlassId(Long courseId, Long teamId){
        List<TeamStudent> teamStudentList=findStudentByTeamId(teamId);
        List<Long> klassList=new ArrayList<Long>();
        for(TeamStudent teamStudent:teamStudentList){
            Long studentId=teamStudent.getStudentId();
            System.out.println(studentId+" "+courseId);
            System.out.println(klassStudentDao.queryKlassByStudentIdCourseId(studentId,courseId));
            klassList.add(klassStudentDao.queryKlassByStudentIdCourseId(studentId,courseId));
        }
        System.out.println("Find students' classId");
        List<Long> classList=new ArrayList<Long>();
        for (Long id:klassList){
            if (id!=null){
                classList.add(id);
            }
        }
        if(!classList.isEmpty()) {
            HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
            for (Long id : classList) {
                hashMap.put(id, 0);
            }
            for (Long id : classList) {
                for (Map.Entry<Long, Integer> arg : hashMap.entrySet()) {
                    if (id.equals(arg.getKey())) {
                        hashMap.put(arg.getKey(), arg.getValue() + 1);
                    }
                }
            }
            Long classId;
            Integer max;
            Iterator it = hashMap.keySet().iterator();
            String str = String.valueOf(it.next());
            classId = Long.valueOf(str);
            max = hashMap.get(classId);
            System.out.println("The First is " + classId + " And number is " + max);
            boolean sameFlag = true;
            for (Map.Entry<Long, Integer> arg : hashMap.entrySet()) {
                if (arg.getValue() > max) {
                    sameFlag = false;
                    classId = arg.getKey();
                    max = arg.getValue();
                }
            }
            System.out.println("Now classId is " + classId + " the number is " + max + " And flag of AllSame is " + sameFlag);
            if (sameFlag&&hashMap.size()>1) {
                List<Klass> klasses = klassDao.findByCourseId(courseId);
                HashMap<Long, Integer> hashMap1 = new HashMap<>();
                for (Klass klass : klasses) {
                    Long id = klass.getId();
                    Integer num = numberOfKlassTeam(id);
                    hashMap1.put(id, num);
                }
                Long cId;
                Integer min;
                Iterator iterator = hashMap1.keySet().iterator();
                String temp = String.valueOf(iterator.next());
                cId = Long.valueOf(temp);
                min = hashMap1.get(cId);
                for (Map.Entry<Long, Integer> a : hashMap1.entrySet()) {
                    System.out.println("the key is " + a.getKey() + " the value is " + a.getValue());
                    if (min > a.getValue()) {
                        min = a.getValue();
                        cId = a.getKey();
                    }
                }
                System.out.println("The classId is " + cId + " the number is " + min);
                classId = cId;
            }
            return classId;
        }
        else {
            return new Long(0);
        }
    }
    public int deleteByTeamIdAndKlassId(Long teamId,Long klassId){
        return klassTeamDao.deleteByTeamIdAndKlassId(teamId,klassId);
    }

    /**
     * 增加队员
     * @param teamStudentList
     * @return
     */
    public int batchInsertTeamStudent(List<TeamStudent> teamStudentList){

        return teamStudentDao.batchInsertTeamStudent(teamStudentList);
    }

    /**
     * 删除成员
     * @param studentId
     * @return
     */
    public int deleteByStudentId(Long studentId){
        return teamStudentDao.deleteByStudentId(studentId);
    }

    @GetMapping(value = "{teamId}")
    public ResponseEntity<TeamInfo> teamInfo(@PathVariable("teamId") Long teamId){
        TeamInfo teamInfo=new TeamInfo();
        Team team=teamDao.findById(teamId);
        teamInfo.setTeamId(team.getId());
        teamInfo.setClassId(team.getKlassId());
        teamInfo.setCourseId(team.getCourseId());
        teamInfo.setTeamName(team.getTeamName());
        teamInfo.setTeamSerial(team.getTeamSerial());
        teamInfo.setKlassSerial(team.getKlassSerial());
        Student leader=studentDao.findById(team.getLeaderId());
        teamInfo.setLeader(leader);
        List<Long> memberIdList=new ArrayList<>();
        memberIdList=teamStudentDao.queryByTeamId(teamId);
        List<Student> memberList=new ArrayList<>();
        for(Long id:memberIdList){
            memberList.add(studentDao.findById(id));
        }
        teamInfo.setMember(memberList);
        return new ResponseEntity<TeamInfo>(teamInfo, HttpStatus.OK);
    }
}
