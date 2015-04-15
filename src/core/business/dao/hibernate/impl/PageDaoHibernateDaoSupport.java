package core.business.dao.hibernate.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import core.business.pojo.Parameter;
import core.util.Pagination;

/**
 * 基于HibernateDaoSupport的三个分页控制方法
 * 
 * @author Jeckey.Liu
 *
 */
public class PageDaoHibernateDaoSupport<T> extends HibernateDaoSupport {


	protected final Log log = LogFactory.getLog(super.getClass());
	private String entityPackage = ".pojo.";
	/**
	 * 使用HQL语句进行分页查询操作 offset 第一条记录的索引 pageSize 每页需要显示的记录数
	 * 
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final int offset,
			final int pageSize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List result = session.createQuery(hql).setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	/**
	 * 使用HQL语句进行分页查询操作 value 如果HQL有一个参数需要传人，则value就是传人的参数 offset 第一条记录的索引
	 * pageSize 每页需要显示的记录数
	 * 
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final Object value,
			final int offset, final int pageSize) {
		// System.out.println("PageDaoHibernate.findByPage()");
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List result = session.createQuery(hql).setFirstResult(offset)
						.setParameter(0, value).setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	/**
	 * 使用HQL语句进行分页查询操作 values 如果HQL有多个参数需要传人，则values就是传人的参数数组 offset 第一条记录的索引
	 * pageSize 每页需要显示的记录数
	 * 
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final Object[] values,
			final int offset, final int pageSize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				List result = query.setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}
	

	/**
	 * 
	 * @return
	 */
	protected String getEntityName() {
		String entityName = super.getClass().getName();
		String realPack = entityName.substring(0, entityName.indexOf(".dao")) + this.entityPackage;
		entityName = entityName.substring(entityName.lastIndexOf(".") + 1, entityName.indexOf("DaoImpl"));
		return realPack + entityName;
	}

	/**
	 * 
	 * @return
	 */
	public String getTableName() {
		String entityName = super.getClass().getName();
		entityName = entityName.substring(entityName.lastIndexOf(".") + 1, entityName.indexOf("DaoImpl"));
		return entityName;
	}

	/**
	 * 
	 * @param e
	 */
	protected void setException(Exception e) {
		this.log.info("------发生异常，异常信息如下：------");
		e.printStackTrace();
		this.log.info(e.toString());
		this.log.info("------发生异常，异常信息如上：------");
	}
	
	public Object get(Serializable id) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
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
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(entity);
		session.flush();
	}

	public void saveOrUpdate(Object entity) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(entity);
		session.flush();
	}

	public void flush() {
		Session session = this.getSessionFactory().getCurrentSession();
		session.flush();
	}

	public Serializable save(Object entity) {
		Session session = this.getSessionFactory().getCurrentSession();
		Serializable id = session.save(entity);
		session.flush();
		return id;
	}

	public void update(Object entity) {
		saveOrUpdate(entity);
	}

	public int getCount() {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
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
			Session session = this.getSessionFactory().getCurrentSession();
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
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
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
			Session session = this.getSessionFactory().getCurrentSession();
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
		Query q = this.getSessionFactory().getCurrentSession().createQuery(hql);
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
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
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
			Session session = this.getSessionFactory().getCurrentSession();
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
		int count =  getCount(hql, params);
		if(offset != null && offset.intValue() > count){
			///// 如果起始条数大于总条数,则默认从零开始(即显示第一页)
			offset = 0;
		}
		Query q = queryBySession(hql, params);
		q.setFirstResult(offset.intValue());
		q.setMaxResults(pageSize.intValue());
		List list = q.list();
		Pagination pt = new Pagination(offset.intValue(), pageSize.intValue(), getCount(hql, params), list);
		return pt;
	}

	public List<T> findByParameter(Parameter parameter, String seq, Boolean isDESC) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			StringBuffer hql = new StringBuffer();
			hql.append(" from " + getTableName());
			Map<String,Object> map = new HashMap<String, Object>(1);
			if (parameter != null) {
				if ((parameter.getValue() != null) && (parameter.getName() != null)) {
					String paramName = parameter.getName();
					hql.append(" where ");
					hql.append(paramName);
					hql.append("= :"+paramName + " ");
					map.put(paramName, parameter.getValue());
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
			Query q = session.createQuery(hql.toString());
			for(Map.Entry<String, Object> entry : map.entrySet()){
				q.setParameter(entry.getKey(), entry.getValue());
			}
			List list = q.list();
			return list;
		} catch (Exception e) {
			setException(e);
		}
		return null;
	}

	/**
	 * 
	 * @param parameters
	 * @param seq
	 * @param isDESC
	 * @return
	 */
	public List<T> findByParameter(List<Parameter> parameters, String seq, Boolean isDESC) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			StringBuffer hql = new StringBuffer();
			hql.append(" from " + getTableName());
			Map<String,Object> map = new HashMap<String, Object>();
			if ((parameters != null) && (parameters.size() > 0)) {
				hql.append(" where ");
				for (int i = 0; i < parameters.size(); ++i) {
					Parameter parameter = parameters.get(i);
					if (i > 0) {
						hql.append(" and ");
					}
					if (parameter.getValue() != null) {
						String paramName = parameter.getName();
						hql.append(paramName);
						hql.append(" = :"+paramName + " ");
						map.put(paramName, parameter.getValue());
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
			Query q = session.createQuery(hql.toString());
			for(Map.Entry<String, Object> entry : map.entrySet()){
				q.setParameter(entry.getKey(), entry.getValue());
			}
			List list = q.list();
			return list;
		} catch (Exception e) {
			setException(e);
		}
		return null;
	}

}
