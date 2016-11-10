package com.suwon.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
//@WebFilter("/*")   //파라미터 없이 단순히 매핑만 한다면

@WebFilter(
    urlPatterns={"/*"},
    initParams={@WebInitParam(name="encoding", value="UTF-8")}
)

public class CharacterEncodingFilter implements Filter { // sersvlet.Filter

	String encoding;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response); // 이 체인을 기준으로 전이랑 후에 작업을 나눠야 함.
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding"); // web.xml에서
														// <init-param>에 설정햇음.
	}

}