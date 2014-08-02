package com.growme.torcat.common;

public class Constant {
	
	/**
	 * 크롤러가 사용하는 File DB 위치
	 */
	public static final String CRAWLER_STORAGE_DIRECTORY_PATH = "C:\\Users\\nhn\\Desktop\\crawler";
	
	/**
	 * 크롤링의 결과가 저장되는 디렉토리 위치
	 */
	public static final String CRAWLER_RESULT_DIRECTORY_PATH = "C:\\Users\\nhn\\Desktop\\crawler";
	
	/**
	 * 크롤링하는 쓰레드의 숫자
	 */
	public static final int NUMBER_OF_CRAWLERS = 7;
	
	/**
	 * 크롤링하는 depth로 무제한으로 두면 크롤링을 방어하는 사이트의 무한루프에서 탈출하지 못함.
	 */
	public static final int MAX_DEPTH_OF_CRAWLING = 10;
	
	/**
	 * 특정사이트에서 User AGENT가 없으면 크롤링을 방어함. 그래서 가짜 User Agent를 등록.
	 */
	public static final String FAKE_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0";

}
