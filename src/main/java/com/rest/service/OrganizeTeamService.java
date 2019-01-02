package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizeTeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    TeamStudentDao teamStudentDao;
    @Autowired
    TeamValidApplicationDao teamValidApplicationDao;
    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;
    @Autowired
    TeamAndStrategyDao teamAndStrategyDao;
    @Autowired
    CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;
    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;
    @Autowired
    TeamOrStrategyDao teamOrStrategyDao;
    @Autowired
    TeamStrategyDao teamStrategyDao;


    /**
     * 更新小组关联表
     * @param team
     * @param teamValidApplication
     * @param klassStudent
     * @return
     */
    public int updateTeamTable(Team team, KlassStudent klassStudent, TeamValidApplication teamValidApplication) {

        int count=teamDao.updateKlassStudent(klassStudent);
        teamDao.updateTeamValidApplication(teamValidApplication);
        return count;
    }

    /**
     * 判断member_limit_strategy表
     * @param memberLimitStrategy
     * @param num
     * @return
     */
    public boolean isMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy,int num){
        System.out.println("isMemberLimitStrategy");
        if(num>memberLimitStrategy.getMaxMember()) return false;
        else if(num<memberLimitStrategy.getMinMember()) return false;
        else return true;
    }

    /**
     * 判断course_member_limit_strategy表
     * @param courseMemberLimitStrategy
     * @param teamStudentList
     * @return
     */
    public boolean isCourseMemberLimitStrategy(CourseMemberLimitStrategy courseMemberLimitStrategy,List<Long> teamStudentList ){
        System.out.println("isCourseMemberLimitStrategy");
        Long courseId=courseMemberLimitStrategy.getCourseId();
        int count=0;
        for(Long a:teamStudentList){
            List<Course> courseList=courseDao.queryCourseByStudentId(a);
            for(Course b: courseList){
                if(b.getId()==courseId)
                    count++;
            }
        }
        if(count>courseMemberLimitStrategy.getMaxMember()) return false;
        else if(count<courseMemberLimitStrategy.getMinMember()) return false;
        else return true;
    }

    /**
     * 判断conflict_course_strategy表
     * @param conflictCourseStrategyList
     * @param teamStudentList
     * @param courseId
     * @return
     */
    public boolean isConflictCourseStrategy(List<ConflictCourseStrategy> conflictCourseStrategyList,List<Long> teamStudentList,Long courseId){
        System.out.println("isConflictCourseStrategy");
        int num=teamStudentList.size();
        int count=0;
        List<Long> courseIdList=new ArrayList<Long>();
        for(ConflictCourseStrategy a:conflictCourseStrategyList) {
            if (a.getCourseId() != courseId) {
                courseIdList.add(a.getCourseId());
            }
        }
        for(Long a:teamStudentList){
            List<Course> courseList=courseDao.queryCourseByStudentId(a);
            for(Course b: courseList){
                for(Long c: courseIdList){
                    if(b.getId()==c) return false;
                }
            }
        }
         return true;
    }

    /**
     * 判断TeamOrStrategy表
     * @param teamOrStrategies
     * @param teamStudentList
     * @return
     */
    public boolean isTeamOrStrategy( List<TeamOrStrategy> teamOrStrategies,List<Long> teamStudentList,Long courseId){
        int num=teamStudentList.size();
        for(TeamOrStrategy c: teamOrStrategies) {
            if (c.getStrategyName().equals("TeamAndStrategy")) {
                System.out.println("b TeamAndStrategy");
                Long id_c = c.getStrategyId();
                List<TeamAndStrategy> teamAndStrategies = teamAndStrategyDao.queryTeamAndStrategy(id_c);
                isTeamAndStrategy(teamAndStrategies,teamStudentList,courseId);

            } else if (c.getStrategyName().equals("MemberLimitStrategy")) {
                System.out.println("c MemberLimitStrategy");
                MemberLimitStrategy memberLimitStrategy=memberLimitStrategyDao.queryMemberLimit(c.getStrategyId());
                if(isMemberLimitStrategy(memberLimitStrategy,num)) return true;

            } else if (c.getStrategyName().equals("CourseMemberLimitStrategy")) {
                System.out.println("c CourseMemberLimitStrategy");
                CourseMemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitStrategyDao.queryCourseMemberLimitStrategy(c.getStrategyId());
                if(isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList))return true;
            }else if (c.getStrategyName().equals("ConflictCourseStrategy")){
                System.out.println("a ConflictCourseStrategy");
                List<ConflictCourseStrategy> conflictCourseStrategyList=conflictCourseStrategyDao.queryConflictCourseStrategy(c.getStrategyId());
                if(isConflictCourseStrategy(conflictCourseStrategyList,teamStudentList,courseId)) return true;
            }
        }
        return false;
    }

    /**
     * 判断TeamAndStrategy表
     * @param teamAndStrategies
     * @param teamStudentList
     * @return
     */
    public  boolean isTeamAndStrategy(List<TeamAndStrategy> teamAndStrategies,List<Long> teamStudentList,Long courseId){
        int num=teamStudentList.size();
        int num_s=teamAndStrategies.size();
        int count=0;
        for (TeamAndStrategy c : teamAndStrategies) {
            if (c.getStrategyName().equals("TeamOrStrategy")) {
                System.out.println("b TeamOrStrategy");
                Long id_c=c.getStrategyId();
                List<TeamOrStrategy> teamOrStrategies=teamOrStrategyDao.queryTeamOrStrategy(id_c);
                isTeamOrStrategy(teamOrStrategies,teamStudentList,courseId);
            }
            else if (c.getStrategyName().equals("MemberLimitStrategy")) {
                System.out.println("c MemberLimitStrategy");
                MemberLimitStrategy memberLimitStrategy=memberLimitStrategyDao.queryMemberLimit(c.getStrategyId());
                if(isMemberLimitStrategy(memberLimitStrategy,num))count++;

            } else if (c.getStrategyName().equals("CourseMemberLimitStrategy")) {
                System.out.println("c CourseMemberLimitStrategy");
                CourseMemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitStrategyDao.queryCourseMemberLimitStrategy(c.getStrategyId());
                if(isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList))count++;

            }else if (c.getStrategyName().equals("ConflictCourseStrategy")){
                System.out.println("a ConflictCourseStrategy");
                List<ConflictCourseStrategy> conflictCourseStrategyList=conflictCourseStrategyDao.queryConflictCourseStrategy(c.getStrategyId());
                if(isConflictCourseStrategy(conflictCourseStrategyList,teamStudentList,courseId))count++;
            }
        }
        if(count==num_s) return true;
        else return false;
    }
    /**
     * 判断是否合格
     * @param teamId
     * @param courseId
     * @return
     */
    public boolean isValid(Long teamId,Long courseId){
        System.out.println("teamId"+teamId+" courseId"+courseId);
        List<Long> teamStudentList=teamStudentDao.queryByTeamId(teamId);
        System.out.println("teamStudentList"+teamStudentList.toString());
        int num=teamStudentList.size();
        if(num==0) return false;//不存在此队伍

        List<TeamStrategy> teamStrategies=teamStrategyDao.queryTeamStrategy(courseId);
        if(teamStrategies.size()==0) return false;//不存在该班级或其teamStrategies

        for(TeamStrategy a: teamStrategies){
            if(a.getStrategyName().equals("TeamAndStrategy")){
                System.out.println("a TeamAndStrategy");
                Long id = a.getStrategyId();
                List<TeamAndStrategy> teamAndStrategies = teamAndStrategyDao.queryTeamAndStrategy(id);
                isTeamAndStrategy(teamAndStrategies,teamStudentList,courseId);
            }

            else if(a.getStrategyName().equals("TeamOrStrategy")) {
                System.out.println("a TeamOrStrategy");
                Long id = a.getStrategyId();
                List<TeamOrStrategy> teamOrStrategies = teamOrStrategyDao.queryTeamOrStrategy(id);
                isTeamOrStrategy(teamOrStrategies,teamStudentList,courseId);
            }

            else if(a.getStrategyName().equals("MemberLimitStrategy")){
                System.out.println("a MemberLimitStrategy");
                MemberLimitStrategy memberLimitStrategy=memberLimitStrategyDao.queryMemberLimit(a.getStrategyId());
                isMemberLimitStrategy(memberLimitStrategy,num);
            }
            else if(a.getStrategyName().equals("CourseMemberLimitStrategy")){
                System.out.println("a CourseMemberLimitStrategy");
                CourseMemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitStrategyDao.queryCourseMemberLimitStrategy(a.getStrategyId());
                isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList);
            }
            else if(a.getStrategyName().equals("ConflictCourseStrategy")){
                System.out.println("a ConflictCourseStrategy");
                List<ConflictCourseStrategy> conflictCourseStrategyList=conflictCourseStrategyDao.queryConflictCourseStrategy(a.getStrategyId());
                isConflictCourseStrategy(conflictCourseStrategyList,teamStudentList,courseId);
            }
        }

        return true;
    }


    /**
     * 查找某课程队伍限制人数范围
     */
    public Long queryTeamOrStrategy( List<TeamOrStrategy> teamOrStrategies) {
        for (TeamOrStrategy c : teamOrStrategies) {
            if (c.getStrategyName().equals("TeamAndStrategy")) {
                System.out.println("b TeamAndStrategy");
                Long id_c = c.getStrategyId();
                List<TeamAndStrategy> teamAndStrategies = teamAndStrategyDao.queryTeamAndStrategy(id_c);
                queryTeamAndStrategy(teamAndStrategies);

            } else if (c.getStrategyName().equals("MemberLimitStrategy")) {
                System.out.println("c MemberLimitStrategy");
               return c.getStrategyId();
            }
        }
        return null;
    }

    public Long queryTeamAndStrategy (List<TeamAndStrategy> teamAndStrategies){
            for (TeamAndStrategy c : teamAndStrategies) {
                if (c.getStrategyName().equals("TeamOrStrategy")) {
                    System.out.println("b TeamOrStrategy");
                    Long id_c=c.getStrategyId();
                    System.out.println("TeamOrStrategy: "+id_c);
                    List<TeamOrStrategy> teamOrStrategies=teamOrStrategyDao.queryTeamOrStrategy(id_c);
                    queryTeamOrStrategy(teamOrStrategies);
                }
                else if (c.getStrategyName().equals("MemberLimitStrategy")) {
                    System.out.println("MemberLimitStrategy: "+c.getStrategyId());
                  return c.getStrategyId();
                }
            }
           return null;
    }
    /**
     * 查找总表
     * @param courseId
     * @return
     */
    public Long queryMemberLimitStrategyId(Long courseId){
        List<TeamStrategy> teamStrategies=teamStrategyDao.queryTeamStrategy(courseId);

        for(TeamStrategy a: teamStrategies){
            if(a.getStrategyName().equals("TeamAndStrategy")){
                System.out.println("a TeamAndStrategy");
                Long id = a.getStrategyId();
                System.out.println("TeamAndStrategy: "+id);
                List<TeamAndStrategy> teamAndStrategies = teamAndStrategyDao.queryTeamAndStrategy(id);
                if(queryTeamAndStrategy(teamAndStrategies)!=null) return queryTeamAndStrategy(teamAndStrategies);
            }

            else if(a.getStrategyName().equals("TeamOrStrategy")) {
                System.out.println("a TeamOrStrategy");
                Long id = a.getStrategyId();
                List<TeamOrStrategy> teamOrStrategies = teamOrStrategyDao.queryTeamOrStrategy(id);
                if(queryTeamOrStrategy(teamOrStrategies)!=null) return queryTeamOrStrategy(teamOrStrategies);
            }

            else if(a.getStrategyName().equals("MemberLimitStrategy")){
                System.out.println("a MemberLimitStrategy");
                return a.getStrategyId();
            }
        }
        return null;
    }

    /**
     * 通过组队策略id查找MemberLimitStrategy
     * @param id
     * @return
     */
    public MemberLimitStrategy queryMemberLimitStrategyById(Long id){
        System.out.println("queryMemberLimitStrategyById: "+id);
        return memberLimitStrategyDao.queryMemberLimitStrategyById(id);
    }


    /**
     * 查找某课程 选修课程限制人数范围
     */
    public Long queryTeamOrStrategyOne( List<TeamOrStrategy> teamOrStrategies) {
        for (TeamOrStrategy c : teamOrStrategies) {
            if (c.getStrategyName().equals("TeamAndStrategy")) {
                System.out.println("b TeamAndStrategy");
                Long id_c = c.getStrategyId();
                List<TeamAndStrategy> teamAndStrategies = teamAndStrategyDao.queryTeamAndStrategy(id_c);
                queryTeamAndStrategy(teamAndStrategies);

            } else if (c.getStrategyName().equals("CourseMemberLimitStrategy")) {
                System.out.println("c CourseMemberLimitStrategy");
                return c.getStrategyId();
            }
        }
        return null;
    }

    public Long queryTeamAndStrategyOne (List<TeamAndStrategy> teamAndStrategies){
        for (TeamAndStrategy c : teamAndStrategies) {
            if (c.getStrategyName().equals("TeamOrStrategy")) {
                System.out.println("b TeamOrStrategy");
                Long id_c=c.getStrategyId();
                System.out.println("TeamOrStrategy: "+id_c);
                List<TeamOrStrategy> teamOrStrategies=teamOrStrategyDao.queryTeamOrStrategy(id_c);
                queryTeamOrStrategy(teamOrStrategies);
            }
            else if (c.getStrategyName().equals("CourseMemberLimitStrategy")) {
                System.out.println("CourseMemberLimitStrategy: "+c.getStrategyId());
                return c.getStrategyId();
            }
        }
        return null;
    }
    /**
     * 查找总表
     * @param courseId
     * @return
     */
    public Long queryCourseMemberLimitStrategyId(Long courseId){
        List<TeamStrategy> teamStrategies=teamStrategyDao.queryTeamStrategy(courseId);

        for(TeamStrategy a: teamStrategies){
            if(a.getStrategyName().equals("TeamAndStrategy")){
                System.out.println("a TeamAndStrategy");
                Long id = a.getStrategyId();
                System.out.println("TeamAndStrategy: "+id);
                List<TeamAndStrategy> teamAndStrategies = teamAndStrategyDao.queryTeamAndStrategy(id);
                if(queryTeamAndStrategy(teamAndStrategies)!=null) return queryTeamAndStrategy(teamAndStrategies);
            }

            else if(a.getStrategyName().equals("TeamOrStrategy")) {
                System.out.println("a TeamOrStrategy");
                Long id = a.getStrategyId();
                List<TeamOrStrategy> teamOrStrategies = teamOrStrategyDao.queryTeamOrStrategy(id);
                if(queryTeamOrStrategy(teamOrStrategies)!=null) return queryTeamOrStrategy(teamOrStrategies);
            }

            else if(a.getStrategyName().equals("CourseMemberLimitStrategy")){
                System.out.println("a CourseMemberLimitStrategy");
                return a.getStrategyId();
            }
        }
        return null;
    }


     /**
     *通过组队策略id查找CourseMemberLimitStrategy
     * @param id
     * @return
     */
    public CourseMemberLimitStrategy queryCourseMemberLimitStrategy(Long id ){
        System.out.println("queryCourseMemberLimitStrategy"+id);
        return courseMemberLimitStrategyDao.queryCourseMemberLimitStrategy(id);
    }


    /**
     * 设置状态
     * @param teamId
     * @param courseId
     * @return
     */
    public int setStatus(Long teamId,Long courseId){
        if(isValid(teamId,courseId))
            return teamValidApplicationDao.insertTeamValidApplication(teamId,1);
        else return teamValidApplicationDao.insertTeamValidApplication(teamId,0);
    }

    /**
     * 设置小组总人数
     * @param minMember
     * @param maxMember
     * @return
     */
    public int setMemberLimitStrategy(Integer minMember,Integer maxMember){
        return memberLimitStrategyDao.insertMemberLimitStrategy(minMember,maxMember);
    }

    /**
     * 获得MemberLimitStrategy表的最大id
     * @return
     */
    public Long getMaxIdOfMemberLimitStrategy(){
        Long maxId=Long.valueOf(0);
        List<MemberLimitStrategy> memberLimitStrategyArrayList=new ArrayList<>();
        if(memberLimitStrategyDao.queryAllMemberLimitStrategy()!=null&&memberLimitStrategyDao.queryAllMemberLimitStrategy().size()!=0) {
            memberLimitStrategyArrayList =memberLimitStrategyDao.queryAllMemberLimitStrategy();
            maxId = Long.valueOf(1);
            for ( MemberLimitStrategy memberLimitStrategy: memberLimitStrategyArrayList) {
                if (memberLimitStrategy.getId() > maxId)
                    maxId = memberLimitStrategy.getId();
            }
        }
        return maxId;
    }

    /**
     * 设置组内选修课程人数
     * @param courseId
     * @param minMember
     * @param maxMember
     * @return
     */
    public int setCourseMemberLimitStrategy(Long courseId,Integer minMember,Integer maxMember){
        return courseMemberLimitStrategyDao.insertCourseMemberLimitStrategy(courseId,minMember,maxMember);
    }

    /**
     * 获得CourseMemberLimitStrategy表的最大id
     * @return
     */
    public Long getMaxIdOfCourseMemberLimitStrategy(){
        Long maxId=Long.valueOf(0);
        List<CourseMemberLimitStrategy> courseMemberLimitStrategyArrayList=new ArrayList<>();
        if(courseMemberLimitStrategyDao.queryAllCourseMemberLimitStrategy()!=null&&courseMemberLimitStrategyDao.queryAllCourseMemberLimitStrategy().size()!=0) {
            courseMemberLimitStrategyArrayList =courseMemberLimitStrategyDao.queryAllCourseMemberLimitStrategy();
            maxId = Long.valueOf(1);
            for (CourseMemberLimitStrategy courseMemberLimitStrategy : courseMemberLimitStrategyArrayList) {
                if (courseMemberLimitStrategy.getId() > maxId)
                    maxId = courseMemberLimitStrategy.getId();
            }
        }
        return maxId;
    }

    /**
     * 获得ConflictCourseStrategy表的最大id
     * @return
     */
    public Long getMaxIdOfConflictCourseStrategy(){
        Long maxId=Long.valueOf(0);
        List<ConflictCourseStrategy> conflictCourseStrategyList=new ArrayList<>();
        if(conflictCourseStrategyDao.queryAllConflictCourseStrategy()!=null&&conflictCourseStrategyDao.queryAllConflictCourseStrategy().size()!=0) {
            conflictCourseStrategyList = conflictCourseStrategyDao.queryAllConflictCourseStrategy();
            maxId = Long.valueOf(1);
            for (ConflictCourseStrategy conflictCourseStrategy : conflictCourseStrategyList) {
                if (conflictCourseStrategy.getId() > maxId)
                    maxId = conflictCourseStrategy.getId();
            }
        }
        return maxId;
    }

    /**
     * 设置冲突课程
     * @param courseIdList
     * @return
     */
    public int setConflictCourseStrategy(List<Long> courseIdList){
        Long maxId=getMaxIdOfConflictCourseStrategy();
        for (Long courseId:courseIdList){
            conflictCourseStrategyDao.insertConflictCourseStrategy(maxId+1,courseId);
        }
        if(courseIdList!=null&&courseIdList.size()!=0)
            return 1;
        return 0;
    }

    /**
     * 设置TeamAndStrategy表
     * @param strategyNameList
     * @param strategyIdList
     * @return
     */
    public int setTeamAndStrategy(List<String> strategyNameList,List<Long>strategyIdList){
        Long maxId=getMaxIdOfTeamAndStrategy();
        int i=0;
        for (String strategyName:strategyNameList){
            teamAndStrategyDao.insertTeamAndStrategy(maxId+1,strategyName,strategyIdList.get(i));
            i++;
        }
        if(strategyNameList!=null&&strategyNameList.size()!=0)
            return 1;
        return 0;
    }

    /**
     * 获得TeamAndStrategy表的最大id
     * @return
     */
    public Long getMaxIdOfTeamAndStrategy(){
        Long maxId=Long.valueOf(0);
        List<TeamAndStrategy> teamAndStrategyList=new ArrayList<>();
        if(teamAndStrategyDao.queryAllTeamAndStrategy()!=null&&teamAndStrategyDao.queryAllTeamAndStrategy().size()!=0){
            teamAndStrategyList=teamAndStrategyDao.queryAllTeamAndStrategy();
            maxId=Long.valueOf(1);
            for(TeamAndStrategy teamAndStrategy:teamAndStrategyList){
                if(teamAndStrategy.getId()>maxId)
                    maxId=teamAndStrategy.getId();
            }
        }
        return maxId;
    }

    /**
     * 设置TeamOrStrategy表
     * @param strategyNameList
     * @param strategyIdList
     * @return
     */
    public int setTeamOrStrategy(List<String> strategyNameList,List<Long>strategyIdList){
        Long maxId=getMaxIdOfTeamOrStrategy();
        int i=0;
        for (String strategyName:strategyNameList){
            teamOrStrategyDao.insertTeamOrStrategy(maxId+1,strategyName,strategyIdList.get(i));
            i++;
        }
        if(strategyNameList!=null&&strategyNameList.size()!=0)
            return 1;
        return 0;
    }

    /**
     * 获得TeamOrStrategy表的最大id
     * @return
     */
    public Long getMaxIdOfTeamOrStrategy(){
        Long maxId=Long.valueOf(0);
        List<TeamOrStrategy> teamOrStrategyList=new ArrayList<>();
        if(teamOrStrategyDao.queryAllTeamOrStrategy()!=null&&teamOrStrategyDao.queryAllTeamOrStrategy().size()!=0){
            teamOrStrategyList=teamOrStrategyDao.queryAllTeamOrStrategy();
            maxId=Long.valueOf(1);
            for(TeamOrStrategy teamOrStrategy:teamOrStrategyList){
                if(teamOrStrategy.getId()>maxId)
                    maxId=teamOrStrategy.getId();
            }
        }
        return maxId;
    }





     /**
     *设置均满足或满足其一 => 设置组内选修课程人数
     * @param option
     * @param courseIdList
     * @param minMemberList
     * @param maxMemberList
     * @return
     */
    public int setCourseMemberLimit(int option,List<Long> courseIdList,List<Integer> minMemberList,List<Integer> maxMemberList){
        //如果option为1则均满足则TeamAndStrategy
        //如果option为0则均满足则TeamOrStrategy
        Long maxId=getMaxIdOfCourseMemberLimitStrategy();//CourseMemberLimitStrategy表的最大ID
        List<Long>strategyIdList=new ArrayList<>();//插入CourseMemberLimitStrategy表的idList
        List<String>strategyNameList=new ArrayList<>();//插入CourseMemberLimitStrategy表的NameList
        int i=0;
        for (Long courseId : courseIdList) {
            setCourseMemberLimitStrategy(courseId, minMemberList.get(i), maxMemberList.get(i));
            i++;
            strategyIdList.add(maxId+i);
            strategyNameList.add("CourseMemberLimitStrategy");
        }
        if(option==1) {
            return setTeamAndStrategy(strategyNameList,strategyIdList);
        }
        else if(option==0){
            return setTeamOrStrategy(strategyNameList,strategyIdList);
        }
        return 0;
    }

    /**
     * 设置总表
     * @param minMember_MemberLimitStrategy
     * @param maxMember_MemberLimitStrategy
     * @param option_CourseMemberLimitStrategy
     * @param courseIdList_CourseMemberLimitStrategy
     * @param minMemberList_CourseMemberLimitStrategy
     * @param maxMemberList_CourseMemberLimitStrategy
     * @param courseIdList_ConflictCourseStrategy
     * @param courseId_TeamStrategy
     * @return
     */
    public int setTeamStrategy(Integer minMember_MemberLimitStrategy,
                               Integer maxMember_MemberLimitStrategy,
                               int option_CourseMemberLimitStrategy,
                               List<Long> courseIdList_CourseMemberLimitStrategy,
                               List<Integer> minMemberList_CourseMemberLimitStrategy,
                               List<Integer> maxMemberList_CourseMemberLimitStrategy,
                               List<Long> courseIdList_ConflictCourseStrategy,
                               Long courseId_TeamStrategy
                           )
    {
        List<String> strategyNameList=new ArrayList<>();
        List<Long>strategyIdList=new ArrayList<>();
        //设置小组总人数
        setMemberLimitStrategy(minMember_MemberLimitStrategy,maxMember_MemberLimitStrategy);
        strategyNameList.add("MemberLimitStrategy");
        strategyIdList.add(getMaxIdOfMemberLimitStrategy());
        //设置组内选修课人数
        setCourseMemberLimit(option_CourseMemberLimitStrategy,courseIdList_CourseMemberLimitStrategy,minMemberList_CourseMemberLimitStrategy,maxMemberList_CourseMemberLimitStrategy);
        if(option_CourseMemberLimitStrategy==1){
            strategyNameList.add("TeamAndStrategy");
        }
        else if (option_CourseMemberLimitStrategy==0){
            strategyNameList.add("TeamOrStrategy");
        }
        strategyIdList.add(getMaxIdOfTeamOrStrategy());
        setTeamAndStrategy(strategyNameList,strategyIdList);
        //设置ConflictCourseStrategy表
        setConflictCourseStrategy(courseIdList_ConflictCourseStrategy);
        //插入总表
        Integer strategySerial=0;
        if(teamStrategyDao.queryTeamStrategy(courseId_TeamStrategy).size()!=0&&teamStrategyDao.queryTeamStrategy(courseId_TeamStrategy)!=null)
            strategySerial=teamStrategyDao.queryTeamStrategy(courseId_TeamStrategy).size();
        strategySerial++;
        teamStrategyDao.insertTeamStrategy(courseId_TeamStrategy,strategySerial,"ConflictCourseStrategy",getMaxIdOfConflictCourseStrategy());
        strategySerial++;
        teamStrategyDao.insertTeamStrategy(courseId_TeamStrategy,strategySerial,"TeamAndStrategy",getMaxIdOfTeamAndStrategy());
        return strategySerial;
    }

}
