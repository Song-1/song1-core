package core.util;

import java.util.List;

/**
 * 
 * @ClassName: Pagination
 * @Description: 分页
 * @author Jeckey.Liu
 * @date 2014年4月16日 下午2:38:25
 * 
 * @param <T>
 */
public class Pagination<T> {
	/**
	 * 总记录数
	 */
	private int recordCount = 0;

	/**
	 * 起始记录
	 */
	private int offSet = 0;

	/**
	 * 每页条数
	 */
	private int pageSize = 0;

	/**
	 * 总页数
	 */
	private int pageNum = 1;
	/**
	 * 所有对象
	 */
	private List<T> listPageObject;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * 分页对象实例化
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param offSet
	 * @param pageSize
	 * @param recordCount
	 * @param listPageObject
	 */
	public Pagination(int offSet, int pageSize, int recordCount, List<T> listPageObject) {
		setRecordCount(recordCount);
		setPageSize(pageSize);
		setOffSet(offSet);
		setListPageObject(listPageObject);
	}

	/**
	 * 
	 * @Title: getRecordCount
	 * @Description: 获取总记录数
	 * @return int
	 * @throws
	 */
	public int getRecordCount() {
		return this.recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getOffSet() {
		return this.offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		if (this.pageSize > 0)
			return (this.recordCount % this.pageSize == 0) ? this.recordCount / this.pageSize : this.recordCount
					/ this.pageSize + 1;
		return 1;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<T> getListPageObject() {
		return this.listPageObject;
	}

	public void setListPageObject(List<T> listPageObject) {
		this.listPageObject = listPageObject;
	}
}