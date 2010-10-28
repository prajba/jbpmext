package org.jbpmext.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.jbpmext.dao.CusTabAssorDirDao;
import org.jbpmext.model.CusTabAssorDir;
import org.jbpmext.service.CusTabAssorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CusTabAssorDirServiceImpl implements CusTabAssorService{
	@Resource
	private CusTabAssorDirDao cusTabAssorDirDao;
	public void delete(Integer cusTabId) {
	     this.cusTabAssorDirDao.delete(cusTabId);
		
	}

	public List<CusTabAssorDir> findAllCusTabAssorDirs() {
		return this.cusTabAssorDirDao.findAll();
	}

	public void findCusTabAssorDirByHql(String hql) {
		
		
	}

	public void findCusTabAssorDirById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public CusTabAssorDir login(CusTabAssorDir cusTabAssorDir) {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable registerUser(CusTabAssorDir cusTabAssorDir) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(CusTabAssorDir cusTabAssorDir) {
		// TODO Auto-generated method stub
		
	}

}
