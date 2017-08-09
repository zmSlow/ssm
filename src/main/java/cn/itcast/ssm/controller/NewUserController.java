package cn.itcast.ssm.controller;

import cn.itcast.ssm.pojo.EasyUIResult;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.UserService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zm on 2017/8/7.
 * 使用restful重写crud
 */
@Controller
@RequestMapping("rest/user")
public class NewUserController {
    @Autowired
    private UserService userService;


    //添加用户
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(User user) throws Exception {
        try {
            if (user.getUserName()==null || user.getUserName()==""){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            boolean b = this.userService.addUser(user);
            if (b){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //修改用户
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(User user){
        try {
            if(user.getId()==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Boolean b = this.userService.updateUser(user);
            if(b){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    //删除用户
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam("ids")List<Object> ids){
        try {
            if(ids==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Boolean b = this.userService.deleteUser(ids);
            if(b){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    //导出
    @RequestMapping("/export/excel")
    public String  export(Model model,
                         @RequestParam("page") Integer pageNum,
                         @RequestParam("rows")Integer pageSize){
        EasyUIResult result = this.userService.queryEasyUIResult(pageNum, pageSize);
        model.addAttribute("userList",result.getRows());

        return "excelView";
    }

}
