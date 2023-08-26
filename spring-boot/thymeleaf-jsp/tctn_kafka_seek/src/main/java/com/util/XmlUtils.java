package com.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtils {

	public static String getTextOfTag(String xml, String parent, String tagName) {
		if (StringUtils.isEmpty(xml)) {
			return "";
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try (StringReader sr = new StringReader(xml)) {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(sr);
			Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(parent);
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nNode;
					Node tagNode = el.getElementsByTagName(tagName).item(0);
					if (tagNode != null) {
						return tagNode.getTextContent();
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static boolean isXMLValid(String string) {
		try (StringReader sr = new StringReader(string)) {
			SAXParserFactory.newInstance() //
					.newSAXParser() //
					.getXMLReader() //
					.parse(new InputSource(sr));
			return true;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
