package core.business.service.impl;

import core.business.dao.hibernate.BaseHibernate4DAO;
import core.business.pojo.Parameter;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

public class BaseServiceHibernate4Impl<T> {
	protected final Logger log = Logger.getLogger(super.getClass());
	private BaseHibernate4DAO<T> dao;

	public BaseHibernate4DAO<T> getDAO() {
		return this.dao;
	}

	public void setDao(BaseHibernate4DAO<T> dao) {
		this.dao = dao;
	}

	public void delete(Serializable id) {
		getDAO().delete(id);
	}

	public void saveOrUpdate(T obj) {
		getDAO().saveOrUpdate(obj);
	}

	public Serializable save(T entity) {
		return getDAO().save(entity);
	}

	public T get(Serializable id) {
		return (T) getDAO().get(id);
	}

	public void update(T entity) {
		getDAO().update(entity);
	}

	public int getCount() {
		return getDAO().getCount();
	}

	public List<T> findAll(String orderby, Boolean isDesc) {
		return getDAO().findAll(orderby, isDesc);
	}

	public List<T> findByParameter(Parameter parameter, String seq, Boolean isDESC) {
		return getDAO().findByParameter(parameter, seq, isDESC);
	}

	public List<T> findByParameter(List<Parameter> parameters, String seq, Boolean isDESC) {
		return getDAO().findByParameter(parameters, seq, isDESC);
	}
}