package rss;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * 
 * Title: Test.java<br>
 * Description: <br>
 * 
 */
public class Test {
    //http://www.163.com/rss/
    private static final String baidu = "http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss";
    private static final String wangyi = "http://news.163.com/special/00011K6L/rss_newstop.xml";

    private static Map<String, String> wangyiChannel = new HashMap<String, String>();
    static{
        wangyiChannel.put("新闻", "http://news.163.com/special/00011K6L/");
        wangyiChannel.put("体育", "http://sports.163.com/special/00051K7F/");
        wangyiChannel.put("娱乐", "http://ent.163.com/special/00031K7Q/");
        wangyiChannel.put("视频", "http://v.163.com/special/008544NC/");
        wangyiChannel.put("科技", "http://tech.163.com/special/000944OI/");
        wangyiChannel.put("财经", "http://money.163.com/special/00252EQ2/");
        wangyiChannel.put("汽车", "http://auto.163.com/special/00081K7D/");
        wangyiChannel.put("数码", "http://tech.163.com/digi/special/00161K7K/");
        wangyiChannel.put("手机", "http://tech.163.com/mobile/special/001144R8/");
        wangyiChannel.put("女性", "http://lady.163.com/special/00261R8C/");
        wangyiChannel.put("房产", "http://sh.house.163.com/special/000741DO/");
        wangyiChannel.put("游戏", "http://game.163.com/special/003144N4/");
        wangyiChannel.put("读书", "http://book.163.com/special/0092451H/");
        wangyiChannel.put("媒体", "http://media.163.com/special/00762B70/");
        wangyiChannel.put("公益", "http://gongyi.163.com/special/009344MB/");
        wangyiChannel.put("校园", "http://daxue.163.com/special/00913J5N/");
        wangyiChannel.put("旅游", "http://travel.163.com/special/00061K7R/");
        wangyiChannel.put("教育", "http://edu.163.com/special/002944N7/");
        wangyiChannel.put("论坛", "http://bbs.163.com/special/001544OQ/");
        wangyiChannel.put("博客", "http://news.163.com/special/000144P0/");
        wangyiChannel.put("营销", "http://mkt.163.com/special/009044MP/");
    }
    

    
    
    private static void testJson() throws IOException, URISyntaxException{
        File f = new File("/rss/");
        System.out.println(f);
        System.out.println(f.exists());
//        Enumeration<URL> urls = Test.class.getClassLoader().getResources("");
//        JSONArray jsonArray = new JSONArray();
//        while(urls.hasMoreElements()){
//            URL url = urls.nextElement();
//            InputStream in = url.openStream();
//            
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            IOUtils.copy(in, out);
//            String jsonStr = new String(out.toByteArray(), Charsets.UTF_8);
//            IOUtils.closeQuietly(out);
//            IOUtils.closeQuietly(in);
//            JSONObject json = JSONObject.fromObject(jsonStr);
//            jsonArray.add(json);
//        }
//        System.out.println(jsonArray);
//        InputStream in = Test.class.getClassLoader().getResourceAsStream("rss/rss_163.json");
    }

    public static void main(String[] args) throws Exception {
        testJson();
    }

}
