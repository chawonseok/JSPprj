package com.suwon.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

//제어구조를 jsp에서 사용하기 위한 태그 만들기
public class ForTag extends TagSupport{
	
	int index = 0;
	
	/*public ForTag() {
		index = 0;
	}*/ 
	
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doAfterBody() throws JspException {

		if(++index > 5 )
			return SKIP_BODY;
		
		return EVAL_BODY_AGAIN;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	
}
