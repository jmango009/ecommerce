/**
 * 
 */
package com.ecommerce.web.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.util.Assert;

import com.ecommerce.core.exception.ECRuntimeException;
import com.ecommerce.model.BaseEntity;
import com.ecommerce.web.dao.BaseDao;
import com.ecommerce.web.dao.BaseDaoSupport;

/**
 * @author richard
 *
 */
public class BaseDaoImpl<T extends BaseEntity> extends BaseDaoSupport implements BaseDao<T> {
	
	private static final  Logger logger = Logger.getLogger(BaseDaoImpl.class);
	
	protected Class<T> entityClass;
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public BaseDaoImpl() {
		this.entityClass = getGenericParameterizedTypeClass();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getGenericParameterizedTypeClass() {
		@SuppressWarnings("rawtypes")
		Class currentClass = getClass();
		
		while (currentClass != null) {
			Type classType = currentClass.getGenericSuperclass();
			if (classType instanceof ParameterizedType) {
				return (Class<T>) ((ParameterizedType) classType)
						.getActualTypeArguments()[0];
			} else {
				currentClass = currentClass.getSuperclass();
			}
		}
		
		throw new ECRuntimeException("The subclass should provider the ParameterizedType.");
	}

	@Override
	public Object create(Object o) {
		try {
			return getHibernateTemplate().save(o);
		} catch (Exception e) {
			logger.error("could not save object");
			throw new ECRuntimeException(e);
		}
	}

	@Override
	public void update(Object o) {
		getHibernateTemplate().update(o);
	}

	@Override
	public void saveOrUpdate(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	@Override
	public T getById(Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass, String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
        if (isAsc) {
        	return getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        }
        return getHibernateTemplate().findByCriteria(
        		DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(Object o) {
		return getHibernateTemplate().findByExample(o);
	}

	@Override
	public void delete(Object o) {
		getHibernateTemplate().delete(o);
	}

	@Override
	public void deleteById(Serializable id) {
		delete(getById(id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criteria criteria) {
		return criteria.list();
	}

}
