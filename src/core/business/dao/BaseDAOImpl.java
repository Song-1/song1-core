package core.business.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseDAOImpl<T> {
	protected final Log log = LogFactory.getLog(super.getClass());
	private String entityPackage = ".pojo.";

	protected String getEntityName() {
		String entityName = super.getClass().getName();
		String realPack = entityName.substring(0, entityName.indexOf(".dao")) + this.entityPackage;
		entityName = entityName.substring(entityName.lastIndexOf(".") + 1, entityName.indexOf("DaoImpl"));
		return realPack + entityName;
	}

	public String getTableName() {
		String entityName = super.getClass().getName();
		entityName = entityName.substring(entityName.lastIndexOf(".") + 1, entityName.indexOf("DaoImpl"));
		return entityName;
	}

	protected void setException(Exception e) {
		this.log.info("------发生异常，异常信息如下：------");
		e.printStackTrace();
		this.log.info(e.toString());
		this.log.info("------发生异常，异常信息如上：------");
	}
}