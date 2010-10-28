package org.jbpmext.service;

import java.io.Serializable;

import org.jbpmext.model.User;


public interface UserService {

	public Serializable registerUser(User user);
	public User login(User user);
	public void update(User user);
	public void delete(User user);
	public void findUserById(Long id);
	public void findAllUsers();
	public void findUsersByHql(String hql);
	public boolean saveTable(String formname, String tablename, String textArea) throws Exception;
}
