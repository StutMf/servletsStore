package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.User;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SAVE = "INSERT INTO users (firstname, lastname, email, password) VALUES (?,?,?,?)";

    //language=SQL
    private final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";

    //language=SQL
    private final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    //language=SQL
    private final String FIND_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email=? AND password=?";

    private RowMapper<User> userRowMapper = (r, i) -> User.builder()
            .id(r.getLong("id"))
            .firstname(r.getString("firstname"))
            .lastname(r.getString("lastname"))
            .email(r.getString("email"))
            .password(r.getString("password"))
            .role(r.getString("role"))
            .build();

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(User user) {
        return jdbcTemplate.update(SQL_SAVE, user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword()) > 0;
    }

    @Override
    public User findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_EMAIL, userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_EMAIL_AND_PASSWORD, userRowMapper, email, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
