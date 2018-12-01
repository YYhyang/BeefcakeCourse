package com.example.demo.Controller;


import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
@RequestMapping(value = "/User/Login",method = RequestMethod.POST)
public String login(@RequestParam("account")String account,@RequestParam("password")String password)
{
    return userService.getRole(account,password);
}

    @RequestMapping("/hello")
    public String index()
    {
        return"Hello World";
    }
}
