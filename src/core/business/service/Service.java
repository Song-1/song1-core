package core.business.service;

import core.business.pojo.Parameter;
import java.io.Serializable;
import java.util.List;

public abstract interface Service<T> {
	
	public abstract Serializable save(T paramObject);

	public abstract T get(Serializable paramSerializable);

	public abstract void delete(Serializable paramSerializable);

	public abstract void update(T paramObject);

	public abstract void saveOrUpdate(T paramObject);

	public abstract int getCount();

	public abstract List<T> findAll(String paramString, Boolean paramBoolean);

	public abstract List<T> findByParameter(Parameter paramParameter, String paramString, Boolean paramBoolean);

	public abstract List<T> findByParameter(List<Parameter> paramList, String paramString, Boolean paramBoolean);
}