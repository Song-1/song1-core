package core.bean;

import java.util.List;
/**
 * 
* @ClassName: ITreeBean 
* @Description: 分类树接口
* @author Jeckey.Liu
* @date 2014年4月24日 上午10:33:07 
* 
* @param <T>
 */
public abstract interface ITreeBean<T> {
	public abstract Integer getId();
	public abstract String getText();
	public abstract Integer getDaemonTreeId();
	public abstract Integer getDaemonTreePId();
	public abstract Integer getDaemonTreeSeat();
	public abstract List<T> getChildren();
	public abstract boolean isLeaf();
	public abstract void setLeaf(boolean paramBoolean);
}