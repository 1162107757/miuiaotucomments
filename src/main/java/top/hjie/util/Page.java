package top.hjie.util;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
	
	private static final long serialVersionUID = -2247543442157652278L;
	
	private Integer totalPage;	// 总页数
	private Integer total;	// 总条数
	private List<?> data;	// 数据
	@SuppressWarnings("unused")
	private Integer prevPage;	// 上一页
	@SuppressWarnings("unused")
	private Integer nextPage;	// 下一页
	private Integer page = 1;	// 页码||当前页
	private Integer limit = 10;	// 每页数据大小
	
	
	public Integer getTotalPage() {
		if(total % limit == 0){
			return totalPage = total / limit;
		}else{
			return totalPage = total / limit + 1;
		}
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public Integer getPrevPage() {
		if((page - 1) < 0 || (page - 1) == 0){
			// 如果当前页减去上一页小于或者等于0
			return 1;
		}else{
			return page - 1;
		}
	}
	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}
	public Integer getNextPage() {
		if((page + 1) > totalPage || (page + 1) == totalPage){
			// 如果当前页加上1大于或者等于总页数直接返回最大页
			return totalPage;
		}else{
			return page + 1;
		}
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
