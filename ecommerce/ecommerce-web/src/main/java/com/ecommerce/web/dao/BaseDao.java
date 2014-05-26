/**
 * 
 */
package com.ecommerce.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

import com.ecommerce.model.BaseEntity;


/**
 * @author richard
 *
 */
public interface BaseDao<T extends BaseEntity> {
	
	public Object create(Object o);
	
	public void update(Object o);
	
	public void saveOrUpdate(Object o);
	
	public T getById(Serializable id);
    
    public List<T> findAll(Class<T> entityClass);
    
    public List<T> findByExample(Object o);
    
    public List<T> findByCriteria(Criteria criteria);
	
	public void delete(Object o);
    
    public void deleteById(Serializable id);    
    
}
