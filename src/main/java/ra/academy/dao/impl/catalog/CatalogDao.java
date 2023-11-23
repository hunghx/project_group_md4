package ra.academy.dao.impl.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ra.academy.dao.ICatalogDao;
import ra.academy.model.Catalog;

import java.util.List;
@Repository
public class CatalogDao implements ICatalogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    public CatalogDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public List<Catalog> findAll(int limit, int offset) {
        String sql = "select * from catalog limit "+limit+" offset "+offset+"";
        List<Catalog> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Catalog cat = new Catalog();
                    cat.setId(rs.getLong("id"));
                    cat.setName(rs.getString("name"));
                    cat.setDescription(rs.getString("description"));
                    return cat;
                });
        return list;
    }
    @Override
    public List<Catalog> findAll() {
        String sql = "select * from catalog";
        List<Catalog> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Catalog cat = new Catalog();
                    cat.setId(rs.getLong("id"));
                    cat.setName(rs.getString("name"));
                    cat.setDescription(rs.getString("description"));
                    return cat;
                });
        return list;
    }

    @Override
    public Catalog findById(Long id) {
        String sql = "select * from catalog where id =?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Catalog.class));
    }

    @Override
    public int save(Catalog catalog) {
        String sql = null;
        if (catalog.getId() == null) {
            // thêm mới
            sql = "Insert into catalog(name,description) values(?,?)";
            return jdbcTemplate.update(sql, catalog.getName(), catalog.getDescription());
        } else {
            sql = "update catalog set name=?,description=? where id =? ";
            return jdbcTemplate.update(sql, catalog.getName(), catalog.getDescription(), catalog.getId());
        }
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from catalog where id =?";
        return jdbcTemplate.update(sql, id);
    }
}
