package core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.bean.TreeBeans;
import core.bean.TreeHandBean;

public class TreeUtils<T extends TreeBeans> {
	
	private Log logger = LogFactory.getLog(TreeUtils.class);
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
				TreeBeans t = (TreeBeans) rootLists.get(i);
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
			TreeBeans t = (TreeBeans) allList.get(i);
			if ((t.findDaemonTreePId() == null) || (t.findDaemonTreePId() == 0)) {
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
		logger.debug("TreeUtil parseTreeListIsRoot:");
		for (int i = 0; i < allList.size(); ++i) {
			TreeBeans t = (TreeBeans) allList.get(i);
			if ((t.findDaemonTreePId() == null) || (t.findDaemonTreePId() == 0)) {
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
				Integer pId = ((TreeBeans) root.get(r)).findDaemonTreeId();
				for (int i = 0; i < allList.size(); ++i) {
					TreeBeans t = (TreeBeans) allList.get(i);
					if (pId.equals(t.findDaemonTreePId())) {
						((TreeBeans) root.get(r)).getChildren().add(t);
						((TreeBeans) root.get(r)).setLeaf(false);
					}

				}

				Collections.sort(((TreeBeans) root.get(r)).getChildren(), new TAlphaComparator());
				recursionTree(allList, ((TreeBeans) root.get(r)).getChildren());
			}
		}
		return this.rootList;
	}

	class TAlphaComparator implements Comparator {
		TAlphaComparator() {
		}

		public int compare(Object o1, Object o2) {
			TreeBeans p1 = (TreeBeans) o1;
			TreeBeans p2 = (TreeBeans) o2;
			if (p1.findDaemonTreeSeat() > p2.findDaemonTreeSeat()) {
				return 1;
			}
			return -1;
		}

	}
}