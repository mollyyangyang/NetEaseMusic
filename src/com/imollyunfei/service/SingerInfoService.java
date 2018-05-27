package com.imollyunfei.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @ClassName: SingerInfoService
 * @Description: singerService--提供具体歌手信息(这里用一句话描述这个类的作用)
 * @author @mollyunfei
 * @date 2018年1月14日 下午4:32:10
 *
 */
public class SingerInfoService {
	// url--前台传入的具体的歌手分类
	// 根据传入的歌手子分类获取该分类下全部歌手信息【仅热门歌手】
	public static String getSingerInfo(String url) throws IOException {
		Document document = Jsoup.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36")
				.timeout(60000).get();
		// 测试成功--屏蔽输出
		// System.out.println(document);
		Elements elements = document.select(".s-fc0");
		// 测试输出关键节点成功--下面开始数据格式化操作
		// System.out.println(elements);
		// 下面进行数据格式化成json格式
		List<Object> list = new ArrayList<Object>();
		HashMap<String, String> hashMap = null;
		for (int i = 0; i < elements.size(); i++) {
			hashMap = new HashMap<>();
			// 获取歌手名
			String singerName = elements.get(i).text();
			// 获取歌手主页
			String singerMainPage = elements.get(i).absUrl("href");
			System.out.println(singerName + "--------------------" + singerMainPage);
			hashMap.put("singerName", singerName);
			hashMap.put("singerMainPage", singerMainPage);
			list.add(hashMap);
		}
		JSONArray array = new JSONArray(list);
		// 测试成功---生成json数据符合要求
		System.out.println(array.toString());
		return array.toString();
	}

	// 所有子分类都可以获取成功
	// 获取方式-->传入http://music.163.com/discover/artist/cat?id=4001
	// 注意一定不要带#,否则获取失败，因为网易云是根据框架来加载指定页面的
	// 测试连接成功
	// 除入驻歌手外均可获取---->入驻歌手由于是动态添加的，因此应该做特殊处理
	public static void main(String[] args) throws IOException {
		getSingerInfo("http://music.163.com/discover/artist/signed/");
		//测试成功
		// getSingerInfo("http://music.163.com/discover/artist/cat?id=4002");
	}
}
