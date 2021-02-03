package castudy.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<T> {

        public void insert(T element) throws SQLException;

        public T selectOne(int id);

        public List<T> selectAll();

        public boolean delete(int id) throws SQLException;

        public boolean update(T element) throws SQLException;
}
