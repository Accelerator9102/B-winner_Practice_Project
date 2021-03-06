package practice.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import practice.dao.UserDao;
import practice.domain.User;
import practice.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User fingUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getPhone(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id=?";
        template.update(sql, id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id=?";

        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;


    }

    @Override
    public void update(User user) {
        String sql = "update user set name=?,gender=?,age=?,address=?,phone=?,email=? where id=?";

        template.update(sql, user.getName(), user.getGender(),
                user.getAge(), user.getAddress(), user.getPhone(), user.getEmail(),user.getId());
    }

    @Override
    public void signup(User user) {
        String sql="insert into user values(null,?,null,null,null,null,null,?,?)";
        template.update(sql,user.getName(),user.getUsername(),user.getPassword());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {

        String sql="select count(*) from user where 1=1";

        StringBuilder sb=new StringBuilder(sql);

        Set<String> keySet = condition.keySet();

        List<Object> params = new ArrayList<Object>();
        for (String key:keySet) {
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value=condition.get(key)[0];
            if(!value.equals("")&&value!=null){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }


        return template.queryForObject(sb.toString(), Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql="select * from user where 1=1";

        StringBuilder sb=new StringBuilder(sql);

        Set<String> keySet = condition.keySet();

        List<Object> params = new ArrayList<Object>();
        for (String key:keySet) {
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value=condition.get(key)[0];
            if(!value.equals("")&&value!=null){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }

        sb.append(" limit ?, ?");
        params.add(start);
        params.add(rows);


        sql=sb.toString();

        System.out.println(sql);
        System.out.println(params);

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }


















}
