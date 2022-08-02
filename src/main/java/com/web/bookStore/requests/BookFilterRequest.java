package com.web.bookStore.requests;

public class BookFilterRequest {
	public static final int ASC_PRICE_ORDER = 0;
	public static final int DESC_PRICE_ORDER = 1;
	public static final int NONE_PRICE_ORDER = -1;
	private int pageIndex;
	private int pageSize;
	private int orderPriceFilter;
	private String titlePart;
	
	public BookFilterRequest() {
		pageIndex = 0;
		pageSize=16;
		orderPriceFilter = BookFilterRequest.NONE_PRICE_ORDER;
	}
	public BookFilterRequest(int pageIndex, int pageSize, int orderPriceFilter, String titlePart) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.orderPriceFilter = orderPriceFilter;
		this.titlePart = titlePart;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getOrderPriceFilter() {
		return orderPriceFilter;
	}
	public void setOrderPriceFilter(int orderPriceFilter) {
		this.orderPriceFilter = orderPriceFilter;
	}
	public String getTitlePart() {
		return titlePart;
	}
	public void setTitlePart(String titlePart) {
		this.titlePart = titlePart;
	}
	

}
