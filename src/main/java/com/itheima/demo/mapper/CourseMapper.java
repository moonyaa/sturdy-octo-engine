package com.itheima.demo.mapper;

import com.itheima.demo.pojo.Course;
import com.itheima.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import static com.alibaba.druid.util.FnvHash.Constants.AUTO_INCREMENT;

public interface CourseMapper {

    List<Course> getAllCourse();

    //int addCourse(Course course);
     int addCourse(Course course);

    int updateCourse(Course course);

    Course get(Integer id);

    String deleteCourse(Integer id);

    @Update("alter table s_student AUTO_INCREMENT=1;")
    int alter();

    User login(@Param("username") String username, @Param("password") String password);

   // int save(Course course);

}
