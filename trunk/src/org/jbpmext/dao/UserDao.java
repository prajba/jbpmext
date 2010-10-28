package org.jbpmext.dao;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.model.User;


public interface UserDao {
	public Serializable add(User user);
	public User findById(Long id);
	public void delete(Long id);
	public void update(User user);
	public List<User> findAll();
	public List<User> findByHql();
	public boolean createMsqlTable(String sql);
}
