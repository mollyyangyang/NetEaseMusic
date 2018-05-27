<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<base href="<%=basePath%>">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=0">

<!-- 强制移动设备以app模式打开页面(即在移动设备下全屏，仅支持部分浏览器) -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="full-screen" content="yes">
<!--UC强制全屏-->
<meta name="browsermode" content="application">
<!--UC应用模式-->
<meta name="x5-fullscreen" content="true">
<!--QQ强制全屏-->
<meta name="x5-page-mode" content="app">
<!--QQ应用模式-->


<link rel="shortcut icon" href="http://imollyunfei.top/images/i32.ico" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/srollerBar.css">
<title>歌手-检索页</title>
</head>
<!--
此页分为男歌手、女歌手
点击歌手进入歌手对应的热门歌曲
再次点击某一歌曲可以查看最新的热门评论故事
索性一次性将所有的网易云热门歌手的评论都给获取了！！！
-->
<style>
* {
	margin: 0px;
	padding: 0px;
}

.top {
	margin-top: 5px;
	width: 90%;
	height: 80px;
	margin-right: 5%;
	margin-left: 5%;
	background: #41b17d;
	border-radius: 30px;
}

.welcome {
	text-align: center;
	font-family: Consolas;
	font-size: 30px;
	font-weight: 800;
	color: ghostwhite;
	padding-top: 20px;
}

.content {
	border-radius: 50px;
	width: 98%;
	height: 545px;
	background-color: #89cee8;
	margin-top: 5px;
	margin-left: 1%;
	margin-right: 1%;
	margin-bottom: 5px;
}

.contentT {
	position: absolute;
	left: 5%;
	right: 3%;
	top: 20%;
	text-align: center;
	border-collapse: collapse;
	font-size: 25px;
	font-weight: 800;
	color: #bf5278;
}

.contentT tr, .contentT td {
	width: 200px;
	height: 100px;
}

.contentT tr:first-child {
	font-weight: 800;
	font-size: 40px;
}

.contentT tr:first-child td:hover {
	color: #0271ff;
	font-size: 50px;
}

a {
	text-decoration: none;
}

