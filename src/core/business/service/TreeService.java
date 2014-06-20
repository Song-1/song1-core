package core.business.service;

import core.bean.TreeHandBean;

@SuppressWarnings("rawtypes")
public abstract interface TreeService<T extends TreeHandBean> {
	public abstract String getNewTreeId(String paramString1, String paramString2, String paramString3);

	public abstract void changeChildTreePath(String paramString1, String paramString2, String paramString3);

	public abstract void deleteByCode(String paramString1, String paramString2);
}