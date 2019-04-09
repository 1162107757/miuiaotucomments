package top.hjie.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import top.hjie.mapper.AutoTaskUserMapper;
import top.hjie.pojo.AutoTaskUser;
import top.hjie.util.HTMLSpirit;
import top.hjie.util.HttpRequest;

@Component
public class AutoComment1 extends Thread{

	
	public void startTask(AutoTaskUserMapper autoTaskUserMapper) throws Exception{
		// 当前任务正在执行
		long startTime = System.currentTimeMillis();
		AutoTaskUser autoTaskUser = autoTaskUserMapper.getAutoTaskUserUnlocck();
		if(autoTaskUser != null){
			autoTaskUserMapper.updateAutoTaskUserLocck(autoTaskUser.getAtuId());
			// 第一百页开始获取，防止第一页最后回复全是本人名字被发现
			Integer page = 1;
			while (true) {
				String url = "http://www.miui.com/forum.php?mod=forumdisplayall&page=" + page;
				String sendGet = HttpRequest.sendGet(url);
				List<String> taga = HTMLSpirit.delHTMLTag(sendGet);
				for (int i = 0; i < taga.size(); i++) {
					String articleId = null;
					taga.set(i, taga.get(i).replace("\"", ""));
					articleId = taga.get(i).substring(taga.get(i).indexOf("-") + 1);
					articleId = articleId.substring(0, articleId.indexOf("-"));
					
					boolean isComment = HTMLSpirit.isComment(articleId,autoTaskUser.getBbsName());
					if(isComment){
						String sendMessageUrl = "http://www.miui.com/forum.php?mod=post&action=reply&fid=773&tid="+articleId+"&extra=page%3D1&replysubmit=yes&infloat=yes&handlekey=fastpost&inajax=1";
						List<String> messages = HTMLSpirit.delArticleHTMLTag(HttpRequest.sendGet("http://www.miui.com/thread-" + articleId + "-1-1.html"));
						boolean comment = comment(articleId, messages, sendMessageUrl,autoTaskUser);
						if(!comment){
							autoTaskUserMapper.updateAutoTaskUserTaskStatus(autoTaskUser.getAtuId(),4);
							return;
						}
					}
				}
				// 页数+
				page++;
				long endTime = System.currentTimeMillis();
				
				// 四个小时任务
				if((endTime - startTime) / 1000 > 14400){
					autoTaskUserMapper.updateAutoTaskUserUnLocck(autoTaskUser.getAtuId());
					return;
				}
				
			}
		}
	}
	
	public static boolean comment(String articleId,List<String> messages,String sendMessageUrl, AutoTaskUser autoTaskUser) throws Exception{
		if(messages != null && messages.size() > 1){
			int index = new Random().nextInt(messages.size()-1) + 1;
			String param = "&message=" + messages.get(index) + "&posttime=" + System.currentTimeMillis() + "&formhash=" + autoTaskUser.getFormHash() + "&usesig=1&subject";
			String sendPost = HttpRequest.sendPost(sendMessageUrl, param,autoTaskUser.getCookie());
			if(sendPost.contains("您尚未登录")){
				return false;
			}
			if(sendPost.contains("您的请求来路不正确或表单验证串不符")){
				return false;
			}
			// 输出回复后的内容
			if(sendPost.contains("抱歉，您两次发表间隔少于 15 秒")){
				sleep(1000);
				comment(articleId, messages, sendMessageUrl,autoTaskUser);
			}
			if(sendPost.contains("抱歉，您的帖子小于 15 个字符的限制")){
				messages.remove(index);
				comment(articleId, messages, sendMessageUrl,autoTaskUser);
			}
			if(sendPost.contains("成功")){
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t发布成功！文章链接: http://www.miui.com/thread-" + articleId + "-1-1.html");
			}
		}
		return true;
	}


}
