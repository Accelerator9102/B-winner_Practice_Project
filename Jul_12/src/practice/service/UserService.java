package practice.service;

import practice.domain.PageBean;
import practice.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * Search all users
     * @return
     */
    List<User> findAll();

    /**
     * Log in
     * @param user
     * @return
     */
    User login(User user);

    /**
     * Store user
     * @param user
     */
    void addUser(User user);

    /**
     * Delete user
     * @param id
     */
    void deleteUser(String id);

    /**
     * Find user by id
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * Update user
     * @param user
     */
    void updateUser(User user);

    /**
     * Sign up
     * @param user
     */
    void signup(User user);

    /**
     * Delete Selected
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     * Find user by page
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
