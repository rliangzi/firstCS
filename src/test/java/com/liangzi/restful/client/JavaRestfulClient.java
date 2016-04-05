
package com.liangzi.restful.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cmos.core.util.JsonUtil;
import com.cmos.esbclient.bean.EsbInObject;
import com.cmos.esbclient.util.SHAUtil;

import net.sf.json.JSONObject;

public class JavaRestfulClient {
    private static final String targetURL = "http://192.168.100.57:8088/workflow/task/start";

    public static void main(String[] args) throws Exception{
        getClient();
        //postClient();
        //putClient();
    }
    
    public static void putClient() throws Exception{

        try {
            EsbInObject esbInput = new EsbInObject();
            esbInput.getParams().put("systemId", "sys_1002");
            esbInput.getParams().put("loginStaffId", "admin");
            esbInput.getParams().put("taskId", "20160316181524-12-00017");
            JSONObject vars = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("dealGroup", "xxxxx");
            jsonObject2.put("oper", "oneLevelDeal");
            vars.put("vars", jsonObject2);
            esbInput.setObject(vars);
            String jsonString = JsonUtil.convertObject2Json(esbInput);
            String targetURL = "http://192.168.100.5:9099/workflow/flow/task/finish";
            //targetURL = "http://192.168.100.5:9099/workflow/flow/task/finish";
            //String input = "{\"params\":{\"loginStaffId\":\"admin\",\"processId\":\"userApply\",\"systemId\":\"sys_1002\"}}";
            //String url = targetURL+URLEncoder.encode(jsonString, "UTF-8").replaceAll("\\+", "%20");
            URL restServiceURL = new URL(targetURL);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("PUT");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("content-type", "application/json");
            //esb使用
            httpConnection.setRequestProperty("ClientId", "com.cmos.esb.user.ntbk");
            httpConnection.setRequestProperty("uri", "http://192.168.100.5:9099/workflow/flow/task/finish");
            httpConnection.setRequestProperty("sn", getSn());
            httpConnection.setRequestProperty("secret",SHAUtil.shaEncode(jsonString));
            httpConnection.connect();
            
            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(jsonString.getBytes());
            outputStream.flush();
            System.out.println(httpConnection.getResponseCode());
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException(
                        "HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(
                    new InputStreamReader((httpConnection.getInputStream())));
            String output;
            System.out.println("Output from Server:  \n");
            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
            }
            httpConnection.disconnect();
           
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getClient() throws Exception{

        try {
            EsbInObject esbInput = new EsbInObject();
            esbInput.getParams().put("systemId", "sys_1002");
            esbInput.getParams().put("loginStaffId", "admin");
            //esbInput.getParams().put("processId", "jf_workflow");
            esbInput.getParams().put("resourceType", "image");
            esbInput.getParams().put("processInstId", "20160315094048-12-01315");
            JSONObject vars = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("dealGroup", "xxxxx");
            vars.put("vars", jsonObject2);
            esbInput.setObject(vars);
            String jsonString = JsonUtil.convertObject2Json(esbInput);
            String targetURL = "http://192.168.100.57:8088/workflow/process/resource/";
            targetURL = "http://192.168.100.5:9099/workflow/flow/process/resource/";
            //String input = "{\"params\":{\"loginStaffId\":\"admin\",\"processId\":\"userApply\",\"systemId\":\"sys_1002\"}}";
            String url = targetURL+URLEncoder.encode(jsonString, "UTF-8").replaceAll("\\+", "%20");
            URL restServiceURL = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            //httpConnection.setRequestProperty("Accept", "application/json");
            //httpConnection.setRequestProperty("Accept", MediaType.APPLICATION_OCTET_STREAM_VALUE);
            //esb使用
            httpConnection.setRequestProperty("ClientId", "com.cmos.esb.user.ntbk");
            httpConnection.setRequestProperty("uri", "http://192.168.100.5:9099/workflow/flow/process/resource/");
            httpConnection.setRequestProperty("sn", getSn());
            httpConnection.setRequestProperty("secret",SHAUtil.shaEncode(jsonString));
            httpConnection.connect();
            System.out.println(httpConnection.getResponseCode());
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException(
                        "HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());
            }
//            BufferedReader responseBuffer = new BufferedReader(
//                    new InputStreamReader((httpConnection.getInputStream())));
//            String output;
//            System.out.println("Output from Server:  \n");
//            while ((output = responseBuffer.readLine()) != null) {
//                System.out.println(output);
//            }
//            httpConnection.disconnect();
            InputStream is = httpConnection.getInputStream();
            File file = new File("c:\\aa8.png");
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            is.close();
            httpConnection.disconnect();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void postClient() {
        try {
            URL targetUrl = new URL(targetURL);
            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            EsbInObject esbInput = new EsbInObject();
            esbInput.getParams().put("systemId", "sys_1002");
            esbInput.getParams().put("loginStaffId", "admin");
            esbInput.getParams().put("processId", "jf_workflow");
            JSONObject vars = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("dealGroup", "xxxxx");
            vars.put("vars", jsonObject2);
            esbInput.setObject(vars);
            String jsonString = JsonUtil.convertObject2Json(esbInput);
            //String input = "{\"id\":1,\"firstName\":\"Liam\",\"age\":22,\"lastName\":\"Marco\"}";
            //String input = "{\"params\":{\"loginStaffId\":\"admin\",\"processId\":\"userApply\",\"systemId\":\"sys_1002\"}}";
            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(jsonString.getBytes());
            outputStream.flush();
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(
                    new InputStreamReader((httpConnection.getInputStream())));
            String output;
            System.out.println("Output from Server:\n");
            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
            }
            httpConnection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getSn() {
        String clientId = "com.cmos.esb.user.ntbk";
        String prefix = clientId.substring(clientId.lastIndexOf(".") + 1);
        String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String random = String.valueOf(new Random().nextInt(899999) + 100000);
        String sn = prefix + date + random;
        return sn;
    }

}
