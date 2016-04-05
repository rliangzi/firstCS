
package com.liangzi.restful.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.cmos.core.util.JsonUtil;
import com.cmos.esbclient.bean.EsbInObject;
import com.cmos.esbclient.util.SHAUtil;
import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class HttpClientResutful {

    public static void main(String[] args)throws Exception {
        putClient();
    }
    
    public static String putClient() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
        HttpPut httpPut = new HttpPut("http://192.168.100.5:9099/workflow/flow/task/finish");
        
        EsbInObject esbInput = new EsbInObject();
        esbInput.getParams().put("systemId", "sys_1002");
        esbInput.getParams().put("loginStaffId", "admin");
        esbInput.getParams().put("taskId", "20160316180843-12-00009");
        JSONObject vars = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("dealGroup", "xxxxx");
        jsonObject2.put("oper", "review");
        vars.put("vars", jsonObject2);
        esbInput.setObject(vars);
        String jsonString = JsonUtil.convertObject2Json(esbInput);
        System.out.println(jsonString);
        httpPut.addHeader("content-type", "application/json");
        httpPut.addHeader("Accept", "application/json");
        httpPut.addHeader("ClientId","com.cmos.esb.user.ntbk");
        httpPut.addHeader("uri","http://192.168.100.5:9099/workflow/flow/task/finish");
        httpPut.addHeader("sn",getSn());
        httpPut.addHeader("secret",SHAUtil.shaEncode(jsonString));
        
        StringEntity entity = new StringEntity(jsonString);
        httpPut.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPut);
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        String response = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(response);
        
        return null;
    }

    public static String getClient() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
        // instead
        EsbInObject esbInput = new EsbInObject();
        esbInput.getParams().put("systemId", "sys_1002");
        esbInput.getParams().put("loginStaffId", "admin");
        //esbInput.getParams().put("processId", "jf_workflow");
        esbInput.getParams().put("processInstId", "20160315094048-12-01315");
        esbInput.getParams().put("resourceType", "image");
        JSONObject vars = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("dealGroup", "xxxxx");
        vars.put("vars", jsonObject2);
        esbInput.setObject(vars);
        String jsonString = JsonUtil.convertObject2Json(esbInput);
        String url = URLEncoder.encode(jsonString, "UTF-8").replaceAll("\\+", "%20");
        
        HttpGet httpGet = new HttpGet("http://192.168.100.5:9099/workflow/flow/process/resource/"+url);
        //HttpGet httpGet = new HttpGet("http://192.168.100.57:8088/workflow/process/resource/"+url);
        //HttpGet httpGet = new HttpGet("http://localhost:9901/workflow/process/resource/"+url);
        //httpGet.addHeader("content-type", "application/json");
        //httpGet.addHeader("Accept", "application/json");
        //esb使用
        httpGet.addHeader("ClientId","com.cmos.esb.user.ntbk");
        httpGet.addHeader("uri","http://192.168.100.5:9099/workflow/flow/process/resource/");
        httpGet.addHeader("sn",getSn());
        httpGet.addHeader("secret",SHAUtil.shaEncode(jsonString));


        // get with json
        HttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        //String response = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        //System.out.println(response);
        
        HttpEntity entity = httpResponse.getEntity();
        InputStream is = entity.getContent();  
        File file = new File("c:\\aa6.png");
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        is.close();
        
        /*Gson gson = new Gson();
        List<PersonDto> embossList = gson.fromJson(response, new TypeToken<List<PersonDto>>() {
        }.getType());
        if (embossList != null && embossList.size() > 0) {
            System.out.println("Name = " + embossList.get(0).getName());
            System.out.println("Country = " + embossList.get(0).getCountry());
            System.out.println("dddddddddd");
        }*/
        return null;
    }

    public static String postClient() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
        Gson gson = new Gson();
        // post data with json
        HttpPost httpPost = new HttpPost("http://192.168.100.57:8088/workflow/task/start");
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        // get with json
        //HttpResponse httpResponse = httpClient.execute(httpPost);
        //String response = EntityUtils.toString(httpResponse.getEntity());
/*        PersonDto dto = new PersonDto();
        dto.setName("http-client");
        dto.setHobby("json parser");
        dto.setCountry("US");
        dto.setDegree("本科");
        dto.setOccupy("tester");
        String jsonString = gson.toJson(dto);*/
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
        StringEntity entity = new StringEntity(jsonString);
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String response = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(response);

        return null;
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
