package com.corey.springboot_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class Application {
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
    @RequestMapping("/manage")
    public String manage() {
        return "manage";
    }
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser( @RequestParam(value="port",required = false) String port,@RequestParam("passwd") String passwd, Model model) {
    	String ret = Sender.sendAddUser(port, passwd);
    	 model.addAttribute("name", "addport: " +  port + " password: " + passwd + " " + ret);
    	return "json";
    }
    @RequestMapping(value = "/removeUser",method = RequestMethod.POST)
    public String removeUser( @RequestParam(value="port",required = false) String port, Model model) {
    	String ret = Sender.sendRemoveUser(port);
    	 model.addAttribute("name", "remove " + ret);
    	return "json";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost() {
        return "Login Post Request";
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}