package core.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

/**
 * 
 * @ClassName: TreeBeans
 * @Description: 树形结构实体抽象类
 * @author Jeckey.Liu
 * @date 2014年4月30日 下午5:03:36
 * 
 * @param <T>
 */
public abstract class TreeBeans<T> {
	private List<T> children = new ArrayList<T>();
	private boolean leaf = true;

	@Transient
	public Integer getId() {
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
	public abstract String getText();

	@Transient
	public abstract Integer findDaemonTreeId();

	@Transient
	public abstract Integer findDaemonTreePId();

	@Transient
	public abstract Integer findDaemonTreeSeat();
}
