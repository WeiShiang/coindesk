package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class HttpUtil {
    private static Logger log = LogManager.getLogger(HttpUtil.class);
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * @param in
     * @param encode
     * @return
     * @throws IOException
     */
    public static String getStringFromStream(InputStream in, String encode) throws IOException {
//		log.debug("encode{}", encode);
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        String rtnResponse = "";
        try {
            reader = new BufferedReader(new InputStreamReader(in, encode));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            rtnResponse = buffer.toString();
           
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
            in.close();
        }
        return rtnResponse;
    }

    /**
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> headers) throws IOException {
        log.debug("url={}, header={}", url, headers);
        HttpGet httpPost = new HttpGet(url);

        if (StringUtils.isBlank(url)) {
            new IllegalArgumentException("input is empty or null");
        }
        if (!headers.isEmpty()) {
            headers.forEach((k, v) -> httpPost.addHeader(k, (String) headers.get(k)));
        }

        String responseBody = null;
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            responseBody = getStringFromStream(response.getEntity().getContent(), StandardCharsets.UTF_8.name());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return responseBody;
    }

}
