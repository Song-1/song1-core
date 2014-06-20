package core.bean;

import java.util.List;

public abstract interface TreeBean<T> {
	public abstract String getId();

	public abstract String getText();

	public abstract String getDaemonTreeId();

	public abstract String getDaemonTreePId();

	public abstract int getDaemonTreeSeat();

	public abstract List<T> getChildren();

	public abstract boolean isLeaf();

	public abstract void setLeaf(boolean paramBoolean);
}