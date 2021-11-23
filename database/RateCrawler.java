package database;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class RateCrawler {
	private static double rate=-1.0;
	
	private static void crawlRate() {
		URL url=null;
		BufferedReader br=null;
		
		try {
			url=new URL("https://finance.naver.com/marketindex/");
			
			try {
				br = new BufferedReader(new InputStreamReader(url.openStream(), "euc-kr"));  // 인코딩방식: "euc-kr"
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			String line;
			String rateLine="";
			try {
				while((line=br.readLine())!=null) {
					if(line.contains("selected=\"selected\"> 미국 달러 USD")) {
						rateLine=line;
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// rateLine == "<option value="1190.0" label="1" class="selectbox-default" selected="selected"> 미국 달러 USD</option>"
			//                              ^ 환율 1 USD == 1190.0 KRW 2021.11.20 기준
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<rateLine.length();++i) {
				if(Character.isDigit(rateLine.charAt(i)) || rateLine.charAt(i)=='.') {
					sb.append(rateLine.charAt(i));
				}
			}
			sb.deleteCharAt(sb.length()-1);	//마지막 원소 제거
			
			rate=Double.parseDouble(sb.toString());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double getRate() {
		if(RateCrawler.rate==-1.0) {
			crawlRate();
		}
		return rate;
	}
}