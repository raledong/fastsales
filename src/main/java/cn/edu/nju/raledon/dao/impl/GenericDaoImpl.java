package cn.edu.nju.raledon.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.nju.raledon.dao.GenericDao;
import javassist.bytecode.Descriptor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



/**
 * genericDao接口的泛型实现 为抽象类
 * @author rale
 *
 * @param <T>
 * @param <ID>
 */
@Transactional
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {
	private Class<T> persistentClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDaoImpl(Class<T> persistentClass){
		this.persistentClass = persistentClass;
	}
	
	public Class<T> getPersistentClass(){
		return this.persistentClass;
	}
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}
	
	public Session currentSession(){
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 根据id查找相应实体
	 * @param id
	 * @return entity T
	 */
	@Override
	public T findById(ID id){
		Session session = this.currentSession();
		T entity = (T)session.get(persistentClass, id);
		return entity;
	}
	
	/**
	 * 添加数据至数据库
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ID add(T entity){
		return (ID)currentSession().save(entity);
	}
	
	/**
	 * 更新数据库数据
	 * @param entity
	 */
	@Override
	public void update(T entity){
		currentSession().update(entity);;
	}
	
	/**
	 * 删除数据库数据
	 * @param id
	 */
	@Override
	public void delete(ID id){
		T entity = findById(id);
		Session session = currentSession();
		session.delete(entity);
	}
	
	
	/**
	 * 获得所有实例
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(){
		Session session = this.currentSession();
		List<T>  entityList = (List<T>)session.createCriteria(persistentClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return entityList;
	}
	
	/**
	 * 及时提交变更
	 */
	public void flush(Session session){
		if(session != null){
			session.flush();
		}
	}
	
	/**
	 * 及时清理session
	 * @param session
	 */
	public void clear(Session session){
		if(session != null)
			session.clear();
	}
	
	/**
	 * 使用hql语句进行查询
	 * @param hql String
	 * @return List<entity>
	 */
	@SuppressWarnings("unchecked")
	public List<T> HQLQuery(String hql){
		return (List<T>)currentSession().createQuery(hql).list();
	}

    /**
     * 通过指定column的值获得相应的查询结果
     * @param queryString
     * @param queryParams
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedParam(String queryString, Map<String, Object> queryParams, int startIndex, int count){
        Query query = currentSession().createQuery(queryString);
        setQueryParameters(query, queryParams);
        query.setFirstResult(startIndex);
        query.setMaxResults(count);
        return query.list();
	}

    /**
     * 设定多个column值获得相应的查询结果
     * @param queryString
     * @param queryParams
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedParam(String queryString, Map<String, Object> queryParams) {
		Query query = currentSession().createQuery(queryString);
        setQueryParameters(query, queryParams);
		return (List<T>)query.list();
	}

	public List<T> findByNamedQueryAndParams(String queryName, Map<String, Object> queryParams){
        Query query = currentSession().getNamedQuery(queryName);
        setQueryParameters(query, queryParams);
        return query.list();
    }

    public List<T> findByNamedQueryAndParams(String queryName, Map<String, Object> queryParams, int startIndex, int count){
        Query query = currentSession().getNamedQuery(queryName);
        setQueryParameters(query, queryParams);
        query.setFirstResult(startIndex);
        query.setMaxResults(count);
        return query.list();
    }

    private void setQueryParameters(Query query, Map<String, Object> queryParams){
        if (query!=null && queryParams!=null && queryParams.size()>0){
            Iterator i = queryParams.keySet().iterator();
            while (i.hasNext()){
                String key = (String)i.next();
                query.setParameter(key, queryParams.get(key));
            }
        }
    }

}
