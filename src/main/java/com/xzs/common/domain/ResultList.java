package com.xzs.common.domain;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * 分页统一返回对象
 * @Description:TODO
 * @param <T>
 * @exception:
 * @author: 徐正顺
 * @time:2017年11月1日 下午3:56:22
 */
@ApiModel(" 分页统一返回对象")
public class ResultList<T> {
	// 当前页码
	@ApiModelProperty("当前页码")
	private int pageNum;
	// 当前每页数量
	@ApiModelProperty("当前每页数量")
	private int pageSize;
	@ApiModelProperty("当前条数")
	private long currentCount;
	// 总数
	@ApiModelProperty("总数")
	private long count;
	// 总数
	@ApiModelProperty("总页数")
	private int pages;
	// 数据源
	@ApiModelProperty("数据")
	private List<T> data;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(long currentCount) {
		this.currentCount = currentCount;
	}

	
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public static ResultList genResultList(PageInfo pageInfo) {
		ResultList resultList = new ResultList<>();
		resultList.setCount(pageInfo.getTotal());
		resultList.setData(pageInfo.getList());
		resultList.setPages(pageInfo.getPages());
		resultList.setPageNum(pageInfo.getPageNum());
		resultList.setCurrentCount(pageInfo.getSize());
		resultList.setPageSize(pageInfo.getPageSize());
		return resultList;
	}
}
