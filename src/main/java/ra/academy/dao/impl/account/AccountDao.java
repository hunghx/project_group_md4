package ra.academy.dao.impl.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ra.academy.dao.IAccountDao;
import ra.academy.model.Account;
import ra.academy.model.Catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountDao implements IAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findByUserName(String username) {
        String sql = "select * from account where email = '"+username+"'";
        return jdbcTemplate.query(sql, rs -> {
            Account acc = null;
            if(rs.next()){
                acc = new Account();
                acc.setId(rs.getLong("id"));
                acc.setFullName(rs.getString("full_name"));
                acc.setPhone(rs.getString("phone"));
                acc.setAddress(rs.getString("address"));
                acc.setEmail(rs.getString("email"));
                acc.setAvatarUrl(rs.getString("avatar"));
                acc.setStatus(rs.getBoolean("status"));
                acc.setRole(rs.getBoolean("role"));
                acc.setPassword(rs.getString("password"));
            }
            return acc;
        });
    }

    @Override
    public List<Account> findAll(int limit, int offset) {
        String sql = "select * from account limit "+limit+" offset "+offset+"";
        List<Account> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Account acc = new Account();
                    acc.setId(rs.getLong("id"));
                    acc.setFullName(rs.getString("full_name"));
                    acc.setPhone(rs.getString("phone"));
                    acc.setAddress(rs.getString("address"));
                    acc.setEmail(rs.getString("email"));
                    acc.setAvatarUrl(rs.getString("avatar"));
                    acc.setStatus(rs.getBoolean("status"));
                    acc.setRole(rs.getBoolean("role"));
                    return acc;
                });
        return list;
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        List<Account> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Account acc = new Account();
                    acc.setId(rs.getLong("id"));
                    acc.setFullName(rs.getString("full_name"));
                    acc.setPhone(rs.getString("phone"));
                    acc.setAddress(rs.getString("address"));
                    acc.setEmail(rs.getString("email"));
                    acc.setAvatarUrl(rs.getString("avatar"));
                    acc.setStatus(rs.getBoolean("status"));
                    acc.setRole(rs.getBoolean("role"));
                    return acc;
                });
        return list;
    }

    @Override
    public Account findById(Long id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public int save(Account account) {
        String sql = null;
        if (account.getId() == null) {
            // thêm mới
            sql = "insert into account(full_name, email, password, phone, address, avatar) values (?,?,?,?,?,?)";
            return jdbcTemplate.update(sql,account.getFullName(),account.getEmail(),account.getPassword(),account.getPhone(),account.getAddress(),account.getAvatarUrl());
        } else {
            sql = "update account set full_name=?, email=?, phone=?, address=?, avatar=? where id =? ";
            return jdbcTemplate.update(sql,account.getFullName(),account.getEmail(),account.getPhone(),account.getAddress(),account.getAvatarUrl(),account.getId());
        }
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from account where id = ?";
        return jdbcTemplate.update(sql,id);
    }
}
