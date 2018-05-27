package com.imollyunfei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imollyunfei.service.FetchSongCommentsService;

@WebServlet(description = "获取某个单曲下的Comments", urlPatterns = { "/FetchSongCommentsServlet" })
public class FetchSongCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FetchSongCommentsServlet() {
		super();
	}
	
	// 提高安全级别--防止被爬---仅测试时开启get方式
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 由于前台是点击操作，因此此处无需再做校验
		// 前台必须传入歌曲songId才能进行获取热评
		String songId = request.getParameter("songId");
		System.out.println("歌曲Id为："+songId);
		FetchSongCommentsService commentsService = new FetchSongCommentsService();
		try {
			// json格式数据
			String json = commentsService.parseCommentMessage(songId);
			response.getWriter().write(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
