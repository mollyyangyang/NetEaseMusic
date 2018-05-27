package com.imollyunfei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imollyunfei.service.KindService;

@WebServlet(description = "提供给前台歌手分组", urlPatterns = { "/ListGrroupServlet" })
public class ListGrroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListGrroupServlet() {
		super();
	}

	// 由于不涉及敏感信息--因此设置post/get方式均可
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 首先一来设置下格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 分组的查询，不涉及校验，因此可直接返回数据
		// 直接调用Serverce层提供的服务
		KindService kindService = new KindService();
		String json = kindService.getSingerKindFromFindMusic();
		response.getWriter().write(json);
	}
}
