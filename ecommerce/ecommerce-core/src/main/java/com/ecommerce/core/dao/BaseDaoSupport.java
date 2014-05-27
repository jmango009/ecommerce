/**
 * 
 */
package com.ecommerce.core.dao;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


/**
 * @author richard
 *
 */
public class BaseDaoSupport extends DaoSupport {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}
	
	public final SessionFactory getSessionFactory() {  
	    return ((getHibernateTemplate() != null) ? this.hibernateTemplate.getSessionFactory() : null);  
	} 
	
    public Session getSession() {
        if (getSessionFactory() == null) {
            throw new HibernateException("Session Create Fail,SessionFactory is null!");
        }
        return getSessionFactory().getCurrentSession();
    }

    @Override
    protected void checkDaoConfig() throws IllegalArgumentException {
        if (this.hibernateTemplate == null) {
            throw new IllegalArgumentException("'sessionFactory' or 'hibernateTemplate' is required");
        }
    }
    
    protected final Session getSession(boolean allowCreate)
            throws DataAccessResourceFailureException, IllegalStateException {
        return (!allowCreate ? SessionFactoryUtils.getSession(
                getSessionFactory(), false) : SessionFactoryUtils.getSession(
                getSessionFactory(),
                this.hibernateTemplate.getEntityInterceptor(),    
                this.hibernateTemplate.getJdbcExceptionTranslator()));
    }

    protected final DataAccessException convertHibernateAccessException(
            HibernateException ex) {
        return this.hibernateTemplate.convertHibernateAccessException(ex);
    }

    protected final void releaseSession(Session session) {
        SessionFactoryUtils.releaseSession(session, getSessionFactory());
        if(null!=session)session=null;
    }

}
