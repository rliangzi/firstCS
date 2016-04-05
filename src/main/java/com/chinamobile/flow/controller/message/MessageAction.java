
package com.chinamobile.flow.controller.message;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/message")
public class MessageAction {
    // 日志记录
    protected Logger logger = LogManager.getLogger(MessageAction.class);
    
    /**
     * @Description: 投诉查询
     * @param request
     * @param response
     * @throws Exception
     * @ReturnType void
     * @author: 任铭亮
     * @Created 2016 2016年2月26日 下午12:08:48
     */
    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    @ResponseBody
    public void complainQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("complainQuery");
        InputStream is = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));  
        StringBuffer sb = new StringBuffer();  
        String str = null;
        while ((str = reader.readLine()) != null){  
            sb.append(str).append("\n");  
        }  
        System.out.println("***"+sb.toString());
        
        String type = request.getParameter("type");
        String result = ""; 
        if(StringUtils.isNotBlank(type)){
            result = getContent(type+".xml");
        }
        logger.info("complainQuery result is { returnMsg={}}",result);
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().print(result);
    }
    
    private String getContent(String type) {
        String FileName = MessageAction.class.getClassLoader().getResource("/").getPath()+type;
        File myFile = new File(FileName);
        String str = null;
        if (!myFile.exists()) {
            System.err.println("Can't Find " + FileName);
        }
        StringBuffer sbb = new StringBuffer();
        try {
            BufferedReader in = new BufferedReader(new FileReader(myFile));
            while ((str = in.readLine()) != null) {
                sbb.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return sbb.toString();
    }

    /**
     * @Description: 接口地址
     * @return
     * @throws IllegalArgumentException
     * @throws Exception
     * @ReturnType ModelAndView
     * @author: 任铭亮
     * @Created 2016 2016年2月26日 下午2:27:46
     */
    @RequestMapping(value = "/address")
    public ModelAndView address() throws IllegalArgumentException, Exception {
        logger.debug("address");
        ModelAndView mav = new ModelAndView("address/address");
        return mav;
    }
}
