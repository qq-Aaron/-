package cn.anan.chat_all.sysmanager.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * Robot
 * 
 *Simple To Introduction
 *项目名称: [Robot]
 * 包:  [cn.anan.chat_all.sysmanager.action]
 *类名称: [Robot]
 *类描述: []
 *创建人: [Aaron]
 *创建时间: [上午9:10:16]
 *
 */
@WebServlet("/robot")
public class Robot extends HttpServlet {

	/*public static final String APIKEY="b256a26b7fa44d26ad21cafeafa198ed";
	public static final String URL="http://www.tuling123.com/openapi/api?key=";*/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		String text=req.getParameter("text");
		String result = query(text);
		//result=new String(result.getBytes("8859_1"), "utf8");
        resp.getWriter().println(result);
	}
	/*public static String getResult(String text){
        String INFO = "";
        //用来存数据
        StringBuilder sb = new StringBuilder();

        try {
            //解码器
            INFO = URLEncoder.encode(text, "UTF-8");
            String getUrl = URL+APIKEY+"&info="+INFO;//字符串拼接成url地址
            URL queryUrl = new URL(getUrl);
            URLConnection conn = queryUrl.openConnection();
            BufferedReader br =new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String temp="";
            while ((temp=br.readLine())!=null) {
                sb.append(temp);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }*/
	
	public static String query(String text){
		String ACTION = "http://www.tuling123.com/openapi/api?key=b256a26b7fa44d26ad21cafeafa198ed&info="+text;
		InputStream is=null;
		InputStreamReader ir=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();
		try {
			URL url = new URL(ACTION);
			URLConnection connection=url.openConnection();
			is=connection.getInputStream();
			ir=new InputStreamReader(is);
			br=new BufferedReader(ir);
			String line="";
			while((line=br.readLine()) !=null){
				sb.append(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
			if(br !=null){
				br.close();
			}
			if(ir !=null){
				ir.close();
			}
			if(is !=null){
				is.close();
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		String query = query("体育新闻");
		System.out.println(query);
	}
}
