package com.donzzul.spring.recommendboard.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	public static void main(String[] args) {
		String target = "<p><img src=\"/summernote/imageView.dz?imgName=2d64cbae-4a04-472e-a7b9-dfe645e07a9b.png\" style=\"width: 998.006px;\"><img src=\"/summernote/imageView.dz?imgName=2765baa7-3552-44e6-9d96-90c577cc9a78.PNG\" style=\"width: 998.006px;\"><img src=\"/summernote/imageView.dz?imgName=fcb9416e-9784-4a9f-b01d-761c25e9e097.png\" style=\"width: 998.006px;\"><br></p>";
		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
		Matcher matcher = pattern.matcher(target);
		System.out.println("매처 : " + matcher);
		while(matcher.find()) {
			String path = matcher.group(1).substring(0, matcher.group(1).lastIndexOf("/") + 1);
			String savedName = matcher.group(1).substring(matcher.group(1).lastIndexOf("/") + 1);
			System.out.println("패스 : " + path);
			System.out.println(savedName);
			String realName =  matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
			System.out.println("리얼네임 : " + realName);
		}
	}
}
