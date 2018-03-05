package com.redpack.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.redpack.account.model.UserDo;

/**
 * 
 * @author harry
 * 
 */
public class LevelTag extends BodyTagSupport {

	private UserDo userInfo;

	private String item;
	
	private int idx = 1;

	private String index;
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	public UserDo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserDo userInfo) {
		this.userInfo = userInfo;
	}

	public int doStartTag() throws JspException {
		if (null != userInfo) {
			getNextParent();
			if (null == userInfo) {
				return SKIP_BODY;
			}
		}
		
		return this.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() {
		idx++;
		getNextParent();
		if (null == userInfo) {
			return this.SKIP_BODY;
		}
		return this.EVAL_BODY_AGAIN;
	}

	private void getNextParent() {
		UserDo parent = userInfo.getParentDo();
		userInfo = parent;
		if (null != parent) {
			pageContext.setAttribute(item, parent);
			pageContext.setAttribute(index, idx);
		}
	}
}
