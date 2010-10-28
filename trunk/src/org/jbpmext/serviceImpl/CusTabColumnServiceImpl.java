package org.jbpmext.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.jbpmext.dao.CusTabColumnDao;
import org.jbpmext.dao.TableListDao;
import org.jbpmext.model.CusTabColumn;
import org.jbpmext.service.CusTabColumnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class CusTabColumnServiceImpl implements CusTabColumnService{
	@Resource
	private CusTabColumnDao cusTabColumnDao;
	
	
	public Serializable add(CusTabColumn cusTabColumn) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createMsqlTable(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public List<CusTabColumn> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CusTabColumn> findByHql() {
		// TODO Auto-generated method stub
		return null;
	}

	public CusTabColumn findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(CusTabColumn cusTabColumn) {
		// TODO Auto-generated method stub
		
	}

}
