package ru.slavers9.springboottest.repositiries;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.slavers9.springboottest.dto.UserDto;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<UserDto> userDtoRowMapper = (r, i) -> {
        UserDto rowObject = new UserDto();
        rowObject.setId(Long.valueOf(r.getInt("id")));
        rowObject.setName(r.getString("name"));
        rowObject.setAge(r.getInt("age"));
        return rowObject;
    };

    public List<UserDto> getAllUsers() {
        String sqlQuery = "SELECT * FROM users";
        return jdbcTemplate.query(sqlQuery, userDtoRowMapper);
    }

    public UserDto getUserByID(Long id) {
        String sqlQuery = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, userDtoRowMapper, id);
    }

    public void addUser(UserDto user) {
        String sqlQuery = "INSERT INTO users(name, age) VALUES (?, ?)";
        jdbcTemplate.update(sqlQuery, user.getName(), user.getAge());
    }

    public void updateUser(UserDto user) {
        String sqlQuery = "UPDATE users SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, user.getName(), user.getAge(), user.getId());
    }

    public void removeUser(Long id) {
        String sqlQuery = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
