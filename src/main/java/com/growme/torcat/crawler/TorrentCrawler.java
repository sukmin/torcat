package com.growme.torcat.crawler;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import edu.uci.ics.crawler4j.util.IO;

public class TorrentCrawler extends WebCrawler {
	private final static Logger logger = LoggerFactory.getLogger(TorrentCrawler.class);

	private static final Pattern filters = Pattern.compile(".*(\\.(" + //
			"css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	private static final Pattern torrentFilePatterns = Pattern.compile(".*(\\.(torrent))$");

	private static File storageFolder;

	public static void configure(String storageFolderName) {
		storageFolder = new File(storageFolderName);
		if (!storageFolder.exists()) {
			storageFolder.mkdirs();
		}
	}

	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();

		if (filters.matcher(href).matches()) {
			return false;
		}

		logger.debug("shouldVisit: {}", href);
		return true;
	}

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();

		if (!(page.getParseData() instanceof BinaryParseData)) {
			return;
		}

		if (page.getContentData().length <= 0) {
			return;
		}

		String extension = url.substring(url.lastIndexOf("."));
		if (extension.indexOf("?") > 0) {
			extension = extension.substring(0, extension.indexOf("?"));
		}

		String hashedName = DigestUtils.md5Hex(url) + extension;
		IO.writeBytesToFile(page.getContentData(), storageFolder.getAbsolutePath() + "/" + hashedName);

		logger.debug("Stored: {}", url);
	}
}
