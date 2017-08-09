package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.mapper.NewUserMapper;
import cn.itcast.ssm.pojo.EasyUIResult;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.UserService;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zm on 2017/8/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private NewUserMapper userMapper;


    public EasyUIResult queryEasyUIResult(Integer pageNum, Integer pageSize) {
        //查询方法调用之前,调用分页插件的静态方法,中间最好不要隔任何代码
        PageHelper.startPage(pageNum,pageSize);
        // 第一个参数是从那条开始，第二个参数是查询多少条
        //List<User> userList = this.userMapper.queryUsersByPage((pageNum-1)*pageSize, pageSize);
        //List<User> userList = this.userMapper.queryUserAll();
        List<User> userList = this.userMapper.select(null);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);

        EasyUIResult easyUIResult = new EasyUIResult();
        easyUIResult.setTotal(pageInfo.getTotal());
        easyUIResult.setRows(pageInfo.getList());

        return easyUIResult;
    }

    public void addUser(User user1, User user2) throws Exception {
       // this.userMapper.addUser(user1);
        this.userMapper.insert(user1);
        // 制造异常
        //int i=1/0;
        //this.userMapper.addUser(user2);
        this.userMapper.insert(user2);

    }

    public boolean addUser(User user) throws Exception {
        //int i = this.userMapper.addUser(user);
        int i = this.userMapper.insert(user);
        if (i>0){
            return true;
        }
        return false;
    }

    public boolean deleteUser(List<Object> ids) throws Exception {
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        int i = this.userMapper.deleteByExample(example);
        if (i>0){
            return true;
        }
        return false;
    }

    public boolean updateUser(User user) throws Exception {
        //int i = this.userMapper.updateUser(user);
        int i = this.userMapper.updateByPrimaryKeySelective(user);
        if (i>0){
            return true;
        }
        return false;
    }
}
