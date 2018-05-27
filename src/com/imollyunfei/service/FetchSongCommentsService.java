	package com.imollyunfei.service;

import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.google.common.collect.ImmutableMap;

import com.imollyunfei.bean.MusicComment;
import com.imollyunfei.bean.MusicCommentMessage;
import com.imollyunfei.util.Constants;
import com.imollyunfei.util.EncryptUtils;

public class FetchSongCommentsService {
	// 通过歌曲ID获取评论API，网易对其进行了加密
	public static String parseCommentMessage(String songId) throws Exception {
		String songUrl = Constants.DOMAIN + "/song?id=" + songId;
		URL uri = new URL(songUrl);
		Document msdoc = Jsoup.parse(uri, 10000);
		System.out.println(msdoc);

		String secKey = new BigInteger(100, new SecureRandom()).toString(32).substring(0, 16);
		String encText = EncryptUtils.aesEncrypt(EncryptUtils.aesEncrypt(Constants.TEXT, "0CoJUm6Qyw8W8jud"), secKey);
		String encSecKey = EncryptUtils.rsaEncrypt(secKey);
		Response response = Jsoup.connect(Constants.NET_EASE_COMMENT_API_URL + songId + "/?csrf_token=")
				.method(Connection.Method.POST).header("Referer", Constants.BASE_URL).cookie("Cookie", "_ntes_nnid=b3b37859bd6d72df26518cc260bc8ede,1512728704533; _ntes_nuid=b3b37859bd6d72df26518cc260bc8ede; _ngd_tid=YC6IazEeqFHu6O8OGapPzpp3ieb6EFWc; vjuids=-78024abec.1603f1c505a.0.674ce5755d96b; usertrack=ezq0pVotC1Q7ETy3X0hHAg==; __e_=1; _ga=GA1.2.1040007537.1512901466; Province=010; City=010; P_INFO=2776793315@qq.com|1513561586|2|study|00&99|bej&1512728715&cloudmusic#bej&null#10#0#0|&0||2776793315@qq.com; vjlast=1512887308.1516345184.12; NNSSPID=88670bec52294444967dc5e8702081f6; vinfo_n_f_l_n3=6c066f29f155c26c.1.3.1512887308400.1515992593550.1516345212253; JSESSIONID-WYYY=nnBQQHgvIrdDu8INMjA9%2B7FD88M9gg3l%2BlyBGU5%2BuCmryoEg8cD4%2FaUc02llu9U2kZt10cowjnvst%5CRz7xwXvJ5fI0Yd7oE%5C6RaGGFQw42cOiONvpxCjP%2FPe9SKi%5Cyy8M3BeAfB5lr%2FF9aFRGOHJUgm%5Ck3fq0Q3BRsAAtDHaWn1FkbgV%3A1516366864195; _iuqxldmzr_=32; MUSIC_U=40931bb9b84174586428badb45e42e24d358613ef656c39ebb972348a2cf3387ee26998168bced1ae515fbd3b10c08a22c404e73e1e89313de39c620ce8469a8; __remember_me=true; __csrf=19d80190c3c9f1ca3869f184ad4b08c1; __utma=94650624.315779462.1512728706.1516355578.1516365065.28; __utmb=94650624.9.10.1516365065; __utmc=94650624; __utmz=94650624.1516365065.28.12.utmcsr=baidu|utmccn=(organic)|utmcmd=organic")
				.data(ImmutableMap.of("params", encText, "encSecKey", encSecKey)).execute();

		Object res = JSON.parse(response.body());

		if (res == null) {
			return null;
		}

		MusicCommentMessage musicCommentMessage = new MusicCommentMessage();

		int commentCount = (int) 3;
		int hotCommentCount = (int) JSONPath.eval(res, "$.hotComments.size()");
		int latestCommentCount = (int) JSONPath.eval(res, "$.comments.size()");

		musicCommentMessage.setSongTitle(msdoc.title());
		musicCommentMessage.setSongUrl(songUrl);
		musicCommentMessage.setCommentCount(commentCount);

		List<MusicComment> ls = new ArrayList<MusicComment>();

		if (commentCount != 0 && hotCommentCount != 0) {

			for (int i = 0; i < hotCommentCount; i++) {
				String nickname = JSONPath.eval(res, "$.hotComments[" + i + "].user.nickname").toString();
				String time = EncryptUtils.stampToDate((long) JSONPath.eval(res, "$.hotComments[" + i + "].time"));
				String content = JSONPath.eval(res, "$.hotComments[" + i + "].content").toString();
				String appreciation = JSONPath.eval(res, "$.hotComments[" + i + "].likedCount").toString();
				ls.add(new MusicComment("hotComment", nickname, time, content, appreciation));
			}
		} else if (commentCount != 0) {

			for (int i = 0; i < latestCommentCount; i++) {
				String nickname = JSONPath.eval(res, "$.comments[" + i + "].user.nickname").toString();
				String time = EncryptUtils.stampToDate((long) JSONPath.eval(res, "$.comments[" + i + "].time"));
				String content = JSONPath.eval(res, "$.comments[" + i + "].content").toString();
				String appreciation = JSONPath.eval(res, "$.comments[" + i + "].likedCount").toString();
				ls.add(new MusicComment("latestCommentCount", nickname, time, content, appreciation));
			}
		}

		musicCommentMessage.setComments(ls);
		JSONArray array = new JSONArray(musicCommentMessage.getComments());
		return array.toString();
	}

	// 测试能否获取评论----哈哈测试成功--获取得到所有评论
	public static void main(String[] args) throws Exception {
		// 压力测试得知，频繁请求返回数据为空
		System.out.println(new FetchSongCommentsService().parseCommentMessage("25727803"));
	}
}
