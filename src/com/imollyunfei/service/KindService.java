package com.imollyunfei.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KindService {
	/**
	 * --->注：此服务类主要用于动态生成表格并绑定url 此服务类主要用于获取<br>
	 * http://music.163.com/discover/artist/<br>
	 * 该分类中的歌手分类<br>
	 * ----><br>
	 * 总体思路为，先获取歌手分类<br>
	 * 然后根据歌手获取歌曲，然后根据某一歌曲，得到topComments<br>
	 * ---->主要用到Gson/json/Jsoup<br>
	 * 
	 * @throws IOException
	 */
	// get方式即可--->获取到数据后记得组装成json数据--->用于传输到前台
	// 这个获取json的过程，不用传入参数，获取的链接也应该固定
	// 最终转换成格式成json之后的String字符串返回--供前台调用
	public static String getSingerKindFromFindMusic() throws IOException {
		Document document = Jsoup.connect("http://music.163.com/discover/artist/").timeout(10000).get();
		// 测试成功
		// System.out.println(document);
		// 获得推荐模块
		// 最外层数据结构应当为List类型---这样才能将最终数据组合成一个JsonArray
		// 此处数据结构应当为Map类型
		// 下面开始整合数据成json格式--首先整合小组
		// 接着整个小组下名称
		String recommendName = document.select("#singer-cat-nav > h2").text();
		// 推荐
		System.out.println(recommendName);
		List<Object> listAll = new ArrayList<>();
		Map<String, Object> childMap = new HashMap<>();
		List<Object> list = new ArrayList<>();
		Map<String, String> childMap0 =null;
		Elements elements = document.select("#singer-cat-nav > ul li");
		for (int i = 0; i < elements.size(); i++) {
			childMap0= new HashMap<String, String>();
			Element element = elements.get(i);
			String eleName = element.text();
			String eleUrl = element.select("a").attr("abs:href");
			// 获取节点成功--停止提示
			// System.out.println(element);
			 System.out.println(eleName);
			 System.out.println(eleUrl);
			// 子类名称Name和链接Url组合进数据中
			childMap0.put("childName", eleName);
			childMap0.put("childUrl", eleUrl);
			list.add(childMap0);
		}
		childMap.put("Listkind", recommendName);
		childMap.put("content", list);
		listAll.add(childMap);
		// 获取华语--到--韩国分类下的所有子分类
		Elements otherKind = document.select("#singer-cat-nav .blk");
		// 为数据组装准备Map

		// 最外层Map--推荐【子分类】
		Map<String, Object> childMap2 = null;
		// 内层Map--子分类【华语男歌手--url】
		Map<String, String> childMap1 = null;
		// 内层List--Map转换成List
		List<Object> list1 = null;
		for (int j = 0; j < otherKind.size(); j++) {
			childMap2 = new HashMap<>();
			list1 = new ArrayList<>();
			Element element = otherKind.get(j);
			// 获取华语到韩国所有子分类成功
			// System.out.println(element);
			// System.out.println("----------------------------------------------------");
			// 获得模块大分类名
			String otherKindName = element.select(".tit").text();
			// System.out.println(otherKindName);
			// 获得模块下小分类
			Elements elements2Little = element.select(".f-cb li");
			for (int k = 0; k < elements2Little.size(); k++) {
				childMap1 = new HashMap<String, String>();
				// 小分类名-----比如华语分类下【华语男歌手】
				String littleName = elements2Little.get(k).select("a").text();
				// 小分类名链接----比如华语分类下【华语男歌手】的链接
				String littleUrl = elements2Little.get(k).select("a").attr("abs:href");
				// System.out.println(littleName +
				// "-------------------------------" + littleUrl);
				childMap1.put("childName", littleName);
				childMap1.put("childUrl", littleUrl);
				list1.add(childMap1);
			}
			childMap2.put("Listkind", otherKindName);
			childMap2.put("content", list1);
			listAll.add(childMap2);
		}
		// 将数据清洗格式化
		// 20180113晚，小主人想去玩会儿啦，嘿嘿明儿接着写喽
		// 明日任务，分组格式化/根据分组找出歌手--->根据歌手找出热门歌曲---->根据歌曲找出热门评论
		// 分为几个servlet来获取

		// 此处测试一下
		JSONArray array = new JSONArray(listAll);
		System.out.println(array.toString());
		return array.toString();
	}

	// 测试成功--下面开始分组数据返回
	public static void main(String[] args) throws IOException {
		getSingerKindFromFindMusic();
	}
}
