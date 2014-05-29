/**
 * 
 */
package com.ecommerce.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ecommerce.core.dao.impl.BaseDaoImpl;
import com.ecommerce.core.exception.ECRuntimeException;
import com.ecommerce.model.User;
import com.ecommerce.util.StringUtil;
import com.ecommerce.util.support.ECLogger;
import com.ecommerce.web.consts.DaoQueryConsts;
import com.ecommerce.web.dao.UserDao;

/**
 * @author richard
 *
 */
@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
	private static final ECLogger logger = ECLogger.getLogger(UserDaoImpl.class);

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq(DaoQueryConsts.USERNAME, username));
		criteria.add(Restrictions.eq(DaoQueryConsts.PASSWORD, password));
		List<User> results = findByCriteria(criteria);
		
        if (StringUtil.isEmpty(results)) return null;
        return results.get(0);
	}

	@Override
	public User findUserByUsername(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq(DaoQueryConsts.USERNAME, username));
		List<User> results = findByCriteria(criteria);
		
        if (StringUtil.isEmpty(results)) return null;
        return results.get(0);
	}
	
	public List<String> testIfNamedQueryWorks(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DaoQueryConsts.USERNAME, username);
		try {
			return findByNamedQuery(DaoQueryConsts.TEST_SQL, params);
		} catch (Exception e) {
			throw new ECRuntimeException("test failed.");
		}
	}

}
