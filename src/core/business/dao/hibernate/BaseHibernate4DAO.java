package core.business.dao.hibernate;

import core.business.dao.BaseDAO;
import core.business.pojo.Parameter;
import core.util.Pagination;

import java.util.List;

/**
 * 
 * @ClassName: BaseHibernate4DAO
 * @Description: 基础dao
 * @author Jeckey.Liu
 * @date 2014年6月7日 上午10:13:43
 * 
 * @param <T>
 */
public abstract interface BaseHibernate4DAO<T> extends BaseDAO<T> {
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
	public abstract List<T> findList(String hql, Object[] params);

	/**
	 * 
	 * @Title: findList 分页查询
	 * @param offset
	 *            firstResult
	 * @param pageSize
	 * @param hql
	 * @param params
	 * @return Pagination<T>
	 * @throws
	 */
	public abstract Pagination<T> findList(Integer offset, Integer pageSize, String hql, Object[] params);

	public abstract List<T> findByParameter(Parameter paramParameter, String paramString, Boolean paramBoolean);

	public abstract List<T> findByParameter(List<Parameter> paramList, String paramString, Boolean paramBoolean);
}