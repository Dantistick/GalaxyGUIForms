package DAO;

import java.sql.SQLException;

public interface DAO<T> {
    void getAll() throws SQLException;

    void insert() throws SQLException;

    void update() throws SQLException;

    void delete() throws SQLException;
}