package org.jbpmext.dao;

/**
 *  该类为hibernaeDao的基础类，所以其它模块的类的dao都集成该接口
 *  @author cheafen
 *  @version 1.0 05/30/2007
 *  @since 1.0
 */
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T,ID extends Serializable> {
	/**
	 * <strong>根据对象id获得一个对象</strong>
	 * @param id 对象主键id
	 * @return 返回一个对象类型
	 */
	T loadObjectById(ID id);	
	/**
	 * <strong>保存一条记录</strong>
	 * @param entity 一个对象实体
	 * @return 返回保存的对象实体
	 */
	T save(T entity);
	
	/**
	 * <strong>保存一条新的记录或者跟新一条新的记录</strong>
	 * @param entity 一个对象实体
	 * @return 返回保存/更新的对象实体
	 */
	T saveOrUpdate(T entity);
	/**
	 * <strong>更新一条记录</strong>
	 * @param entity 一个对象实体
	 * @return 返回更新的对象实体
	 */
	T update(T entity);

	/**
	 * <strong>执行del 或者 update语句 </strong>
	 * <li> delete from com.jpc.model.JpcUser us  where us.id>0
	 * <li> update com.jpc.model.JpcUser us set us.ucode = 'admin' where id in (0,1,2,3,4)
	 * @param hql
	 */
	void executeDelOrUpdateHql(String hql);
	/**
	 * <strong>删除一个对象实体</strong>
	 * @param entity 对象实体
	 */
	void delete(T entity);
	
	/**
	 * <strong>查询出所有的对象集合</strong>
	 * @return 返回一个对象实体的集合
	 */
	List<T> findAll();
	
	/**
	 * <strong>根据条件列出对象实体集合</strong>
	 * @param cmd可以是HQL/SQL语句 也可以是XML中配置的HQL语句的查询相关关键字  
	 * <li> 例如: from com.jpc.model.JpcUser as p where p.ucode = ?
	 * <li> 例如: findUserBycode 这里HQL语句在XML中声明
	 * <li> 例如: select JPC_JPCUSER as p where p.ucode = ? //前提该表必须有相应的po
	 * @param page 显示的第几个页面
	 * @param count 一共返回的条数
	 * @param params 参数数组
	 * @return 返回集合为count条记录的集合
	 */
	List<T> findListByCmd(String cmd ,int page,int count,Object... params);
	
	/**
	 * <strong>根据动态querySql来列出对象实体集合-适合对象为动态结构的数据表</strong>
	 * <li> 例如: select did,ucode,uname,email或者* from D_FILE1 as u where u.uname = '李春雨' //表名为数据库中的名称 返回单个对象为Map的list
	 * <li> 注意: map 获取值必须大写 即: map.get("TITLE")
	 * @param querySql 查询语句
	 * @param page 显示的第几页面
	 * @param count 集合的条数 每页显示的条目数
	 * @return 返回集合为count条记录的集合
	 * 注意: 查询字段中不能存在大字段类型
	 */
	List<Map> findMapListByDynSql(String querySql,int page,int count);
	
	/**
	 * <strong>返回实体类对象/或者单个对象例如Max/min等</strong>
	 * 根据cmd对应语句查询可以执行如下操作：count、max、min、sum、avg 
	 * <li> from net.vscholl.user.User as user where user.userCode = '刘玉华' and ...
	 * <li> from com.jpc.model.JpcUser as p where p.ucode = ?
	 * <li> select max(did) from d_file1 where title like '%我们%'
	 * <li>count: select count(*) from net.vschool.user.User as user 
	 * <li>max: select max(user.id) from net.vschool.user.User as user
	 * <li>min: select min(user.id) from net.vschool.user.User as user
	 * <li>sum: select sum(user.id) from net.vschool.user.User as user
	 * <li>avg: select avg(user.id) from net.vschool.user.User as user
	 * <li> 返回一条记录<font color=red>obj对象</font>的时候 object 可以强制转换成Object[] 数组,保存该记录内多个key-value
	 * <li> 例如: select * from JPC_USER as u where u.did = 2 可强制转换成Object[]
	 * <li> 根据cmd对应语句查询可以执行如下操作：count、max、min、sum、avg 这个时候则是Object对象
	 * <li> 例如: select MAX(did) from JPC_USER as u where u.did = 2 返回为Object 不可转换为数组
	 * @param querySql 可以是xml中定义的query Name 也可以是Hql语句 也可以是 SQL语句
	 * @param params ? 的数组对象 用来简化sql
	 * @return 单条记录或者cout . max obj等等
	 * 注意: 如果返回结果不是实体对象的情况下,返回为数组 则返回结果中(即查询字段中)不能存在大字段类型
	 */
	Object queryForCmd(String querySql , Object... params);
	
	/**
	 * 获取单条记录对象(Map)
	 * @param querySql sql/hql/qname
	 * @param params 参数
	 * @return 单条记录的Map对象
	 * 注意: 查询字段中不能存在大字段类型
	 */
	Object queryForSingleResultMap(String querySql , Object... params);
	
	
	/**
	 * 创建表单 
	 * 
	 * @param  tablename  //表名称
	 * @param  zdlist   //字段名称
	 */
	
	boolean createTable(String tablename,List<String> zdlist);
	
	
}