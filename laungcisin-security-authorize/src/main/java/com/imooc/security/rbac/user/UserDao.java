package com.imooc.security.rbac.user;

import com.imooc.security.rbac.permissioin.Permission;
import com.imooc.security.rbac.role.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findUserByUsername(String username) {
        logger.info("查询用户信息");

        String sql = "select * from user where username = ? limit 1";
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }, username);

        return user;
    }

    public List<Role> findRolesByUsername(String username) {
        String sql = "        select r.* from user u, user_role ur, role r " +
                "				where u.user_id = ur.user_id and ur.role_id = r.role_id and u.username = ? ";
        return jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<Role>(Role.class));
    }

    public List<Permission> findPermissionsByUsername(String username) {
        String sql = "        select p.*" +
                "        from user u, user_role ur, role r, role_permission rp, permission p" +
                "        where u.user_id = ur.user_id" +
                "        and ur.role_id = r.role_id" +
                "        and r.role_id = rp.role_id" +
                "        and rp.permission_id = p.permission_id" +
                "        and u.username = ?";
        return jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<Permission>(Permission.class));
    }

    public List<Map<String, Object>> findPermissionsByPidAndUsername(String username, String pid) {
        String sql = "        select" +
                "               p.permission_id as id," +
                "               p.name as title," +
                "               p.icon," +
                "               p.url as href," +
                "               false as spread" +
                "        from user u, user_role ur, role r, role_permission rp, permission p" +
                "        where u.user_id = ur.user_id" +
                "        and ur.role_id = r.role_id" +
                "        and r.role_id = rp.role_id" +
                "        and rp.permission_id = p.permission_id" +
                "        and u.username = ? and p.pid = ? ";
        return jdbcTemplate.queryForList(sql, username, pid);
    }
}