a:hover {
	font-size: 30px;
	color: transparent;
	-webkit-background-clip: text;
	background-image: -webkit-gradient(linear, left top, right top, color-stop(0, #f22),
		color-stop(0.15, #f2f), color-stop(0.3, #22f), color-stop(0.45, #2ff),
		color-stop(0.6, #2f2), color-stop(0.75, #2f2), color-stop(0.9, #ff2),
		color-stop(1, #f22));
}

.fg {
	background-color: #ff658c;
}

.footer {
	text-align: center;
	font-weight: 800;
	font-family: "Microsoft YaHei UI";
	font-size: 20px;
	background-color: whitesmoke;
	color: #101010;
}

input {
	position: absolute;
	left: 36%;
	right: 40%;
	bottom: 15%;
	width: 300px;
	height: 40px;
	font-size: 30px;
	font-weight: 800;
	color: #ff6869;
	background-color: #47ff8f;
	margin-bottom: 5px;
}

.singerT {
	position: absolute;
	left: 30%;
	right: 30%;
	top: 20px;
	bottom: 10px;
	width: 500px;
	height: 600px;
	background-color: #ffbac2;
	border-radius: 30px;
	overflow-y: auto;
}

.signOut {
	position: fixed;
	right: 480px;
	top: 27px;
	font-size: 60px;
	border: 2px solid #ff3225;
	width: 20px;
	height: 20px;
	cursor: pointer;
}

.sign {
	font-size: 30px;
	position: fixed;
	right: 485px;
	top: 20px;
	font-family: Consolas;
	font-style: oblique
}

.signOut1 {
	position: fixed;
	right: 400px;
	top: 27px;
	font-size: 60px;
	border: 2px solid #ff3225;
	width: 20px;
	height: 20px;
	cursor: pointer;
}

.sign1 {
	font-size: 30px;
	position: fixed;
	right: 405px;
	top: 20px;
	font-family: Consolas;
	font-style: oblique
}

.signOut2 {
	position: fixed;
	right: 120px;
	top: 27px;
	font-size: 60px;
	border: 2px solid #ff3225;
	width: 20px;
	height: 20px;
	cursor: pointer;
}

.sign2 {
	font-size: 30px;
	position: fixed;
	right: 125px;
	top: 20px;
	font-family: Consolas;
	font-style: oblique
}

.singerTableT {
	text-align: center;
	position: absolute;
	width: 300px;
	left: 20%;
	right: 20%;
	top: 10px;
	bottom: 10px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
	font-family: Consolas;
	font-weight: 800;
}

.singerTableT tr:first-child {
	font-size: 30px;
}

.singerTableT tr:first-child:hover {
	font-size: 35px;
	color: #24ff9b;
}

.singerTableT tr:not(:first-child):hover {
	color: #353aff;
}

.singerTableT tr {
	height: 50px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
}

.singerTableT td {
	width: 100px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
}

.songT {
	position: absolute;
	left: 30%;
	right: 30%;
	top: 3%;
	bottom: 5%;
	width: 600px;
	height: 600px;
	background-color: #ffbac2;
	border-radius: 30px;
	overflow-y: auto;
}

.songTableT {
	text-align: center;
	position: absolute;
	width: 500px;
	left: 10%;
	right: 10%;
	top: 50px;
	bottom: 10px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
	font-family: Consolas;
	font-weight: 800;
}

.songTableT tr:first-child {
	font-size: 25px;
}

.songTableT tr:first-child td:hover {
	font-size: 35px;
	color: #24ff9b;
}

.songTableT tr:not(:first-child) td:hover {
	font-size: 35px;
	color: #ff7562;
}

.songTableT tr {
	height: 50px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
}

.songTableT td {
	width: 100px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
}

.info {
	text-align: center;
	margin-top: 10px;
	font-size: 25px;
	font-family: "Microsoft YaHei UI";
	font-weight: 800;
}

.songCommentT {
	position: absolute;
	left: 6%;
	right: 20%;
	top: 3%;
	bottom: 5%;
	width: 1200px;
	height: 600px;
	background-color: #ffbac2;
	border-radius: 30px;
	overflow-y: auto;
}

.songCommentTableT {
	text-align: center;
	position: absolute;
	width: 640px;
	left: 5%;
	right: 10%;
	top: 50px;
	bottom: 10px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
	font-family: Consolas;
	font-weight: 800;
	word-break: break-all;
}

.songCommentTableT tr:first-child {
	font-size: 20px;
}

.songCommentTableT tr:first-child td:hover {
	font-size: 25px;
	color: #24ff9b;
}

.songCommentTableT tr:not(:first-child) td:hover {
	font-size: 20px;
	color: #ff7562;
}

.songCommentTableT tr {
	height: 50px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
}

.songCommentTableT td {
	width: 100px;
	border: 3px solid cadetblue;
	border-collapse: collapse;
}

.nickname {
	text-align: center;
	color: #8e3ca9;
}

.contentC {
	text-align: left;
}

.lyricDiv {
	position: absolute;
	right: 5%;
	top: 70px;
	width: 400px;
	border: 2px solid green;
	background: #C5FEE1;
	top: 50px;
	overflow-y: auto;
}
</style>
<body>
	<div class="top">
		<p class="welcome">欢迎您来到网易云音乐热评现场！！！</p>
	</div>
	<div class="content">
		<table class="contentT">
			<tr>
				<td>推荐</td>
				<td>华语</td>
				<td>欧美</td>
				<td>日本</td>
				<td>韩国</td>
				<td>其他</td>
			</tr>
			<tr>
				<td>推荐歌手</td>
				<td>华语男歌手</td>
				<td>欧美男歌手</td>
				<td>日本男歌手</td>
				<td>韩国男歌手</td>
				<td>其他男歌手</td>
			</tr>
			<tr>
				<td>入驻歌手</td>
				<td>华语女歌手</td>
				<td>欧美女歌手</td>
				<td>日本女歌手</td>
				<td>韩国女歌手</td>
				<td>其他女歌手</td>
			</tr>
			<tr>
				<td></td>
				<td>华语组合/乐队</td>
				<td>欧美组合/乐队</td>
				<td>日本组合/乐队</td>
				<td>韩国组合/乐队</td>
				<td>其他组合/乐队</td>
			</tr>
		</table>
		<input type="button" value="云飞点一点">
	</div>
	<hr class="fg" />
	<p class="footer">
		<img src="http://imollyunfei.top/images/i32.ico" alt="林飞集团"
			width="25px" height="25px">@Linfei.Lord —— <small>make
			your life beautiful.</small>
	</p>
	<div class="singerTest"></div>
	<div class="songTest"></div>
	<div class="songCommentTest"></div>
	<!-- http://music.163.com/song/media/outer/url?id=28949444.mp3 -->
	<div class="music">
		<audio id="music1" src='mp3/ilu-ChianoSky.mp3' autoplay="autoplay"
			preload="auto" loop="loop"></audio>
	</div>
</body>
<script type="text/javascript">
	//歌词解析单独方法
	var unescapeHTML = function(lrc) {
		var t = document.createElement("div");
		return t.innerHTML = lrc + "";
	}
	$(function() {
		$
				.ajax({
					type : 'post',
					url : 'ListGrroupServlet',
					async : false,
					data : {},
					dataType : 'json',
					success : function(data) {
						//获取得到tr个数
						var len = $(".contentT tr").length
						//alert(len)
						//测试成功--屏蔽tr个数
						for (var i = 0; i < len - 1; i++) {
							for (var j = 0; j < 6; j++) {
								//alert($(".contentT").find("tr").eq(i).html())
								if (i == 2 && j == 0) {
									console
											.log("经过data[0].content[2].childUrl!")
									continue;
								} else if (j == 0 && i == 1) {
									$(".contentT")
											.find("tr:not(:first)")
											.eq(i)
											.find("td")
											.eq(j)
											.html("")
											.html(
													"<a class='kindSingListSpecial' href='javascript:void(0);' target='_blank' nameUrl='"
															+ data[j].content[i].childUrl
															+ "'>"
															+ data[j].content[i].childName
															+ "</a>")

								} else {
									console.log(j + "-------------" + i)
									$(".contentT")
											.find("tr:not(:first)")
											.eq(i)
											.find("td")
											.eq(j)
											.html("")
											.html(
													"<a class='kindSingList' href='javascript:void(0);' target='_blank' nameUrl='"
															+ data[j].content[i].childUrl
															+ "'>"
															+ data[j].content[i].childName
															+ "</a>")

								}
							}
						}
					}
				});
	})

	//a标签被点击，弹出nameUrl
	$(document)
			.delegate(
					".kindSingList",
					"click",
					function() {
						//测试成功
						//alert($(this).html())
						//获取对应歌手分类的url		
						//	alert($(this).attr("nameUrl"))
						//测试能否跳转成功
						//window.open($(this).attr("nameUrl"), "_blank")
						//--->当某个歌手分类进行点击的时候，上传该url，后台捕获，并返回数据
						$
								.ajax({
									type : 'post',//不涉及到敏感信息--post或者get方式皆可
									url : 'SingerServlet',
									async : false,
									dataType : 'json',
									data : {
										"singerUrl" : $(this).attr("nameUrl")
									},
									success : function(data) {
										//首先进行表格清空操作
										$(".singerTest").html("")
										$(".singerT").slideUp(1)
										$(".songT").slideUp(1)
										//接着进行数据加载
										$(".singerTest")
												.html(
														"<div class='singerT'><div class='signOut' title='关闭此悬浮框'><span class='sign'>x</span></div><table class='singerTableT'></table></div>");
										for (var i = 0; i < data.length; i++) {
											$(".singerTableT")
													.append(
															"<tr><td><a class='singerDeInfo' href='javascript:void(0)' singerUrl='"
																	+ data[i].singerMainPage
																	+ "'>"
																	+ data[i].singerName
																	+ "</a></td></tr>");
										}
									}
								})
					})
	//入驻歌手特殊处理
	//此处歌手加载有问题---20180115--20180116待解决
	$(document)
			.delegate(
					".kindSingListSpecial",
					"click",
					function() {
						$
								.ajax({
									type : 'post',//不涉及到敏感信息--post或者get方式皆可
									url : 'SingerInfoSpecialServlet',
									async : false,
									dataType : 'json',
									data : {},
									success : function(result) {
										//测试成功
										//alert("666666666666666")
										//alert(result.artists[0].id)
										//alert(result.artists.length)
										//测试成功--出错在于循环数组的查找出错，应该是result.artists.length而不是result.length
										//for (var i = 0; i < result.artists.length; i++) {
										//console
										//	.log('http://music.163.com/artist?id='
										//		+ result.artists[i].id
										//	+ "---------------------"
										//+ result.artists[i].name)
										//}

										//下面开始真实数据展现
										//首先进行表格清空操作
										$(".singerTest").html("")
										$(".singerT").slideUp(1)
										$(".songT").slideUp(1)
										//接着进行数据加载
										$(".singerTest")
												.html(
														"<div class='singerT'><div class='signOut' title='关闭此悬浮框'><span class='sign'>x</span></div><table class='singerTableT'></table></div>");
										for (var i = 0; i < result.artists.length; i++) {
											$(".singerTableT")
													.append(
															"<tr><td><a class='singerDeInfo' href='javascript:void(0)' singerUrl='http://music.163.com/artist?id="
																	+ result.artists[i].id
																	+ "'>"
																	+ result.artists[i].name
																	+ "</a></td></tr>");
										}
									}
								})
					})
	//当某个歌手被点击时
	$(document)
			.delegate(
					".singerDeInfo",
					"click",
					function() {
						//测试成功
						//alert("666")
						//关闭当前页面之前--首先获取歌手url
						var singerurl = $(this).attr("singerUrl");
						//alert(singerurl)
						//	点击某一歌手之后，关闭当前分类下的歌手
						$(".singerT").slideUp(1)
						$
								.ajax({
									type : 'get',
									async : false,
									url : 'GetSongInfoServlet',
									data : {
										"SingerUrl" : singerurl
									},
									dataType : 'json',
									success : function(data) {
										//alert("666777")
										//首先进行清空操作
										$(".songTest").html("")
										//接着填入数据
										$(".songTest")
												.append(
														"<div class='songT'><div class='signOut1'><span class='sign1'>x</span></div><p class='info'>当前歌手</p><table class='songTableT'><tr><td>歌曲</td><td>来自专辑</td></tr></table></div>")
										for (var i = 0; i < data.length; i++) {
											$(".songTableT")
													.append(
															"<tr><td><a class='songDeInfo' href='javascript:void(0)' songUrl='http://music.163.com/song?id="
																	+ data[i].id
																	+ "'title='点击进入歌曲'>"
																	+ data[i].name
																	+ "</a></td><td><a class='albumInfo' href='javascript:void(0)'  ablumPic='"
																	+ data[i].album.picUrl
																	+ "' title='点我-查看专辑照片'>"
																	+ data[i].album.name
																	+ "</a></td></tr>");
										}
									}
								})
					})
	//此处写一个公共方法以便小主人看评论同时播放背景音乐
	$(document)
			.delegate(
					".songDeInfo",
					"click",
					function() {
						//点击的同时，加载一个背景音乐--ajax获取背景音乐
						var songUrl = $(this).attr("songUrl") + "";
						//alert(songUrl)
						//获取得到songId--供后面ajax使用
						var songId = songUrl
								.substring(songUrl.indexOf("=") + 1)
						//alert(songId)
						var songName = $(this).text()
						//1.接着进行ajax操作------获取热评
						$
								.ajax({
									type : 'POST',
									url : 'FetchSongCommentsServlet',
									async : false,
									data : {
										"songId" : songId
									},
									dataType : 'json',
									success : function(data) {
										//2.接着进行ajax加载--在内部进行歌词加载
										$
												.ajax({
													type : 'GET',
													url : 'http://music.163.com/api/song/media?id='
															+ songId,
													async : false,
													data : {},
													dataType : 'jsonp',
													success : function(LRC) {
														//alert("获取" + songId + "的热评成功！！！")
														//首先进行清空操作
														//测试成功--无须再进行校验
														//alert("获取歌词成功啦")
														$(".songCommentTest")
																.html("")
														//首先对歌词体进行清空操作，然后再进行加载
														$(".lyricDiv").html("")
														$(".songCommentTest")
																.html(
																		"<div class='songCommentT'><div class='signOut2'><span class='sign2'>x</span></div><p class='info'>当前歌曲《"
																				+ songName
																				+ "》热评TOP"
																				+ data.length
																				+ "</p><table class='songCommentTableT'><tr><td>评论信息</td></tr></table><div class='lyricDiv'><div></div>")
														//首先对歌词体进行清空操作，然后再进行加载
														$(".lyricDiv").html("")
														for (var ii = 1; ii < unescapeHTML(
																LRC.lyric)
																.split("[").length; ii++) {
															$(".lyricDiv")
																	.append(
																			'<br>['
																					+ unescapeHTML(
																							LRC.lyric)
																							.split(
																									"[")[ii])
														}
														for (var i = 0; i < data.length; i++) {
															$(
																	".songCommentTableT")
																	.append(
																			"<tr><td><div class='contentC'><div class='nickname'>"
																					+ data[i].nickname
																					+ "</div><div class='commentDate'>评论时间："
																					+ data[i].commentDate
																					+ "</div><div class='appreciation'>点赞数:"
																					+ data[i].appreciation
																					+ "</div><div class='contentInfo	'>评论内容:"
																					+ data[i].content
																					+ "</div></div></td></tr>")
														}

													}
												});
									}
								});
						//修改--不用ajax异步请求-->直接加载来测试能否避免歌曲有时403的情况
						//3.接着ajax异步请求---获取播放背景音乐操作---歌曲流加载放在最后，防止加载北京音乐失败
						//暂时没找到办法消除阻塞
						//alert("开始放歌啦")
						var audio = $("#music1").get(0);
						audio.load()
						audio.src = 'http://music.163.com/song/media/outer/url?id='
								+ songId + '.mp3';
						//哈哈此种方式似乎完美解决了有时403的情况
						audio.addEventListener("canplaythrough", function() {
							audio.paused && (audio.play());
						}, false);
					});
	//专辑点击
	$(document)
			.delegate(
					".albumInfo",
					"click",
					function() {
						window
								.open(
										$(this).attr("ablumpic"),
										"",
										"width=900,height=600,top=80,left=-200,toolbar=no, menubar=no, scrollbars=no, resizable=no")
					})
	//退出模块-->统一放到最后
	//由于是加载出来的因此此处需要使用事件委托
	$(document).delegate(".signOut", "click", function() {
		$(".singerT").slideUp(2000, function() {
			$(".singerTest").html("")
		})
	})
	//由于是加载出来的因此此处需要使用事件委托
	$(document).delegate(".signOut1", "click", function() {
		$(".songT").slideUp(2000, function() {
			$(".songTest").html("")
		})
	})
	//由于是加载出来的因此此处需要使用事件委托
	$(document).delegate(".signOut2", "click", function() {
		$(".songCommentTableT").slideUp(10, function() {
			$(".songCommentTest").html("")
		})
	})
</script>
</html>