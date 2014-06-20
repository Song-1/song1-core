package core.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.bean.TreeHandBean;
import core.business.dao.hibernate.BaseHibernate4DAO;
import core.business.pojo.Parameter;
import core.business.service.TreeService;
import core.util.SixtyTwoCountUtil;

public class TreeServiceImpl<T extends TreeHandBean> implements TreeService<T> {

	private static final Log logger = LogFactory.getLog(TreeServiceImpl.class);
	private BaseHibernate4DAO<T> dao;

	public BaseHibernate4DAO<T> getDAO() {
		return this.dao;
	}

	public void setDao(BaseHibernate4DAO<T> dao) {
		this.dao = dao;
	}

	/**
	 * 获取新建树形code
	 */
	public String getNewTreeId(String codeKey, String pCodeKey, String pCodeValue) {
		List list = null;
		String code = null;
		int i = 0;
		while (true) {
			if ((pCodeValue == null) || (pCodeValue.length() == 0)) {
				list = getDAO().findList("from " + getDAO().getTableName() + " where " + pCodeKey + " is null", null);
				logger.debug("from " + getDAO().getTableName() + " where " + pCodeKey + " is null");
			} else {
				list = getDAO().findList("from " + getDAO().getTableName() + " where " + pCodeKey + " = ? ",
						new Object[] { pCodeValue });
				logger.debug("from " + getDAO().getTableName() + " where " + pCodeKey + " = " + pCodeValue);
			}
			if ((list != null) && (list.size() > 0))
				code = SixtyTwoCountUtil.thirtySixCount(list.size() + i);
			else {
				code = SixtyTwoCountUtil.thirtySixCount(0 + i);
			}
			if (pCodeValue != null) {
				code = pCodeValue + code;
			}
			List listParameter = new ArrayList();
			listParameter.add(new Parameter(codeKey, code));
			listParameter.add(new Parameter(pCodeKey, pCodeValue));
			List listResule = getDAO().findByParameter(listParameter, null, false);
			if ((listResule == null) || (listResule.size() <= 0))
				break;
			++i;
		}

		return code;
	}

	public void changeChildTreePath(String code, String changeId, String treePath) {
		List list = list = getDAO().findList(
				"from " + getDAO().getTableName() + " where treePath like '%/" + code + "/%'", null);
		if ((list != null) && (list.size() > 0))
			for (int i = 0; i < list.size(); ++i) {
				TreeHandBean t = (TreeHandBean) list.get(i);
				t.setTreePath(treePath + "/" + t.getId() + "/");

				getDAO().save(t);
			}
	}

	public void deleteByCode(String codeKey, String codeValue) {
		List list = null;
		if ((codeKey != null) && (codeKey.length() > 0) && (codeValue != null) && (codeValue.length() > 0)) {
			list = getDAO().findList("from " + getDAO().getTableName() + " where " + codeKey + " = ? ",
					new Object[] { codeValue });
			if ((list != null) && (list.size() > 0))
				getDAO().delete(list.get(0));
		}
	}
	
	public static void main(String[] args) {
		TreeService ts = new TreeServiceImpl<TreeHandBean>();
		String newTreeId = ts.getNewTreeId(null, null, null);
		System.out.println(newTreeId);
	}
}