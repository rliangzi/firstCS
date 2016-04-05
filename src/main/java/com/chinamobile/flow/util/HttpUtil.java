package com.chinamobile.flow.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpUtil {

    //创建HttpClientBuilder  
    private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
    //HttpClient  
    private static CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
    /**
     * 根据url请求数据�?
     *
     * @param url
     * @return
     */
    public static String getResultHttp(String url) {
        String responseString = "";

        HttpGet httpGet = new HttpGet(url);
        HttpResponse resp;
        try {
            resp = closeableHttpClient.execute(httpGet);
            HttpEntity entity = resp.getEntity();
            if (entity != null) {
                responseString = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("responseString:" + responseString);
        return responseString;
    }

    /**
     * 根据url请求数据�? 指定字符编码
     *
     * @param url
     * @return
     * @throws IOException 
     */
    public static String getResultHttp(String url, String encode) throws IOException {
        String responseString = "";

        HttpGet httpGet = new HttpGet(url);

        RequestConfig requestConfig = RequestConfig.custom()  
                .setConnectionRequestTimeout(50).setConnectTimeout(50)  
                .setSocketTimeout(50).build();  
        httpGet.setConfig(requestConfig);  
        CloseableHttpResponse  resp=null;
        try {
            resp = closeableHttpClient.execute(httpGet);
            
            HttpEntity entity = resp.getEntity();
            if (entity != null) {
                responseString = EntityUtils.toString(entity, encode);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            resp.close();
            closeableHttpClient.close();
        }
        System.out.println("responseString:" + responseString);
        return responseString;
    }

    public static List<String> getPicSrcString(String str) {
        List<String> list = new ArrayList<String>();
        str = str.toLowerCase();
        String yangben = "<img";
        int fromIndex = 0;
        while (str.indexOf(yangben, fromIndex) > 0) {
            int imgStartIndex = str.indexOf(yangben, fromIndex);
            int srcStartIndex = str.indexOf("src=\"", imgStartIndex) + 5;
            if (srcStartIndex == 4) { // 如果没有找到 img�?src 则代表，后边没有图片
                break;
            }
            int srcEndIndex = str.indexOf("\"", srcStartIndex);
            String imgSrc = str.substring(srcStartIndex, srcEndIndex);
            fromIndex = str.indexOf(">", srcEndIndex);
            if (fromIndex == -1) {
                break;
            }
            list.add(imgSrc);
        }

        return list;
    }

    /**
     * 把url对应的文件下载到指定的目录，并对文件进行缩小处理以�?应手机显�?
     *
     * @param url 远程文件地址
     * @param savePath 保存文件的路径，包含文件�?
     * @return true 成功 / false 失败
     * @throws IOException
     */
    public static boolean downloadAndMinifyPic(URL url, String savePath)
            throws IOException {

        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        } else {
            return true;
        }

        URLConnection urlCon = url.openConnection();
        urlCon.setConnectTimeout(3000);
        urlCon.setReadTimeout(3000);
        BufferedImage input = null;
        try {
            urlCon.connect();
            input = ImageIO.read(urlCon.getInputStream());
        } catch (java.net.SocketTimeoutException e) {
            e.printStackTrace();
            return false;
        }
        float width = input.getWidth();
        float height = input.getHeight();
        float wh = width / height;
        if (width > 280) {
            width = 280;
            height = width / wh;
        }
        BufferedImage inputbig = new BufferedImage((int) width, (int) height,
                BufferedImage.TYPE_INT_BGR);
        Graphics2D g = (Graphics2D) inputbig.getGraphics();
        g.drawImage(input, 0, 0, (int) width, (int) height, null);
        g.dispose();
        inputbig.flush();

        ImageIO.write(inputbig, "jpg", saveFile);
        return true;
    }

    /**
     * 找出新闻中的图片，作为首页
     *
     * @param news
     * @throws Exception
     * @throws IOException
     */
    // public static void replaseContentToImg(List<TBulletin> news) throws
    // IOException{
    // // String webPath =
    // HttpUtil.class.getClassLoader().getResource("").getPath();
    // // File ff = new File(webPath);
    // // String appPath = ff.getParentFile().getParentFile().getPath();
    // String picPath = Conv.ZSXYWAPURL+File.separator;
    // Iterator it=news.iterator();
    // while(it.hasNext()){
    // TBulletin n=(TBulletin)it.next();
    // List<String> urls=getPicSrcString(n.getContent());
    // if(urls.size()>0){
    // String path=urls.get(0);
    // if(remoteFileExists(picPath+path)){
    // n.setIsdownimg(path);
    // }else{
    // it.remove();
    // }
    // }else{
    // it.remove();
    // }
    // }
    // }
    public static void getResultHttp2(String url, String encode)
            throws Exception {
        /*
         * HttpClient client = new HttpClient(); PostMethod post = new
         * PostMethod(url); NameValuePair name = new NameValuePair("date",
         * "2014-08-08"); post.setRequestBody(new NameValuePair[] { name }); int
         * status = client.executeMethod(post);
         * System.out.println(post.getResponseBodyAsString());
         * post.releaseConnection();
         */

        /*
         * DefaultHttpClient httpclient = new DefaultHttpClient(); HttpPost
         * httpPost = new HttpPost(url); List<NameValuePair> nvps = new
         * ArrayList<NameValuePair>(); nvps.add(new BasicNameValuePair("date",
         * "2014-08-09")); httpPost.setEntity(new UrlEncodedFormEntity(nvps,
         * encode)); HttpResponse httpResponse = httpclient.execute(httpPost);
         * HttpEntity entity = httpResponse.getEntity(); String responseString =
         * EntityUtils.toString(entity, encode);
         * System.out.println("responseString:" + responseString);
         * httpclient.getConnectionManager().shutdown();
         */
        
        RequestConfig requestConfig = RequestConfig.custom()  
                .setConnectionRequestTimeout(50).setConnectTimeout(50)  
                .setSocketTimeout(50).build(); 
        
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("date", "2014-08-09"));
        // 向服务器写json
        JSONObject json = new JSONObject();
        json.put("date", "2014-08-09");
        StringEntity se = new StringEntity(json.toString(), encode);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        //post.setEntity(new UrlEncodedFormEntity(param, encode));
        HttpResponse httpResponse = closeableHttpClient.execute(post);
        String responseString = EntityUtils.toString(httpResponse.getEntity(), encode);
        System.out.println("responseString:" + responseString);
        closeableHttpClient.close();

    }

    public static void getResultHttp3(String url, String encode)
            throws Exception {
        /*
         * HttpClient client = new HttpClient(); PostMethod post = new
         * PostMethod(url); NameValuePair name = new NameValuePair("date",
         * "2014-08-08"); post.setRequestBody(new NameValuePair[] { name }); int
         * status = client.executeMethod(post);
         * System.out.println(post.getResponseBodyAsString());
         * post.releaseConnection();
         */

        /*
         * DefaultHttpClient httpclient = new DefaultHttpClient(); HttpPost
         * httpPost = new HttpPost(url); List<NameValuePair> nvps = new
         * ArrayList<NameValuePair>(); nvps.add(new BasicNameValuePair("date",
         * "2014-08-09")); httpPost.setEntity(new UrlEncodedFormEntity(nvps,
         * encode)); HttpResponse httpResponse = httpclient.execute(httpPost);
         * HttpEntity entity = httpResponse.getEntity(); String responseString =
         * EntityUtils.toString(entity, encode);
         * System.out.println("responseString:" + responseString);
         * httpclient.getConnectionManager().shutdown();
         */
        RequestConfig requestConfig = RequestConfig.custom()  
                .setConnectionRequestTimeout(50).setConnectTimeout(50)  
                .setSocketTimeout(50).build(); 
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        // 向服务器写json
        //JSONObject json = new JSONObject();
        //json.put("date", "2014-08-09");
        //JSONArray jsonArray = new JSONArray();
        String data = "[{\"datas\":[\"2014-08-09-040\",\"2014-08-09\",\"周六040\",\"荷兰乙级联赛\",\"埃因霍温青年队\",\"阿基里斯\",\"-1\",\"2\",\"0\",\"2\",\"0\",\"2014-08-09 22:25:58\",\"1.41\",\"4.60\",\"5.10\",\"已完成\"]},{\"datas\":[\"2014-08-09-039\",\"2014-08-09\",\"周六039\",\"瑞典超级联赛\",\"米亚尔比\",\"厄勒布鲁\",\"-1\",\"0\",\"0\",\"0\",\"1\",\"2014-08-09 21:57:21\",\"2.07\",\"3.30\",\"2.97\",\"已完成\"]}]";
        JSONArray jsonArray = JSONArray.fromObject(data);
        StringEntity se = new StringEntity(jsonArray.toString(), encode);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        HttpResponse httpResponse = closeableHttpClient.execute(post);
        String responseString = EntityUtils.toString(httpResponse.getEntity(), encode);
        System.out.println("responseString:" + responseString);
        closeableHttpClient.close();

    }

    public static void main(String[] args) throws Exception {
        // System.out.println(getResultHttp("http://soa2013.zzwater.com/diaodu/sc.ashx?factoryname=sy","utf-8"));
        // String info=
        // getResultHttp("http://192.168.1.105:8080/zzwaterserver/app/Article_queryPicId.action","utf-8");
        // getResultHttp2("http://www.football-888.com/readCsv/importData.do","utf-8");
//        getResultHttp2(
//                "http://localhost:8080/football/readCsv/isExistsData.do",
//                "utf-8");
        getResultHttp(
                "http://localhost:8088/firstCS/message/getMessage?type=complainQuery",
                "utf-8");
        /*getResultHttp3(
         "http://192.168.1.114:8080/football/readCsv/importData.do",
         "utf-8");*/
        // System.out.println(info);
		/*
         * JSONObject obj = new JSONObject(info); JSONArray array =
         * obj.getJSONArray("data"); for(int i = 0;i < array.length();i++){
         * JSONObject child = array.getJSONObject(i); System.out.println(child);
         * }
         */
    }
}
