package core.business.service;

import core.bean.TreeBeans;

/**
 * 
 * @ClassName: TreeServices
 * @Description: 树形结构服务接口
 * @author Jeckey.Liu
 * @date 2014年4月24日 上午10:46:12
 * 
 * @param <T>
 */
public abstract interface TreeServices<T extends TreeBeans<T>> {
	
	public abstract String getNewTreeId(String paramString1, String paramString2, String paramString3);

	public abstract void changeChildTreePath(String paramString1, String paramString2, String paramString3);

	public abstract void deleteByCode(String paramString1, String paramString2);
}