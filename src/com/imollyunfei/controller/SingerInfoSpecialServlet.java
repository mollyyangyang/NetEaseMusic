package com.imollyunfei.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imollyunfei.service.SingerInfoSpecialService;

@WebServlet(description = "针对入驻歌手特殊加载方式", urlPatterns = { "/SingerInfoSpecialServlet" })
public class SingerInfoSpecialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SingerInfoSpecialServlet() {
		super();
	}

	// 加载歌手，不涉及敏感信息--因此post/get方式均可
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 首先设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SingerInfoSpecialService infoSpecialService = new SingerInfoSpecialService();
		String json = infoSpecialService.getSingerInfo();
		response.getWriter().write(json);
	}
}
