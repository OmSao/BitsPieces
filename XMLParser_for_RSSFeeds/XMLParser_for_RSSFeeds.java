package viscomp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser_for_RSSFeeds {
  public static void main(String[] args) throws Exception {
	  String urlString = httpswww.psychologytoday.comintlfrontfeed;
	  URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setReadTimeout(10000  milliseconds );
      conn.setConnectTimeout(15000  milliseconds );
      conn.setRequestMethod(GET);
      conn.setDoInput(true);

       Starts the query
      conn.connect();
      InputStream stream = conn.getInputStream();
      String result = getStringFromInputStream(stream);

		System.out.println(result);
		System.out.println(----------------------------);
		
		
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(result); result is a string but .parse takes File as argument
        Document doc = loadXMLFromString(result);
        
        doc.getDocumentElement().normalize();
        System.out.println(Root element  + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName(item);
        System.out.println(----------------------------+nList.getLength());
        int numberOfItems = nList.getLength();
        
        String[] titleArray = new String[numberOfItems];
        String[] descriptionArray = new String[numberOfItems];
        String[] pubDateArray = new String[numberOfItems];
        
        
        for (int temp = 0; temp  nList.getLength(); temp++) {
           Node nNode = nList.item(temp);
           System.out.println(nCurrent Element  + nNode.getNodeName());
           
           
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        	   
              Element eElement = (Element) nNode;
              
              titleArray[temp] = eElement.getElementsByTagName(title).item(0).getTextContent();
              
              System.out.println(Title  + eElement.getElementsByTagName(title).item(0).getTextContent());
              System.out.println(Link   + eElement.getElementsByTagName(link).item(0).getTextContent());
              System.out.println(Description   + eElement.getElementsByTagName(description).item(0).getTextContent());
              System.out.println(pubDate   + eElement.getElementsByTagName(pubDate).item(0).getTextContent());
              System.out.println(dccreator + eElement.getElementsByTagName(dccreator).item(0).getTextContent());
           }
         }
        System.out.println(titleArray[29]);
      
     
  }
  
convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
}
