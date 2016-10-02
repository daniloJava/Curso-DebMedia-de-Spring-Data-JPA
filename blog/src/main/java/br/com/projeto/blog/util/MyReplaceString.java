package br.com.projeto.blog.util;

import java.text.Normalizer;

public class MyReplaceString {

	public static String formatarPermalink(String titulo){
		String link = titulo.trim();
		link = link.toLowerCase();
		link = Normalizer.normalize(link, Normalizer.Form.NFD);
		link = link.replaceAll("\\s", "_");

		link = link.replaceAll("\\W", "");
		
		link = link.replaceAll("\\_+", "_");
		
		return link;
	}
	
}
