package cn.itcast.ssm.controller;

import cn.itcast.ssm.pojo.EasyUIResult;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.UserService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zm on 2017/8/7.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("users")
    public String toUsers(){
        return "users";
    }

    @ResponseBody
    @RequestMapping("list")
    public EasyUIResult queryUsersByPage(@RequestParam("page") Integer pageNum, @RequestParam("rows")Integer pageSize){

        EasyUIResult result = this.userService.queryEasyUIResult(pageNum, pageSize);
        return result;
    }

    //通用跳转页面
    @RequestMapping("/page/{pageName}")
    public String toUserAdd(@PathVariable("pageName")String pageName){
        return pageName;
    }

    //添加用户
    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> save(User user){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            boolean b=false;
            if (user.getId()!=null){
                b = this.userService.updateUser(user);
            }else {
                b = this.userService.addUser(user);
            }
            if (b){
                map.put("status",200);
            }else {
                map.put("status",500);
            }
        } catch (Exception e) {
            map.put("status",500);
            e.printStackTrace();
        }
        return  map;
    }

    //删除用户
    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("ids")List<Object> ids){
        Map<String,Object> map = new HashMap<String, Object>();

        try {
            boolean b = this.userService.deleteUser(ids);
            if (b){
                map.put("status",200);
            }else {
                map.put("status",500);
            }
        } catch (Exception e) {
            map.put("status",500);
            e.printStackTrace();
        }
        return  map;
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
