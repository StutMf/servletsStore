package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Category;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate template;

    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT * FROM category WHERE id=?";

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM category";

    //language=SQL
    private final String SQL_SAVE = "INSERT INTO category (name) VALUES (?)";

    //language=SQL
    private final String SQL_DELETE = "DELETE FROM category WHERE id=?";

    private RowMapper<Category> categoryRowMapper = (r, i) -> Category.builder()
            .id(r.getLong("id"))
            .name(r.getString("name"))
            .build();

    public CategoryRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Category findById(long id) {
        try {
            return template.queryForObject(SQL_FIND_BY_ID, categoryRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        try {
            return template.query(SQL_FIND_ALL, categoryRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(Category category) {
        template.update(SQL_SAVE, category.getName());
    }

    @Override
    public void delete(long id) {
        template.update(SQL_DELETE, id);
    }
}
