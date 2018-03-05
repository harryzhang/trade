package com.redpack.notice;

import java.util.List;
import java.util.Map;

public interface INoticeService {

	/**
	 * 
	 * @return
	 */
	List<Map<String, Object>>  queryNotice();

	List<Map<String, Object>> queryNoticeById(String id);

}
