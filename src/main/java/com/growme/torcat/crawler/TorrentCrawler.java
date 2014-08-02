package com.growme.torcat.crawler;

import java.io.File;
import java.util.regex.Pattern;

import com.growme.torcat.common.Cryptography;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import edu.uci.ics.crawler4j.util.IO;

public class TorrentCrawler extends WebCrawler {

    private static final Pattern filters = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	private static final Pattern imgPatterns = Pattern.compile(".*(\\.(torrent))$");

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

            if (imgPatterns.matcher(href).matches()) {
                    return true;
            }
            System.out.println("checkUrl : " + href);
            return true;
    }

    @Override
    public void visit(Page page) {
            String url = page.getWebURL().getURL();
            System.out.println("visit : " + url);
            // We are only interested in processing images
            if (!(page.getParseData() instanceof BinaryParseData)) {
                    return;
            }

            if (!imgPatterns.matcher(url).matches()) {
                    return;
            }

            // Not interested in very small images
            if (page.getContentData().length <= 0) {
                    return;
            }

            // get a unique name for storing this image
            String extension = url.substring(url.lastIndexOf("."));
            String hashedName = Cryptography.MD5(url) + extension;

            // store image
            IO.writeBytesToFile(page.getContentData(), storageFolder.getAbsolutePath() + "/" + hashedName);

            System.out.println("Stored: " + url);
    }
}
