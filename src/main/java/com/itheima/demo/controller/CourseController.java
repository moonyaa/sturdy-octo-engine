package com.itheima.demo.controller;

import com.itheima.demo.pojo.Course;
import com.itheima.demo.pojo.User;
import com.itheima.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/course")

public class CourseController {

    @Autowired
    private CourseService courseService;
    String prepath;

    //跳转登录页面
    @RequestMapping(value = "/tologin")
    public  String toLogginPage(){
        return "login";
    }

    //登录
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,Model model){

        User user=courseService.login(username,password);
        if(username==""){
            model.addAttribute("msg","用户名不能为空！");
        }else if(password==""){
            model.addAttribute("msg","密码不能为空！");
        }
        else if(user==null || !(user.getUsername().equals(username)) || ! (user.getPassword().equals(password))){
            model.addAttribute("msg","用户名或密码错误，请重新登录！");
        }else if ((user.getPassword().equals(password) )&& (user.getUsername().equals(username))){
            session.setAttribute("user_session", user);

            return "redirect:/course/getAllCourse";
        }
        return "login";
    }

    //退出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return  "redirect:tologin";
    }

    @RequestMapping(value = "/getAllCourse" ,method = RequestMethod.GET)
    public String getAllCourse(Model model) {
       List<Course> list = courseService.getAllCourse();
       model.addAttribute("list",list);
       return "course_list";
    }

   /** @RequestMapping(value = "/up",method = RequestMethod.POST)
    public String fileUp(MultipartFile images, HttpSession session,Course course) throws IOException {
        //获取上传的文件的文件名
        String fileName = images.getOriginalFilename();
        //获取上传的文件的后缀名
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        //拼接一个新的文件名
        fileName = uuid + hzName;
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取当前工程下photo目录的真实路径
        String photoPath = servletContext.getRealPath("photo");
        //创建photoPath所对应的File对象
        File files = new File(photoPath);
        //判断file所对应目录是否存在
        if(!files.exists()){
            files.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //上传文件
       images.transferTo(new File(finalPath));
        course.setImages(photoPath);
        courseService.addCourse(course);
        return "redirect:/course/getAllCourse";
    }*/

   @RequestMapping(value = "/up",method=RequestMethod.POST)
   public String addCourse(Course course, HttpServletRequest request) {

        courseService.alter();
        int state=courseService.addCourse(course,request);
        System.out.println(state);
        return "redirect:/course/getAllCourse";
   }



    @RequestMapping(value = "/updatecourse2")
    public String updateCourse(@RequestParam(value="file")MultipartFile logoImage,Course course,HttpSession request){
        int flag=0;
        System.out.println(2);
        System.out.println(course.getName());
        System.out.println(prepath);
         course.setLogoImage(logoImage);
        if(!(course.getLogoImage().isEmpty())) {
            String fileName = logoImage.getOriginalFilename();
            //选择了文件
            System.out.println(1);
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
                    logoImage.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            course.setImages(prepath);
        }
        courseService.updateCourse(course);

        return "redirect:/course/getAllCourse";
    }

    @RequestMapping(value = "/updatecourse1/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model){
        //根据id查询课程信息

        Course course =courseService.get(id);
        //将课程信息共享到请求域中
        System.out.println(course.getImages());
        prepath=course.getImages();
        model.addAttribute("Qcourse",course);
        //跳转到employee_update.jsp
        return "course_update";
    }

    @RequestMapping(value = "/deletecourse/{id}", method = RequestMethod.DELETE)
    public String deleteCourse(@PathVariable("id") Integer id){
        //删除信息
        courseService.deleteCourse(id);
        //重定向到列表功能：/employee
        return "redirect:/course/getAllCourse";
    }

}
