package ${pageEntityPackage};

/**
 * 类: PageEntity <br>
 * 描述: 分页用实体 <br>
 */
public class PageEntity<T> {
	//开始条数
	private int startNo = 1;
	//结束条数
	private int endNo = 15;
	//总条数
	private int totalSize;
	//当前页
	private int currentPage = 1;
	//总页数
	private int totalPage;
	//每页条数
	private int pageSize = 15;
	
	private T data;

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		if (totalSize % this.pageSize == 0) {
			this.totalPage = totalSize / this.pageSize;
		} else {
			this.totalPage = totalSize / this.pageSize + 1;
		}
		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}
		setStartEndNo();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		setStartEndNo();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setStartEndNo();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public void setStartEndNo() {
		if (this.currentPage < 1) {
			this.currentPage = 1;
		}
		if (this.pageSize < 1) {
			this.pageSize = 1;
		}
		this.endNo = currentPage * pageSize;
		this.startNo = endNo - pageSize + 1;
	}
}
