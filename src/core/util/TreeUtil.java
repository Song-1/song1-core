package core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.bean.TreeHandBean;

public class TreeUtil<T extends TreeHandBean> {
	
	private Log logger = LogFactory.getLog(TreeUtil.class);
	private List<T> rootList = new ArrayList();

	public List<T> parseTreeList(List<T> allList, T t) {
		this.rootList.add(t);
		return parseTreeList(allList, this.rootList);
	}

	/**
	 * 
	 * @Title: parseTreeList
	 * @Description: 解析树
	 * @param @param allList 所有节点
	 * @param @param rootLists 根节点
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	public List<T> parseTreeList(List<T> allList, List<T> rootLists) {
		if ((rootLists != null) && (rootLists.size() > 0)) {
			for (int i = 0; i < rootLists.size(); ++i) {
				TreeHandBean t = (TreeHandBean) rootLists.get(i);
				if (allList.contains(t)) {
					allList.remove(t);
				}
			}
		}

		return recursionTree(allList, rootLists);
	}

	/**
	 * 
	 * @Title: parseTreeListForPIdIsNull
	 * @Description: 分析根节点
	 * @param @param allList
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	public List<T> parseTreeListForPIdIsNull(List<T> allList) {
		logger.debug("TreeUtil parseTreeListForPIdIsNull:");
		for (int i = 0; i < allList.size(); ++i) {
			TreeHandBean t = (TreeHandBean) allList.get(i);
			if ((t.findDaemonTreePId() == null) || (t.findDaemonTreePId().length() == 0)) {
				rootList.add((T) t);
			}
		}
		Collections.sort(this.rootList, new TAlphaComparator());
		return parseTreeList(allList, this.rootList);
	}
	
	/**
	 * 
	 * @Title: parseTreeListForPIdIsNull
	 * @Description: 分析根节点
	 * @param @param allList
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	public List<T> parseTreeListIsRoot(List<T> allList) {
		logger.debug("TreeUtil parseTreeListForPIdIsNull:");
		for (int i = 0; i < allList.size(); ++i) {
			TreeHandBean t = (TreeHandBean) allList.get(i);
			if ((t.findDaemonTreePId() == null) || (t.findDaemonTreePId().length() == 0)) {
				rootList.add((T) t);
			}
		}
		Collections.sort(this.rootList, new TAlphaComparator());
		return parseTreeList(allList, this.rootList);
	}

	/**
	 * 
	 * @Title: recursionTree
	 * @Description: 递归树
	 * @param allList
	 * @param  root
	 * @return List<T>
	 * @throws
	 */
	public List<T> recursionTree(List<T> allList, List<T> root) {
		if ((root != null) && (root.size() > 0)) {
			for (int r = 0; r < root.size(); ++r) {
				String pId = ((TreeHandBean) root.get(r)).findDaemonTreeId();
				for (int i = 0; i < allList.size(); ++i) {
					TreeHandBean t = (TreeHandBean) allList.get(i);
					if (pId.equals(t.findDaemonTreePId())) {
						((TreeHandBean) root.get(r)).getChildren().add(t);
						((TreeHandBean) root.get(r)).setLeaf(false);
					}

				}

				Collections.sort(((TreeHandBean) root.get(r)).getChildren(), new TAlphaComparator());
				recursionTree(allList, ((TreeHandBean) root.get(r)).getChildren());
			}
		}
		return this.rootList;
	}

	class TAlphaComparator implements Comparator {
		TAlphaComparator() {
		}

		public int compare(Object o1, Object o2) {
			TreeHandBean p1 = (TreeHandBean) o1;
			TreeHandBean p2 = (TreeHandBean) o2;
			if (p1.findDaemonTreeSeat() > p2.findDaemonTreeSeat()) {
				return 1;
			}
			return -1;
		}

	}
}