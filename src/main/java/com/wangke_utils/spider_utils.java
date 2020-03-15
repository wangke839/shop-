package com.wangke_utils;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class spider_utils {

	public static void spiderArticle(String url2,String path) throws IOException {
		Connection connect = Jsoup.connect(url2);
		Document document = connect.get();
//		System.out.println(document);
		Elements news = document.select(".list16");
		for (Element element : news) {
//			System.out.println(element);
			Elements HTM = element.select("a");
			for (Element htm : HTM) {
				System.out.println(htm);
//				System.err.println(split[1]);
//				把搜狐新闻首页
				String title= htm.attr("title");
//				String[] split = htm.toString().split(">");
//				String title= split[1];
				title = title.replace("|", "").replace("*", "").replace("\"", "").replace("?", "").replace("/", "")
						.replace("\\", "").replace(">", "").replace("<", "").replace(":", "");
				System.err.println(title);
				String url = htm.attr("href");
				if(!url.startsWith("http")) {
					url="http:"+url;
				}
				Connection connect2 = Jsoup.connect(url);
				Document document2 = connect2.get();
//				System.out.println(document2);
				Elements article = document2.select("article");
				for (Element element2 : article) {
//					System.out.println(element2);
					String text = element2.text();
//					System.out.println(text);
					File_Utils.writeFile(path+title+".txt", text, "utf-8");
				}
				}
			
		}
	}
}
