package practice.service.impl;

import practice.dao.UserDao;
import practice.dao.impl.UserDaoImpl;
import practice.domain.PageBean;
import practice.domain.User;
import practice.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao=new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.fingUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    /**
     * Store user
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    /**
     * Delete user
     *
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    /**
     * Find user by id
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    /**
     * Update user
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    /**
     * Sign up
     *
     * @param user
     */
    @Override
    public void signup(User user) {
        dao.signup(user);
    }

    /**
     * Delete Selected
     *
     * @param ids
     */
    @Override
    public void delSelectedUser(String[] ids) {

        if(ids!=null&&ids.length>0){
            for(String id:ids){
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    /**
     * Find user by page
     *
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);


        int totalCount=dao.findTotalCount(condition);
        int totalPage = (totalCount%rows)==0?totalCount/rows:(totalCount/rows)+1;

        if(currentPage<=0){
            currentPage=1;
        }

        if(currentPage>=totalPage){
            currentPage=totalPage;
        }
        int start=(currentPage-1)*rows;
        PageBean<User> pb=new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);


        pb.setTotalCount(totalCount);


        List<User> list=dao.findByPage(start,rows,condition);
        pb.setList(list);


        pb.setTotalPage(totalPage);
        return pb;
    }


}
