package com.imollyunfei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imollyunfei.service.GetSongOfSingerService;

@WebServlet(description = "获取歌手对应的热门歌曲【默认50首】", urlPatterns = { "/GetSongInfoServlet" })
public class GetSongInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSongInfoServlet() {
		super();
	}

	// 不涉及敏感信息--默认设置为post/get
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// SingerUrl--得从前台上传
		String SingerUrl = request.getParameter("SingerUrl");
		GetSongOfSingerService getSongOfSingerService = new GetSongOfSingerService();
		String json = getSongOfSingerService.getSongInfo(SingerUrl);
		//点击操作无需校验--直接向前台展现数据
		response.getWriter().write(json);
	}
}
