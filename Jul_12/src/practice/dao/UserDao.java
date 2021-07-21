package practice.dao;

import practice.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public List<User> findAll();

    public User fingUserByUsernameAndPassword(String username, String password);

    public void add(User user);

    public void delete(int id);

    User findUserById(int id);

    void update(User user);

    void signup(User user);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

}
