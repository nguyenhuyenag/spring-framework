package com.reader;

import java.net.URL;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class FeedConsumer {

	public static void main(String[] args) throws Exception {
		String url = "https://stackjava.com/feed";
		// String url = "https://vnexpress.net/rss/tin-moi-nhat.rss";
		// String url = "https://www.24h.com.vn/upload/rss/trangchu24h.rss";
		URL feedUrl = new URL(url);
		try (XmlReader xmlReader = new XmlReader(feedUrl);) {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(xmlReader);
			for (SyndEntry entry : feed.getEntries()) {
				System.out.println("-----------------------------");
				System.out.println("Title : " + entry.getTitle());
				System.out.println("Author: " + entry.getAuthor());
				// System.out.println("Description : " + entry.getDescription());
				System.out.println("Link  : " + entry.getLink());
			}
		}
	}

}
