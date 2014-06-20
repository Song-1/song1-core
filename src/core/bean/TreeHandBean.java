package core.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public abstract class TreeHandBean<T> {
	private List<T> children = new ArrayList<T>();
	private boolean leaf = true;

	@Transient
	public String getId() {
		return findDaemonTreeId();
	}

	@Transient
	public List<T> getChildren() {
		return this.children;
	}

	@Transient
	public boolean isLeaf() {
		return this.leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Transient
	public abstract void setTreePath(String paramString);

	@Transient
	public abstract String getText();

	@Transient
	public abstract String findDaemonTreeId();

	@Transient
	public abstract String findDaemonTreePId();

	@Transient
	public abstract int findDaemonTreeSeat();
}
