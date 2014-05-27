/**
 * 
 */
package com.ecommerce.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.util.Assert;

import com.ecommerce.core.dao.BaseDao;
import com.ecommerce.core.dao.BaseDaoSupport;
import com.ecommerce.core.exception.ECRuntimeException;
import com.ecommerce.model.BaseEntity;

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
	public T findById(Serializable id) {
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
		delete(findById(id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criteria criteria) {
		return criteria.list();
	}
	
	@Override
	public List findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        String[] params = new String[queryParams.size()];
        Object[] values = new Object[queryParams.size()];
        int index = 0;
        Iterator<String> i = queryParams.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            params[index] = key;
            values[index++] = queryParams.get(key);
        }
        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, params, values);
    }

}
