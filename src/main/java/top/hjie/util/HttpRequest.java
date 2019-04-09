package top.hjie.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;


public class HttpRequest {

	public static String sendGet(String url) throws Exception{
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
		URL realUrl = new URL(url);
        URLConnection con = realUrl.openConnection();
        con.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        con.setRequestProperty("connection", "Keep-Alive");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        con.setRequestProperty("Cache-Control", "max-age=0");
        // con.setRequestProperty("Cookie", "UM_distinctid=16956ce14085a2-01d7e9c1279a9-65567528-13c680-16956ce140952a; __utmz=230417408.1553339926.16.6.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; CNZZDATA5677709=cnzz_eid%3D1296319676-1551948259-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1553490389; __utmc=230417408; Hm_lvt_3c5ef0d4b3098aba138e8ff4e86f1329=1553579747,1553584324,1553586257,1553594353; CNZZDATA1270690907=659846289-1551948810-http%253A%252F%252Fwww.miui.com%252F%7C1553590584; CNZZDATA2441309=cnzz_eid%3D458194263-1551937762-null%26ntime%3D1553644177; CNZZDATA30049650=cnzz_eid%3D403142707-1551936867-null%26ntime%3D1553643278; __utma=230417408.206783033.1551939540.1553593789.1553648028.28; __utmt=1; MIUI_2132_saltkey=Ka2u0tWn; MIUI_2132_lastvisit=1553644432; MIUI_2132_home_diymode=1; MIUI_2132_sendmail=1; MIUI_2132_ulastactivity=29eayPN5lyBbTV0Givwa7xZ0uSEJSBGBGF8D8isHN3VmGG%2BLqFa5kEs; MIUI_2132_auth=2516JInSPxZYNndNNjFPtAO6Bq69iD20jFtv75m1k5e5iEbSnS5v0A; lastLoginTime=82f0ZsLebGOwmepk1BSKMX9DE1%2BVBt9hmb0A5iDt0b7xAhhNHIry; MIUI_2132_smile=3D1; MIUI_2132_nofavfid=1; MIUI_2132_lastact=1553648114%09forum.php%09viewthread; MIUI_2132_visitedfid=773D39; MIUI_2132_viewid=tid_22758997; Hm_lpvt_3c5ef0d4b3098aba138e8ff4e86f1329=1553648114; CNZZDATA5557939=cnzz_eid%3D1067749793-1551934196-null%26ntime%3D1553646031; __utmb=230417408.10.10.1553648028");
        con.setRequestProperty("Host", "www.miui.com");
        con.setRequestProperty("Referer", "http://www.miui.com/index.html");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3610.2 Safari/537.36");
        con.setDoOutput(true);
        con.setDoInput(true);
        out = new PrintWriter(con.getOutputStream());
        out.flush();
        in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
		return result;
	}
	
	public static String sendPost(String url, String param, String cookie) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
	        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
	        conn.setRequestProperty("Cache-Control", "max-age=0");
	        conn.setRequestProperty("Cookie", cookie);
	        conn.setRequestProperty("Host", "www.miui.com");
	        conn.setRequestProperty("Referer", "http://www.miui.com/index.html");
	        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
	        conn.setRequestProperty("user-agent",
	                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3610.2 Safari/537.36");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义 BufferedReader输入流来读取URL的响应
			Reader reader = null;
			if ("gzip".equals(conn.getContentEncoding())) {
				in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
}
