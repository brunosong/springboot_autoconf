package com.brunosong.springbootautoconf.repository;

import com.brunosong.springbootautoconf.Hello;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class HelloRepositoryJdbc implements HelloRepository{

    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {

        try {
            Hello hello = jdbcTemplate.queryForObject("select * from bruno where name='" + name + "'",
                    (rs, rowNum) -> {
                        return new Hello(rs.getString("name"), rs.getInt("count"));
                    });

            return hello;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if(hello == null) jdbcTemplate.update("insert into bruno (name, count) values (?,?)" , name, 1);
        else jdbcTemplate.update("update bruno set count =? where name = ?", hello.getCount() + 1, name);
    }
}
