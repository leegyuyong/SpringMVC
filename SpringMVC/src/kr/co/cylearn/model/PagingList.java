package kr.co.cylearn.model;

import java.util.List;
import java.util.Map;

public class PagingList {
	private Paging paging;
	private List<?> list;
	private Map<String, Object> map;

	public PagingList(Paging paging, List<?> list) {
		this.list = list;
		this.paging = paging;
	}

	public PagingList(Map<String, Object> map, List<?> list) {
		this.map = map;
		this.list = list;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}
