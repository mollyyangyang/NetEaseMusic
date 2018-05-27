package com.imollyunfei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imollyunfei.service.SingerInfoService;

@WebServlet(description = "获取歌手细分", urlPatterns = { "/SingerServlet" })
public class SingerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SingerServlet() {
		super();
	}

	// 获取歌手细分---无敏感信息，post/get方式均可
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
		// 此处前台应该上传一个url
		String singerUrl = request.getParameter("singerUrl");
		SingerInfoService infoService = new SingerInfoService();
		// 获得的数据就是格式化后的json数据
		String json = infoService.getSingerInfo(singerUrl);
		// 由于前台将添加点击获取--因此后台在前台点击之后，无需校验，直接返回数据
		// 此处应该加一个跳转
		response.getWriter().write(json);
	}
}
