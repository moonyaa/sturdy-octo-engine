package com.itheima.demo.service;

import com.itheima.demo.pojo.Course;
import com.itheima.demo.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
   // int addCourse(Course course);
   int  addCourse(Course course, HttpServletRequest request);
    int updateCourse(Course course);
    Course get(Integer id);
    String deleteCourse(Integer id);
    int alter();

    User login(String username ,String password);




}
