package cn.itcast.ssm.mapper;

import cn.itcast.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zm on 2017/8/7.
 */
public interface UserMapper {

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */

    public User queryUserById(Long id);


    /**
     * 分页查询用户信息
     * @param start
     * @param pageSize
     */
    public List<User> queryUsersByPage(@Param("start")Integer start, @Param("pageSize")Integer pageSize);


    /**
     * 查询所有用户
     * @return
     */
    public List<User> queryUserAll();

    /**
     * 新增用户
     * @param user
     * @return
     * @throws Exception
     */
    public int addUser(User user) throws Exception;

    public int deleteUser(@Param("ids") String[] ids) throws Exception;

    public int updateUser(User user) throws Exception;
}
