package com.rest.service;

import com.rest.dao.CourseDao;
import com.rest.dao.TeamDao;
import com.rest.dao.TeamStudentDao;
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
                List<TeamAndStrategy> teamAndStrategies = teamDao.queryTeamAndStrategy(id_c);
                isTeamAndStrategy(teamAndStrategies,teamStudentList,courseId);

            } else if (c.getStrategyName().equals("MemberLimitStrategy")) {
                System.out.println("c MemberLimitStrategy");
                MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(c.getStrategyId());
                if(isMemberLimitStrategy(memberLimitStrategy,num)) return true;

            } else if (c.getStrategyName().equals("CourseMemberLimitStrategy")) {
                System.out.println("c CourseMemberLimitStrategy");
                CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(c.getStrategyId());
                if(isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList))return true;
            }else if (c.getStrategyName().equals("ConflictCourseStrategy")){
                System.out.println("a ConflictCourseStrategy");
                List<ConflictCourseStrategy> conflictCourseStrategyList=teamDao.queryConflictCourseStrategy(c.getStrategyId());
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
                List<TeamOrStrategy> teamOrStrategies=teamDao.queryTeamOrStrategy(id_c);
                isTeamOrStrategy(teamOrStrategies,teamStudentList,courseId);
            }
            else if (c.getStrategyName().equals("MemberLimitStrategy")) {
                System.out.println("c MemberLimitStrategy");
                MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(c.getStrategyId());
                if(isMemberLimitStrategy(memberLimitStrategy,num))count++;

            } else if (c.getStrategyName().equals("CourseMemberLimitStrategy")) {
                System.out.println("c CourseMemberLimitStrategy");
                CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(c.getStrategyId());
                if(isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList))count++;

            }else if (c.getStrategyName().equals("ConflictCourseStrategy")){
                System.out.println("a ConflictCourseStrategy");
                List<ConflictCourseStrategy> conflictCourseStrategyList=teamDao.queryConflictCourseStrategy(c.getStrategyId());
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

        List<TeamStrategy> teamStrategies=teamDao.queryTeamStrategy(courseId);
        if(teamStrategies.size()==0) return false;//不存在该班级或其teamStrategies

        for(TeamStrategy a: teamStrategies){
            if(a.getStrategyName().equals("TeamAndStrategy")){
                System.out.println("a TeamAndStrategy");
                Long id = a.getStrategyId();
                List<TeamAndStrategy> teamAndStrategies = teamDao.queryTeamAndStrategy(id);
                isTeamAndStrategy(teamAndStrategies,teamStudentList,courseId);
            }

            else if(a.getStrategyName().equals("TeamOrStrategy")) {
                System.out.println("a TeamOrStrategy");
                Long id = a.getStrategyId();
                List<TeamOrStrategy> teamOrStrategies = teamDao.queryTeamOrStrategy(id);
                isTeamOrStrategy(teamOrStrategies,teamStudentList,courseId);
            }

            else if(a.getStrategyName().equals("MemberLimitStrategy")){
                System.out.println("a MemberLimitStrategy");
                MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(a.getStrategyId());
                isMemberLimitStrategy(memberLimitStrategy,num);
            }
            else if(a.getStrategyName().equals("CourseMemberLimitStrategy")){
                System.out.println("a CourseMemberLimitStrategy");
                CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(a.getStrategyId());
                isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList);
            }
            else if(a.getStrategyName().equals("ConflictCourseStrategy")){
                System.out.println("a ConflictCourseStrategy");
                List<ConflictCourseStrategy> conflictCourseStrategyList=teamDao.queryConflictCourseStrategy(a.getStrategyId());
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
                List<TeamAndStrategy> teamAndStrategies = teamDao.queryTeamAndStrategy(id_c);
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
                    List<TeamOrStrategy> teamOrStrategies=teamDao.queryTeamOrStrategy(id_c);
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
        List<TeamStrategy> teamStrategies=teamDao.queryTeamStrategy(courseId);

        for(TeamStrategy a: teamStrategies){
            if(a.getStrategyName().equals("TeamAndStrategy")){
                System.out.println("a TeamAndStrategy");
                Long id = a.getStrategyId();
                System.out.println("TeamAndStrategy: "+id);
                List<TeamAndStrategy> teamAndStrategies = teamDao.queryTeamAndStrategy(id);
                if(queryTeamAndStrategy(teamAndStrategies)!=null) return queryTeamAndStrategy(teamAndStrategies);
            }

            else if(a.getStrategyName().equals("TeamOrStrategy")) {
                System.out.println("a TeamOrStrategy");
                Long id = a.getStrategyId();
                List<TeamOrStrategy> teamOrStrategies = teamDao.queryTeamOrStrategy(id);
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
        return teamDao.queryMemberLimitStrategyById(id);
    }


}
