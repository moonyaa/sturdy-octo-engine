package com.itheima.demo.service.impl;

import com.itheima.demo.mapper.CourseMapper;
import com.itheima.demo.pojo.Course;
import com.itheima.demo.pojo.User;
import com.itheima.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
   @Override
    public List<Course> getAllCourse(){
       return courseMapper.getAllCourse();
   }
  // @Override
    //public int addCourse(Course course){return  courseMapper.addCourse(course); }
    @Override
    public int updateCourse(Course course){return  courseMapper.updateCourse(course);}
    @Override
    public Course get(Integer id){return  courseMapper.get(id);}
    @Override
    public  String deleteCourse(Integer id){return  courseMapper.deleteCourse(id);}
    public int alter(){return  courseMapper.alter();}



    @Override
    public int addCourse(Course course, HttpServletRequest request) {
            if(course.getLogoImage()!=null) {
                String fileName = course.getLogoImage().getOriginalFilename();
                //选择了文件
                if (fileName.length() > 0) {
                    String realpath = request.getServletContext().getRealPath("/photo");
                    //实现文件上传
                    String fileType = fileName.substring(fileName.lastIndexOf('.'));
                    //防止文件名重名
                    String newfilename = UUID.randomUUID().toString() + fileType;
                    course.setImages("photo/" + newfilename);
                    File targetFile = new File(realpath, newfilename);
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                    //上传
                    try {
                        course.getLogoImage().transferTo(targetFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        return courseMapper.addCourse(course);


    }


    //@Override
    //public int save(Course course){return  courseMapper.save(course);}

    @Override
    public User login(String username ,String password){
       return courseMapper.login(username,password);
    }


}
