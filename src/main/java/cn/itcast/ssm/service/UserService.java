package cn.itcast.ssm.service;

import cn.itcast.ssm.pojo.EasyUIResult;
import cn.itcast.ssm.pojo.User;

import java.util.List;

/**
 * Created by zm on 2017/8/7.
 */
public interface UserService {

    public EasyUIResult queryEasyUIResult(Integer pageNum, Integer pageSize);

    public void addUser(User user1, User user2) throws Exception;

    public boolean addUser(User user) throws Exception;

    public boolean deleteUser(List<Object> ids) throws Exception;

    public boolean updateUser(User user) throws Exception;
}
