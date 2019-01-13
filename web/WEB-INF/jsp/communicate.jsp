<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />

		<title>IM</title>
		<!--<link rel="stylesheet" type="text/css" href="https://necolas.github.io/normalize.css/4.1.1/normalize.css"/>消除浏览器之间细微的差异-->
		<!-- 新 Bootstrap 3核心 CSS 文件 -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="css/sprite.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/ux_1462265947_5562909/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/iScroll.css" />
		<link rel="stylesheet" type="text/css" href="css/iconfont.css" />
		<link rel="stylesheet" type="text/css" href="css/sco.message.css" />
		<link rel="stylesheet" type="text/css" href="js/dist/bootstrap-tagsinput.css" />
	
		<link rel="stylesheet" type="text/css" href="css/media.css" />


		<script src="js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8" ></script>
		<script src="js/pako.js" type="text/javascript" charset="utf-8" ></script>
		<script src="js/base64.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/emoji.js" type="text/javascript" charset="utf-8" ></script>
		<script src="js/MD5.min.js" type="text/javascript" charset="utf-8" ></script>
		<script src="https://app.cloopen.com/im50/ytx-web-im-min-5.3.2r14.js" type="text/javascript" charset="utf-8"></script>

		<!--[if lt IE 9]>
		<script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="js/respond.min.js" type="text/javascript" charset="utf-8"></script>
		<![endif]-->
		<style type="text/css">
			@media all and (orientation:landscape) and (max-width:700px) {
				body {
					-ms-flex-align: baseline;
					-webkit-align-items: baseline;
					-webkit-box-align: baseline;
					align-items: baseline;
				}
			}
		</style>
		<script type="text/javascript">
			// Copyright 2014-2015 Twitter, Inc.
			// Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
			if(navigator.userAgent.match(/IEMobile\/10\.0/)) {
				var msViewportStyle = document.createElement('style')
				msViewportStyle.appendChild(
					document.createTextNode(
						'@-ms-viewport{width:auto!important}'
					)
				)
				document.querySelector('head').appendChild(msViewportStyle)
			}
		</script>
		<script>
			var nua = navigator.userAgent;
			var is_android = ((nua.indexOf('Mozilla/5.0') > -1 && nua.indexOf('Android ') > -1 && nua.indexOf('AppleWebKit') > -1) && !(nua.indexOf('Chrome') > -1));
			if(is_android) {
				$('select.form-control').removeClass('form-control').css('width', '100%');
			}
		</script>
	</head>

	<body>
		
		<div class="container">
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 llist">
				<div class="personInfo">
					<img src="images/login/logo_26.png" data-toggle="modal" data-target="personInfor" id="setPersonalInfo" />
					<p id="page_nickname">IM体验demo</p>
				</div>
				<ul class="nav">
					<li style="display: none;">
						<span class="img communicate "></span>沟通

					</li>
					<li class="active">
						<span class="img contacts"></span>联系人
						<span class="refresh"></span>
					</li>
					<li>
						<span class="img group"></span>群组
					</li>
				</ul>
				<div class="bottomBut">
					<!--<ul class="navBot">
						<li>
							<span class="img search2 active" onclick="IM.serchGroup();"></span>搜索
						</li>
						<li>
							<span class="img"></span>创建群
						</li>
						<li>
							<span class="img create_a_discussion_group"></span>创建讨论组
						</li>
						<li>
							<span class="img set"></span>设置
						</li>
					</ul>-->
					<span class="img add openMore"></span>
				</div>
				<div class="bottomBut">
					<span class="logout">退出</span>
				</div>
			</div>

			<div class="col-lg-2 chat_group">

				<!--<div class="col-lg-12  chat_list">-->
					<!--&lt;!&ndash;<div class="searchDiv">&ndash;&gt;-->
						<!--&lt;!&ndash;<input type="text" id="add_chat" value="" placeholder="添加聊天" /><label data-button="addToChat" class="addToChat" style="font-size: 18px; color: #ccc; margin-left: 5px; cursor: pointer;">+</label>&ndash;&gt;-->
					<!--&lt;!&ndash;</div>&ndash;&gt;-->
					<!--<ul class="discuss normal_chat">-->
					<!--</ul>-->
				<!--</div>-->

				<div class="col-lg-12  contact_list ">
					<!--<div class="searchDiv">-->
						<!--<input type="text" id="searchContact" value="" placeholder="搜索" />-->
					<!--</div>-->
					<div class="searchDiv">
						<input type="text" id="add_chat" name="addFriend" value="" placeholder="添加聊天" /><label data-button="addToChat" class="addToChat" style="font-size: 18px; color: #ccc; margin-left: 5px; cursor: pointer;">+</label>
					</div>
					<!--//消息置顶  新div  增加contact_top的class  或者可自行更名-->
					<ul class="discuss normal_chat contact_top" >
						<!--<li class="active"><img src="images/head_portrait/discussion_header/discussio_groups_01.png" />
							<div>
								<span class="discuss_name">系统消息</span><span class="noticeQ">908</span>
							</div>
						</li>-->
					</ul>

					<!--//消息置顶  原div 增加data-list="chat" 属性 或者可自行更名  记=-->
					<ul class="discuss normal_chat" data-list="chat">
						<!--<li class="active"><img src="images/head_portrait/discussion_header/discussio_groups_01.png" />
							<div>
								<span class="discuss_name">系统消息</span><span class="noticeQ">908</span>
							</div>
						</li>-->
					</ul>
				</div>

				<div class="col-lg-12 group_list  hide_list ">
					<div class="searchDiv">
						<input type="text" id="searchGroup" value="" placeholder="搜索" />
					</div>
					<ul class="discuss">
					</ul>
				</div>
			</div>
			
			<div class="col-lg-6 chat_group_window rlist" data-window-type="chat" data-chat-with="" data-chat-type="null">
				<div class="receiver">
					<img src="images/head_portrait/head_portrait_group/groups_head_portrait_02.png">
					<span class="name cNickName">欢迎使用容联IM PLUS</span>
					<span class="isTyping" data-status="isTyping"></span>
					<span class="img video right" data-voip="video"></span>
					<span class="img audio right" data-voip="audio"></span>
					<span class="group_set right" data-gruopid="null"></span>
				</div>
				<div class="chatting">
					<div class="chats iScroll">
						<div class="historyTime">2016.05.29</div>
						<div class="oneTextR" data-msgid="80099B72EA2B8B194BA90D93F6285628|7">
							<p></p>
							<img src="images/head_portrait/head_portrait_group/groups_head_portrait_02.png" class="left">
							<div id="" class="dialog">
								<span class="sjx"></span>
								<pre class="contentText">此窗口为展示窗口，请勿操作！</pre>
							</div>
						</div>
					</div>
				</div>
				<div class="inputText">
					<span class="img expression"></span>
					<span class="img picture" data-send="pic"></span>
					<span class="img file" data-send="file"></span>
					<span class="img burnmsg" data-firemsg="false"></span>
					<span class="img photograph" data-takepic="1"></span>
					<button class="sendB right" data-button="send_msg">发送</button>
					<div class="inputSend">
						<!--<textarea class="col-lg-10 char_input" data-chat-input="chatinput" spellcheck="false"></textarea>-->
						<div class="col-lg-10 char_input" contenteditable="true" data-chat-input="chatinput"></div>
					</div>
					<ul class="atMsg">
					</ul>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 rlist " style="display: none;">
				<div class="receiver">
				</div>
				<div class="videoPhone">
					<img src="images/toux.png" alt="" />
					<p class="name"></p>
					<p></p>
					<span></span>
					<span></span>
					<div>
						<button class="talkBtn"><span class="contact talk "></span>与他沟通(IM)</button>
						<div><button class="conversationBtn"><span class="contact conversation "></span>音频通话</button><button class="videoBtn"><span class="contact video "></span>视频通话</button></div>
					</div>
				</div>
			</div>
			<div class="callmsg_alert col-lg-3">
				<!--<div class="audioAline">
					<div class="imgDiv">
						<img src="images/voipcall.png">
					</div>
					<div class="audioDiv">
						<p class="name">系统</p><span class="color6060">777邀请你加入群组朝阳群众</span>
						<div class="audioBtn"><button>接受</button>
							<button>拒绝</button>
						</div>
					</div>
				</div>-->

			</div>
		</div>

		<div class="video_view" data-video-with="0" data-call-state="1" data-call-type="1">
			<p class="video_drop"></p>
			<video class="video_long_distance" data-video="distance" autoplay controls></video>
			<video class="video_local" data-video="local"  autoplay muted controls></video>
			<p class="video_bottom">
				<span class="hangup" data-btn="cancelVideo">取消</span>
				<span class="hangup" data-silence="false">关闭麦克风</span>
				<span class="hangup" data-camera="false">关闭摄像头</span>
			</p>
		</div>

		<div class="modal fade in" id="group" data-drop="true" role="dialog" aria-labelledby="gridSystemModalLabel" data-groupid="0" data-iscreater="false" data-grouptype="0" data-member="1" data-group-rule = "null">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title"><b class="preGroup">群组：</b><b class="preTao">讨论组：</b><span data-group="gname" contenteditable="true" data-replace="groupname"></span><img src="images/chang.png" /></h4>
						<h4 class="gpre"><b class="preGroup">群ID：</b><b class="preTao">讨论组ID：</b><b data-show="gid"></b></h4>
					</div>
					<div class="modal-body">
						<ul class="" style="overflow: overlay;">
							<li class="modal-left">
								<p class="setting">设置</p>
								<div class="groupSet">
									<label name="isnotice" for="isnotice" data-btn="groupNotice" class="checked">消息免打扰</label>
									<!--<input type="checkbox" id="new" name="flag" value="new">
									<label name="new" for="new">新消息聊天</label>
									<input type="checkbox" id="top" name="flag" value="top">
									<label name="top" for="top">置顶消息</label>-->
								</div>
								<!--<p class="green cleanhistory">清空聊天记录</p>-->
								<p>公告</p>
								<div class="setting affiche" data-replace="declared" contenteditable="true" ></div>

								<div class="tags-default">
									<input type="text" value="" data-role="tagsinput" placeholder="邀请成员" data-group="invite" />
								</div>
								<button data-btn="invitemember" class="invite">邀请加入</button>
								<button type="button" class="btn dismiss" data-btn="dismissgroup">解散群组</button>
								<button type="button" class="btn dismiss" data-btn="quitgroup">退出群组</button>
								<button type="button" class="btn dismiss" data-btn="quitdiscuss">退出讨论组</button>
							</li>
							<li class="modal-right">
								<ul class="card_group" data-group-rule="0">
									<!--<li class="normal_menmber">
										<img src="images/head_portrait/headerX40.png">
										<p class="cg_name">你不好，朋友</p>
										<button class="kick">踢出</button>
										<button class="banned">禁言</button>
										<button class="transfer">转让</button>
										<button class="manager">设为管理员</button>
									</li>-->
								</ul>
							</li>
                            <div class="clear"></div>
						</ul>

					</div>
				</div>
			</div>
		</div>

		<div class="modal fade  " id="login" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static" >
			<!-- data-backdrop="static"  禁止点击背景关闭模态窗事件 -->
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<!--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>-->
						<h4 class="modal-title"><img src="images/login/logo_26.png" width="20px"/>IM体验Demo</h4>
						<img src="images/login/logo_26.png" class="loginImg" />
					</div>
					<div class="modal-body">
						<div class="login1">
							<div class="form-group">
								<input type="text" name="loginUser" class="input-sm" id="loginByPhone" placeholder="输入手机号" maxlength="64" oninput="IM.checkLoginNum(this)" value="" autofocus>
							</div>
							<div class="error">
								对不起，您输入的账号不合法
							</div>
							<button type="button" class="btn login phoneLogin" onclick="IM.Do_login(this)">登陆</button>
						</div>

						<div class="login2">
							<input type="text" class="input-sm" id="voip_account" placeholder="VoIp号码" value="">
							<input type="password" class="input-sm" id="voip_pwd" placeholder="VoIp密码" value="">
							<div class="loginSet">
								<!--<input type="checkbox" id="remember" value="remember" />-->
								<!--<label name="noone" class="checked" for="remember">记住密码</label>-->
								<!--<input type="checkbox" id="auto" value="auto" />-->
								<!--<label name="noone" class="checked" for="auto">自动登录</label>-->
								<div class="error">
									对不起，您输入的密码不正确
								</div>
							</div>
							<button type="button" class="btn login" onclick="IM.Do_login_byVoip()">登陆</button>
						</div>

						<div class="loginfooter">
							<span class="changeLogin">切换登陆模式</span>|<span>About</span>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="modal fade" id="personInfor" role="dialog" aria-labelledby="gridSystemModalLabel" data-drop="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title" id="gridSystemModalLabel">完善个人信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group ">
								<label for="sName" class=" control-label">账号：</label>
								<input type="email" class="input-sm " id="userAcc" disabled>
							</div>
							<div class="form-group ">
								<label for="sName" class=" control-label">昵称：</label>
								<input type="email" class="input-sm " id="person_nickname" placeholder="">
							</div>
							<div class="form-group">
								<label for="sLink" class="control-label">性别：</label>
								<!--<input type="" class="input-sm " id="sLink" placeholder="">-->
								<select name="" class="input-sm" id="person_sex">
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</div>
							<div class="form-group">
								<label for="sOrd" class="control-label">生日：</label>
								<input type="date" class=" input-sm " id="person_birth" placeholder="请选择时间">
							</div>
							<div class="form-group">
								<label for="sKnot" class="control-label">说明：</label>
								<textarea class=" input-sm " id="person_sign" placeholder=""></textarea>
							</div>
							<button type="button" class="btn save" onclick="IM.setPersonalInfo();">保 存</button>
						</form>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="modal fade" id="aboutMore" role="dialog" aria-labelledby="gridSystemModalLabel" data-drop="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="about_more" >
						<div class="more_titile">
							MORE
						</div>
						<div class="more_r_title">
							<p><span class="iconfont closeMore">&times;</span></p>
						</div>
						<div class="clear"></div>
						<div class="more_body_left">
							<ul>
								<!--<li>编辑我的资料</li>-->
								<li class="active">搜索群组</li>
								<li>创建群组</li>
								<li>创建讨论组</li>
							</ul>
						</div>
						<div class="more_body_right">
							<dl>
								<dd class="searchGroup" style="display: block;">
									<span class="searchWay active">按群组ID精确查询</span><span class="searchWay">按群组名称精确查询</span>
									<form action="" method="post" id="searchWayValue">
										<input type="radio" name="search" value="1" checked="checked" />
										<input type="radio" name="search" value="2" />
									</form>
									<div class="clear"></div>
									<input type="text" data-input="searchGroup" /><label data-btn="searchGroup"></label>
									<div class="clear"></div>
									<ul class="card_group">

									</ul>
								</dd>
								<dd class="creat_group" style="display: none;">
									<dl>
										<form id="cGroup">
											<dt>名称</dt>
											<dd>
												<input type="text" value="" name="groupName" required="required" />
											</dd>
											<dt>公告</dt>
											<dd>
												<input rows="" cols="" value="" name="declared" />
											</dd>
											<dt>省份</dt>
											<dd>
												<input type="text" value="" name="province" />
											</dd>
											<dt>城市</dt>
											<dd>
												<input type="text" value="" name="city" />
											</dd>

											<dt>类型</dt>
											<dd>
												<select name="groupType" style="">
													<option value="1" selected="selected">同事</option>
													<option value="1">同学</option>
													<option value="2">朋友</option>
												</select>
											</dd>
											<dt>权限</dt>
											<dd>
												<select name="permission" style="">
													<option value="1" selected="selected">成员可随意加入</option>
													<option value="2">需要身份验证</option>
													<option value="3">仅管理员能邀请</option>
												</select>
											</dd>
										</form>
										<button class="confirm" data-btn="creategroup" data-cgroup-type="2">创建</button>
									</dl>
								</dd>
								<dd class="creat_group" style="display: none;">
									<dl>
										<form id="cDisguss">
											<dt>名称</dt>
											<dd>
												<input type="text" value="" name="groupName" required="required" />
											</dd>
											<dt>成员</dt>
											<dd>
												<div class="tags-default">
													<input type="text" value="" data-role="tagsinput" placeholder="add tags" data-discuss="invite" name="discussMember" required="required" />
												</div>
											</dd>
										</form>
										<button class="confirm" data-btn="creategroup" data-cgroup-type="1">创建</button>
									</dl>
								</dd>
							</dl>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="modal fade" id="firemsg" role="dialog" aria-labelledby="gridSystemModalLabel" data-drop="true">
			<!-- data-backdrop="static"  禁止点击背景关闭模态窗事件 -->
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						阅后即焚消息，关闭窗口此消息永久消失
					</div>
					<div class="modal-body">
						阅后即焚消息
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="emoji_div" id="eMoji">
			<div class="arrow"></div>
			<div class="popover-content" style="max-height: 120px; font-size:18px; overflow-y: auto">
			</div>
		</div>
		<div class="cam_div" id="camlist">
			<ul></ul>
		</div>
		
		<div id="takePic">
			<video width="300" height="300" id="picshow" autoplay="" controls src=""></video>
			<button id="snap"></button>
			<span id="snapCancle">取消</span>
		</div>
		<!--<div id="voiceWindow">-->
			<!--<canvas id="voicePic"></canvas>-->
			<!--<button data-voice="end">结束</button>-->
			<!--<button data-voice="cancel">取消</button>-->
		<!--</div>-->

		<div class="hide">
			<input type="file" name="sendPic" id="sendPic" value=""  multiple="multiple"/>
			<input type="file" name="sendFile" id="sendFile" value="" multiple="multiple" />
			<audio src="js/ring.mp3" id="im_ring"></audio>
			<audio src="" id="voiceMsg"></audio>
			<audio src="js/ringback.wav" id="call_ring" loop="-1"></audio>
			<pre style="display: none;" id="temporary"></pre>
		</div>

		<script src="js/justdo.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/sco.message.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/dist/bootstrap-tagsinput.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<!--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>-->

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/emoji.js" type="text/javascript" charset="utf-8"></script>
		
		<!--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->
		<script type="text/javascript">	

			$(function() {

               $("input[name='loginUser']").val(${username});
               alert("${username}");
               $(".phoneLogin").click();
                $("input[name='addFriend']").val(${proUserId});
                alert("${proUserId}");
               $(".addToChat").click();

				$('li:has(.contacts)').click(function(){
					if ($(document).width()< 740){
						$('.contact_list').css('visibility','visible');
					}

				})
				$('li:has(.communicate)').click(function(){
					if ($(document).width()< 740) {
						$('.chat_list').css('visibility', 'visible');
					}
				})
				$('li:has(.group)').click(function(){
					if ($(document).width()< 740) {
						$('.group_list').css('visibility', 'visible');
					}
				})
//				$('.chat_group>div').click(function(){
//					if ($(document).width()< 740) {
//						$(this).css('visibility', 'hidden');
//						$('.rlist').toggleClass('addrlist');
//					}
//				})
				$('.nav>li').click(function(){
					if ($(document).width()< 740) {
						$('.rlist').addClass('addrlist');
					}

				})
				$('.rlist').click(function(){
					if ($(document).width()< 740) {
						if ($('.rlist').hasClass('addrlist')) {
							$('.rlist').toggleClass('addrlist');
							$('.chat_group>div').css('visibility', 'hidden');
						}
					}
				});
				
				$(document).on("click",".chat_group",function(e){
					$(this).toggleClass('act');
				})
				
				$('.groupSet label').click(function() {
					var radioId = $(this).attr('name');
					if($(this).hasClass('checked')) {
						$(this).removeClass('checked');
						$('#' + radioId).attr('checked', false);
					} else {
						$(this).addClass('checked');
						$('#' + radioId).attr('checked', true);
					}
				});
				$(document).on('click', ".changeLogin", function(e) {
					switch(IM.login_type) {
						case 1: // 手机号登录
							$('.login2').show();
							$('.login1').hide();
							IM.login_type = 3;
							break;
						case 2: //  VOIP账号登陆
							$('.login1').show();
							$('.login2').hide();
							IM.login_type = 1;
							break;
						default:
							$('.login2').hide();
							$('.login1').show();
							IM.login_type = 1;
							break;
					};
				});
			});
			// $("#login").modal("toggle");

			function hide_show(show, hide_1, hide_2) {
				
				$('.chat_group').addClass("act");
				
				if($('.' + show).hasClass("hide_list")) {
					$('.' + show).removeClass("hide_list");
				}
				if(!$('.' + hide_1).hasClass("hide_list")) {
					$('.' + hide_1).addClass("hide_list");
				}
				if(!$('.' + hide_2).hasClass("hide_list")) {
					$('.' + hide_2).addClass("hide_list");
				}
			}

			var navList = $('.nav');
			navList.find("li").click(function(e) {
				var _index = $(this).index();
				navList.find(".active").removeClass("active");
				$(this).addClass("active");
				switch(_index) {
					case 0:
						hide_show("chat_list", "group_list", "contact_list ");
						break;
					case 1:
						hide_show("contact_list", "chat_list", "group_list ");
						break;
					case 2:
						hide_show("group_list", "chat_list", "contact_list ");
						break;
					default:
						break;
				};

			});

			function chatGroup() {
				$('.rlist').hide();
				$('.chat_group_window').show();
			};
			var m = $('.more_body_right dl').children('dd');
			$('.more_body_left').on("click", 'li', function() {
				$('.more_body_left').find('.active').removeClass('active');
				$(this).addClass('active');
				var i = $(this).index();
				m.hide();
				m.eq(i).show();
			});

			$('.closeMore').click(function(e) {
				$('#aboutMore').modal('hide');
			});
			$('.openMore').click(function(e) {
//				$('.about_more').show();
				$('#aboutMore').modal('show');
			});
			$('.searchWay').click(function(e) {
				$(this).siblings('.active').removeClass('active');
				$(this).addClass('active');
				var ins = $(this).index();
				$('#searchWayValue input').eq(ins).click();
				var s = $('#searchWayValue').serialize();
			});
//			IM.getHistoryFromServer();

            var st = RL_YTX.getCamera(function (e) {
                console.log(e);
            });
            console.log(st);
		</script>
	</body>

</html>
