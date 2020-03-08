package com.study.controller;

import com.study.model.User;
import com.study.service.UserServiceInf;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;


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
        return "user_admin/file";
    }

    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file")MultipartFile file) throws IOException{
        if (!file.isEmpty()) {
            log.info("Process file:{}",file.getOriginalFilename());
        }
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\",System.currentTimeMillis()+file.getOriginalFilename()));
        return "succes";
    }

    @RequestMapping("/say")
    public String say(Model model) {
        model.addAttribute("name","wormday"); // 指定Model的值
        model.addAttribute("url","http://www.cnblogs.com/wormday/p/8435617.html"); // 指定Model的值
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("Oh,log is here.");
        return "helloworld";
    }
}

