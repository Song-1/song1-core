package core.business.dao;

import java.io.Serializable;
import java.util.List;

public abstract interface BaseDAO<T> {
	public abstract String getTableName();

	public abstract Object get(Serializable paramSerializable);

	public abstract void delete(Serializable paramSerializable);

	public abstract void delete(Object paramObject);

	public abstract void saveOrUpdate(Object paramObject);

	public abstract void flush();

	public abstract Serializable save(Object paramObject);

	public abstract void update(Object paramObject);

	public abstract List<T> findAll(String paramString, Boolean paramBoolean);

	public abstract int getCount();

	public abstract int getCount(String paramString, Object[] paramArrayOfObject);
}