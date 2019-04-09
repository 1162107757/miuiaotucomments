package top.hjie.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *	过滤html标签只留a标签，并且取出href中的链接
 */
public class HTMLSpirit {

	// 得到文章链接
	public static List<String> delHTMLTag(String htmlStr) {
		String regex = "<a.*?/a>";
		List<String> al = new ArrayList<String>();
		Pattern pt = Pattern.compile(regex);
        Matcher mt = pt.matcher(htmlStr);
        while(mt.find()){
        	String s3 = "href=\"(.*?)\"";
            Pattern pt3=Pattern.compile(s3);
            Matcher mt3=pt3.matcher(mt.group());
            while(mt3.find())
            {
                String u=mt3.group().replaceAll("href=|>","");
                al.add(u);
            }
        }
        for (int i = 0; i < al.size(); i++) {
			if(!al.get(i).contains("thread-")){
				al.remove(i);
				i--;
			}
		}
        for (int i = 0; i < al.size(); i++) {
        	if(!al.get(i).contains("http")){
				al.set(i, "http://www.miui.com/" + al.get(i).replace("\"", ""));
			}
		}
        Set<String> urlList = new HashSet<>(al);
        al = new ArrayList<>(urlList);
		return al; // 返回文本字符串
	}
	
	// 删除文章标签只留td标签，td标签内是回复内容
	public static List<String> delArticleHTMLTag(String htmlStr) {
		String regex = "<td class=\"t_f\" .*?/td>";
		List<String> al = new ArrayList<String>();
		Pattern pt = Pattern.compile(regex);
        Matcher mt = pt.matcher(htmlStr);
        while(mt.find())
        {
            String u=mt.group();
            al.add(u);
        }
        for (int i = 0; i < al.size(); i++) {
			if(!al.get(i).contains("postmessage") || al.get(i).contains("发表于") ||
					al.get(i).contains("设备") || al.get(i).contains("地点") ||
					al.get(i).contains("回复仅作者可见") || al.get(i).contains("下载附件") || 
					al.get(i).contains("本帖最后由") || al.get(i).contains("png") || 
					al.get(i).contains("jpg") || al.get(i).contains("gif")){
				al.remove(i);
				i--;
			}
		}
        for (int i = 0; i < al.size(); i++) {
        	String delTag = delTag(al.get(i));
        	al.set(i, delTag);
		}
		return al; // 返回文本字符串
	}
	
	// 剔除html标签
	static String delTag(String htmlStr){
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        return htmlStr.trim(); //返回文本字符串 
	}
	
	// 得到最大页面
	static Integer getPageMax(String articleId) throws Exception{
		List<String> al = new ArrayList<String>();
		// 得到页码的A标签
		{
			String url = "http://www.miui.com/thread-"+articleId+"-1-1.html";
			String resultHtml = HttpRequest.sendGet(url);
			String regex = "<a.*?/a>";
			Pattern pt = Pattern.compile(regex);
	        Matcher mt = pt.matcher(resultHtml);
	        while(mt.find())
	        {
	            String u=mt.group();
	            al.add(u);
	        }
	        for (int i = 0; i < al.size(); i++) {
				if(!al.get(i).contains("thread-" + articleId) || al.get(i).contains("&gt;") || al.get(i).contains("-1-1")){
					al.remove(i);
					i--;
				}
			}
		}
        
		// 得到最大页码
        if(al != null && al.size() != 0){
        	String regex = ">(.*?)<";
			Pattern pt = Pattern.compile(regex);
			List<String> pg = new ArrayList<String>();
			for (int i = 0; i < al.size(); i++) {
				Matcher mt = pt.matcher(al.get(i));
				while(mt.find())
				{
					String u=mt.group();
					pg.add(u);
				}
			}
			Set<Integer> pgs = new HashSet<>();
			for (int i = 0; i < pg.size(); i++) {
				pg.set(i, pg.get(i).replace(">... ", "").replace("<", "").replace(">", ""));
				pgs.add(Integer.parseInt(pg.get(i)));
			}
			
			Integer pageMax = Collections.max(pgs);
	        return pageMax;
        }
        // 无内容
        return null;
	}
	
	// 判断文章是否已评论过
	public static boolean isComment(String articleId, String bbsName) throws Exception{
		Integer pageMax = getPageMax(articleId);
		Integer page = 1;
		if(pageMax == null){	// 如果为空说明页数不超过十页
			// pageMax = 10;
			return false;
		}else if(pageMax < 4){
			return false;
		}else if(pageMax > 20){	// 大于而二十页的帖子页数太多，回复也不容易看出来，只取最后五页查看是否回复过，回复了就不再次评论
			page = pageMax - 5;	// 最大页-5
		}
		while(page <= pageMax){
			String url = "http://www.miui.com/thread-"+articleId+"-" + page + "-1.html";
			String result = HttpRequest.sendGet(url);
			int appearNumber = appearNumber(result, bbsName);
			if(appearNumber > 1){
				System.err.println("当前文章链接：" + url + "        第  " + page + "  页        存在你的评论，跳过评论！");
				return false;
			}
			page++;
		}
		return true;
	}
	/**
	 * 统计名称出现次数，大于1说名评论过
	 * @param srcText 原字符串文本
	 * @param findText	// 需要统计的字符
	 * @return
	 */
	public static int appearNumber(String srcText, String findText) {
	    int count = 0;
	    Pattern p = Pattern.compile(findText);
	    Matcher m = p.matcher(srcText);
	    while (m.find()) {
	        count++;
	    }
	    return count;
	}
	
}
