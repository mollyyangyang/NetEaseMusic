package com.imollyunfei.service;

import java.io.IOException;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
* @ClassName: GetSongOfSingerService 
* @Description: 获取某个歌手全部热门50首歌曲(这里用一句话描述这个类的作用) 
* @author @mollyunfei
* @date 2018年1月15日 下午3:58:37 
*
 */
public class GetSongOfSingerService {
	//此处传入的是某个歌手的url
	//通过歌手的url来获取该歌手热门的歌曲
	public static String getSongInfo(String Singerurl) throws IOException{
		Document document=Jsoup.connect(Singerurl).timeout(60000).get();
		
		String json=document.select("textarea").eq(0).text();
		JSONArray array=new JSONArray(json);
		System.out.println(array.toString());
		return array.toString();
	}
	//测试能否获取文本--测试成功--直接从网页获取json
	public static void main(String[] args) throws IOException {
		getSongInfo("http://music.163.com/artist?id=10559");
	}
}
