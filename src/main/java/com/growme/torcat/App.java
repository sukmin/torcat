package com.growme.torcat;

import com.growme.torcat.common.Constant;
import com.growme.torcat.crawler.TorrentCrawler;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class App {

	public static void main(String[] args) throws Exception {

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(Constant.CRAWLER_STORAGE_DIRECTORY_PATH);

		config.setIncludeBinaryContentInCrawling(true);
		config.setMaxDepthOfCrawling(Constant.MAX_DEPTH_OF_CRAWLING);
		config.setUserAgentString(Constant.FAKE_USER_AGENT);

		String[] crawlDomains = new String[] { "http://www.naver.com" };

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		for (String domain : crawlDomains) {
			controller.addSeed(domain);
		}

		TorrentCrawler.configure(Constant.CRAWLER_RESULT_DIRECTORY_PATH);

		controller.start(TorrentCrawler.class, Constant.NUMBER_OF_CRAWLERS);

	}

}
