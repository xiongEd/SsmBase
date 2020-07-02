package com.study.controller;

import com.mysql.cj.util.StringUtils;
import com.study.DataCollection.MyCollection;
import com.study.mapper.ProvinceMapper;
import com.study.model.Province;
import com.study.model.User;
import com.study.service.ProvinceServiceInf;
import com.study.service.UserServiceInf;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.UUID;


@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    private static Logger log=LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserServiceInf userService;

    // /user/{id}
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public @ResponseBody
    User getUserInJson(@PathVariable String id, Map<String, Object> model){
        int userId = Integer.parseInt(id);
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);
        log.info(user.toString());
        return user;
    }

    @Autowired
    private MyCollection myCollection;

    @Autowired
    ProvinceMapper provinceMapper;

    private String url = "http://guolin.tech/api/china";

    @RequestMapping(value="/china",method=RequestMethod.GET, produces = "text/html; charset=utf-8")
    public @ResponseBody
    String   getChinaJson1(Map<String, Object> model){
        ObjectMapper objectMapper = new ObjectMapper();


        String temp = this.myCollection.analysisUrl(url);
        Reader read = new StringReader(temp);

        Province province = new Province();
        try {
            province = objectMapper.readValue(read, Province.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug(temp);
        return temp;
    }

    @RequestMapping(value="/china/{id}",method=RequestMethod.GET, produces = "text/html; charset=utf-8")
    public @ResponseBody
    String  getChinaJson2(@PathVariable String id, Map<String, Object> model){
        int userId = Integer.parseInt(id);
        System.out.println("userId:"+userId);
        return this.myCollection.analysisUrl(url + '/' + userId);
    }

    @RequestMapping(value="/china/{id}/{id2}",method=RequestMethod.GET, produces = "text/html; charset=utf-8")
    public @ResponseBody
    String  getChinaJson3(@PathVariable("id")String id, @PathVariable("id2") String id2,  Map<String, Object> model){
        int userId = Integer.parseInt(id);
        int userId2 = Integer.parseInt(id2);
        System.out.println("userId:"+userId);
        return this.myCollection.analysisUrl(url + '/' + userId + '/' + userId2);
    }

    // /user/{id}
    @RequestMapping(value="/jsontype/{id}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User>  getUserInJson2(@PathVariable String id,Map<String, Object> model){
        int userId = Integer.parseInt(id);
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);
        log.info(user.toString());
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    //文件上传、
    @RequestMapping(value="/upload")
    public String showUploadPage(){
        return "/WEB-INF/file/down.txt";
    }

    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
    @ResponseBody
    public String doUploadFile(@RequestParam("file")MultipartFile file) throws IOException{
        if (!file.isEmpty()) {
            log.info("Process file:{}",file.getOriginalFilename());
        }
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/WEB-INF/file/",System.currentTimeMillis()+file.getOriginalFilename()));
        return "succes";
    }

    @RequestMapping("/say")
    public String say(Model model) {
        model.addAttribute("name","wormday"); // 指定Model的值
        model.addAttribute("url","http://www.cnblogs.com/wormday/p/8435617.html"); // 指定Model的值
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("Oh,log is here.");
        return "你好";
    }

    @RequestMapping("/insert")
    public String insert(Model model) {
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("Oh,log is here.");
        return "helloworld";
    }
}

