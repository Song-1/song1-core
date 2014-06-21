package core.business.dao.hibernate.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.business.dao.BaseDAOImpl;
import core.business.dao.hibernate.BaseHibernate4DAO;
import core.business.pojo.Parameter;
import core.util.Pagination;

public class BaseHibernate4DAOImpl<T> extends BaseDAOImpl<T> implements BaseHibernate4DAO<T> {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Object get(Serializable id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			return session.get(getEntityName(), id);
		} catch (Exception e) {
			setException(e);
		}
		return null;
	}

	public void delete(Serializable id) {
		Object entity = get(id);
		delete(entity);
	}

	public void delete(Object entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(entity);
		session.flush();
	}

	public void saveOrUpdate(Object entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
		session.flush();
	}

	public void flush() {
		Session session = this.sessionFactory.getCurrentSession();
		session.flush();
	}

	public Serializable save(Object entity) {
		Session session = this.sessionFactory.getCurrentSession();
		Serializable id = session.save(entity);
		session.flush();
		return id;
	}

	public void update(Object entity) {
		saveOrUpdate(entity);
	}

	public int getCount() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String hql = "select id from " + getTableName();
			List list = session.createQuery(hql).list();
			return list.size();
		} catch (Exception e) {
			setException(e);
		}
		return 0;
	}
	
	public int getCount(String sql,Map<Object, Object> params) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			SQLQuery createSQLQuery = session.createSQLQuery(sql);
			Iterator<Object> iterator = params.keySet().iterator();
			while (iterator.hasNext()) {
				Object next = iterator.next();
				Object key = params.get(next);
				Object value = params.get(key);
				if (key != null && key instanceof Integer && value instanceof Integer) {
					createSQLQuery.setString((Integer)key, (String)value);
				}
			}
			@SuppressWarnings("unchecked")
			List<Object> list = createSQLQuery.list();
			return list.size();
		} catch (Exception e) {
			setException(e);
		}
		return 0;
	}
	
	public int getCountBysql(String sql, Object[] params) {
		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			for (int i = 0; i < params.length; i++) {
				if (params[i] instanceof Integer) {
					query.setInteger(i, (Integer)params[i]);
				}else if (params[i] instanceof String) {
					query.setString(i, (String)params[i]);
				}
			}
			return query.list().size();
		} catch (Exception e) {
			setException(e);
		}
		return 0;
	}

	public List<T> findAll(String orderby, Boolean isDesc) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			StringBuffer hql = new StringBuffer();
			hql.append(" from " + getTableName());
			if ((orderby != null) && (orderby.length() > 0)) {
				hql.append(" order by " + orderby);
				if (isDesc) {
					hql.append(" desc ");
				}
			}
			log.debug("HQL:" + hql.toString());
			System.out.println("HQL:" + hql.toString());
			List list = session.createQuery(hql.toString()).list();
			return list;
		} catch (Exception e) {
			setException(e);
		}
		return null;
	}

	private Query queryBySession(String hql, Object[] params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if ((params != null) && (params.length > 0)) {
			for (int i = 0; i < params.length; ++i) {
				q = q.setParameter(i, params[i]);
			}
		}
		return q;
	}
	/**
	 * 
	 * @Title: findList 条件查询
	 * @param hql
	 *            hql语句
	 * @param params
	 *            条件数组
	 * @return List<T>
	 * @throws
	 */
	public List<T> findListBysql(String sql, Object[] params) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			if (params[i] instanceof Integer) {
				query.setInteger(i, (Integer)params[i]);
			}else if (params[i] instanceof String) {
				query.setString(i, (String)params[i]);
			}
		}
		return query.list();
	}
	
	public List<T> findListBysql(String sql, Map params) {
			Session session = this.sessionFactory.getCurrentSession();
			SQLQuery createSQLQuery = session.createSQLQuery(sql);
			Iterator<Object> iterator = params.keySet().iterator();
			while (iterator.hasNext()) {
				Object next = iterator.next();
				Object key = params.get(next);
				Object value = params.get(key);
				if (key != null && key instanceof Integer && value instanceof Integer) {
					createSQLQuery.setString((Integer)key, (String)value);
				}
			}
			@SuppressWarnings("unchecked")
			List<T> list = createSQLQuery.list();
			return list;
	}
	/**
	 * 
	 * @Title: findList 条件查询
	 * @param hql
	 *            hql语句
	 * @param params
	 *            条件数组
	 * @return List<T>
	 * @throws
	 */
	public List<T> findList(String hql, Object[] params) {
		Query q = queryBySession(hql, params);
		return q.list();
	}

	public int getCount(String hql, Object[] params) {
		String countHql = "select count(*) from " + hql.substring(hql.toLowerCase().indexOf("from") + 4);
		int order = countHql.indexOf("order");
		if (order > -1) {
			countHql = countHql.substring(0, order);
		}
		String res = queryBySession(countHql, params).list().get(0).toString();
		return Integer.valueOf(res).intValue();
	}

	public Pagination<T> findList(Integer offset, Integer pageSize, String hql, Object[] params) {
		Query q = queryBySession(hql, params);
		q.setFirstResult(offset.intValue());
		q.setMaxResults(pageSize.intValue());
		List list = q.list();
		Pagination pt = new Pagination(offset.intValue(), pageSize.intValue(), getCount(hql, params), list);
		return pt;
	}

	public List<T> findByParameter(Parameter parameter, String seq, Boolean isDESC) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			StringBuffer hql = new StringBuffer();
			hql.append(" from " + getTableName());
			if (parameter != null) {
				if ((parameter.getValue() != null) && (parameter.getName() != null)) {
					hql.append(" where ");
					hql.append(parameter.getName());
					hql.append("= '");
					hql.append(parameter.getValue());
					hql.append("'");
				}else if (parameter.getName() != null){
					hql.append(" where ");
					hql.append(parameter.getName());
					hql.append(" is null ");
				}
			}
			if (seq != null) {
				hql.append(" order by " + seq);
				if (isDESC)
					hql.append(" desc ");
				else {
					hql.append(" asc ");
				}
			}
			List list = session.createQuery(hql.toString()).list();
			return list;
		} catch (Exception e) {
			setException(e);
		}
		return null;
	}

	public List<T> findByParameter(List<Parameter> parameters, String seq, Boolean isDESC) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			StringBuffer hql = new StringBuffer();
			hql.append(" from " + getTableName());
			if ((parameters != null) && (parameters.size() > 0)) {
				hql.append(" where ");
				for (int i = 0; i < parameters.size(); ++i) {
					Parameter parameter = parameters.get(i);
					if (i > 0) {
						hql.append(" and ");
					}
					if (parameter.getValue() != null) {
						hql.append(parameter.getName());
						hql.append("= '");
						hql.append(parameter.getValue());
						hql.append("'");
					} else {
						hql.append(parameter.getName());
						hql.append(" is null ");
					}
				}
			}
			if (seq != null) {
				hql.append(" order by " + seq);
				if (isDESC)
					hql.append(" desc ");
				else {
					hql.append(" asc ");
				}
			}

			List list = session.createQuery(hql.toString()).list();
			return list;
		} catch (Exception e) {
			setException(e);
		}
		return null;
	}
}