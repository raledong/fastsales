package cn.edu.nju.raledon.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 这个接口用于实现基本的增删改查工作，用不同的entity可以将其初始化
 * @author rale
 *
 * @param <T>
 * @param <ID>
 */

public interface GenericDao<T, ID extends Serializable>{

	/**
	 * 添加数据至数据库
	 * @param entity
	 */
	public ID add(T entity);


	/**
	 * 更新数据库数据y
	 * @param entity
	 */
	public void update(T entity);


	/**
	 * 删除数据库数据
	 * @param id
	 */
	public void delete(ID id);


	/**
	 * 根据id查找相应实体
	 * @param id
	 * @return entity T
	 */
	public T findById(ID id);


	/**
	 * 获得所有实例
	 * @return List<T>
	 */
	public List<T> findAll();

}
