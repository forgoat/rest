package com.rest.service;

import com.rest.dao.CourseDao;
import com.rest.dao.TeamDao;
import com.rest.dao.TeamStudentDao;
import com.rest.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrganizeTeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    TeamStudentDao teamStudentDao;

    /**
     * 创建小组
     * @param team
     * @param teamValidApplication
     * @param klassStudent
     * @return
     */
    public int createTeam(Team team, KlassStudent klassStudent, TeamValidApplication teamValidApplication) {
        int count=teamDao.createTeam(team);
        teamDao.updateKlassStudent(klassStudent);
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
     * 判断是否合格
     * @param teamId
     * @param courseId
     * @return
     */
    public boolean isValid(Long teamId,Long courseId){
        List<Long> teamStudentList=teamStudentDao.queryByTeamId(teamId);
        int num=teamStudentList.size();

        List<TeamStrategy> teamStrategies=teamDao.queryTeamStrategy(courseId);

        for(TeamStrategy a: teamStrategies){
            if(a.getStrategyName().equals("TeamAndStrategy")){
                Long id = a.getStrategyId();
                List<TeamAndStrategy> teamAndStrategies = teamDao.queryTeamAndStrategy(id);

                for (TeamAndStrategy b : teamAndStrategies) {
                    if (b.getStrategyName().equals("TeamOrStrategy")) {
                        Long id_b=b.getStrategyId();
                        List<TeamOrStrategy> teamOrStrategies=teamDao.queryTeamOrStrategy(id_b);

                        for(TeamOrStrategy c: teamOrStrategies) {
                            if (c.getStrategyName().equals("MemberLimitStrategy")) {
                                MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(c.getStrategyId());
                                isMemberLimitStrategy(memberLimitStrategy,num);

                            } else if (c.equals("CourseMemberLimitStrategy")) {
                                CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(c.getStrategyId());
                                isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList);
                            }
                        }

                    } else if (b.equals("MemberLimitStrategy")) {
                        MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(b.getStrategyId());
                        isMemberLimitStrategy(memberLimitStrategy,num);

                    } else if (b.equals("CourseMemberLimitStrategy")) {
                        CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(b.getStrategyId());
                        isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList);

                    }
                }
            }

            else if(a.equals("TeamOrStrategy")) {
                Long id = a.getStrategyId();
                List<TeamOrStrategy> teamOrStrategies = teamDao.queryTeamOrStrategy(id);

                for (TeamOrStrategy b : teamOrStrategies) {
                    if (b.equals("TeamAndStrategy")) {
                        Long id_b = b.getStrategyId();
                        List<TeamAndStrategy> teamAndStrategies = teamDao.queryTeamAndStrategy(id_b);

                        for (TeamAndStrategy c : teamAndStrategies) {
                            if (c.equals("MemberLimitStrategy")) {
                                MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(c.getStrategyId());
                                isMemberLimitStrategy(memberLimitStrategy,num);

                            } else if (c.equals("CourseMemberLimitStrategy")) {
                                CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(c.getStrategyId());
                                isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList);

                            }
                        }

                    } else if (b.equals("MemberLimitStrategy")) {
                        MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(b.getStrategyId());
                        isMemberLimitStrategy(memberLimitStrategy,num);

                    } else if (b.equals("CourseMemberLimitStrategy")) {
                        CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(b.getStrategyId());
                        isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList);

                    }
                }
            }

            else if(a.equals("MemberLimitStrategy")){
                MemberLimitStrategy memberLimitStrategy=teamDao.queryMemberLimit(a.getStrategyId());
                isMemberLimitStrategy(memberLimitStrategy,num);

            }
            else if(a.equals("CourseMemberLimitStrategy")){
                CourseMemberLimitStrategy courseMemberLimitStrategy=teamDao.queryCourseMemberLimitStrategy(a.getStrategyId());
                isCourseMemberLimitStrategy(courseMemberLimitStrategy,teamStudentList);

            }
            else if(a.equals("ConflictCourseStrategy")){
                List<ConflictCourseStrategy> conflictCourseStrategyList=teamDao.queryConflictCourseStrategy(a.getStrategyId());
                isConflictCourseStrategy(conflictCourseStrategyList,teamStudentList,courseId);
            }
        }

        return true;
    }

}
