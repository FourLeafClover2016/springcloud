package com.hwx.myauth.test;

import com.alibaba.fastjson.JSONObject;
import com.hwx.myauth.config.RestemplateConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public class MyTest {
    public static void main(String[] args) throws Exception{
        //System.out.println(getCode("clientapp","authorization_code", "http://localhost:6677/callback"));
       // System.out.println(getToken("test","password", "123456",null));
       // System.out.println(getToken("test","client_credentials", "123456",null));
       // System.out.println(getToken("test","refresh_token", "123456","468c987b-0879-47f3-aa3a-7c46fee44a89"));
        System.out.println(login());
    }
    public static ResponseEntity login() throws Exception{
        ResponseEntity<String> loginResult = null;
        URI uri = new URIBuilder("http://127.0.0.1:6677/myauth/oauth/authorize")
                .addParameter("client_id", "clientapp")
                .addParameter("response_type", "code")
                .build();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).build();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 从从定向地址中取得code
        if (response.getStatusLine().getStatusCode() == HttpStatus.FOUND.value()) {
            String setCookie = response.getFirstHeader("Set-Cookie").getValue();
            String cookie = setCookie.substring(0, setCookie.indexOf(";"));

            LinkedMultiValueMap<String, String> loginParams = new LinkedMultiValueMap<>();
            loginParams.add("username", "admin");
            loginParams.add("password", "admin1234");

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Cookie", cookie);
            HttpEntity<LinkedMultiValueMap<String, String>> loginEntity = new HttpEntity<>(loginParams, httpHeaders);

            String loginUrl = "http://127.0.0.1:6677/myauth/login";

            RestTemplate restTemplate = new RestemplateConfig().restTemplate();
            loginResult = restTemplate.postForEntity(loginUrl, loginEntity, String.class);

        }

        return loginResult;
    }

    public static String getCode(String client_id, String response_type, String redirect_uri) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 将用户名和密码放入header中
        String plainClientCredentials = "admin:admin1234";
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
        String url = "http://127.0.0.1:6677/myauth/oauth/authorize?client_id=" + client_id + "&"
                + "response_type=" + response_type + "&" + "redirect_uri=" + redirect_uri;

        RequestConfig config = RequestConfig.custom().setRedirectsEnabled(false).setConnectionRequestTimeout(5000)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Basic " + base64ClientCredentials);
        httpGet.setConfig(config);
        String result = "";
        try {

            HttpResponse response = httpClient.execute(httpGet);
            // 从从定向地址中取得code
            if (response.getStatusLine().getStatusCode() == 302) {
                Header header = response.getFirstHeader("Location");
                String location = header.getValue();
                String code = location.substring(location.indexOf("=") + 1, location.length());
                return code;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error";
    }
    public static JSONObject getToken(String client_id, String grant_type, String client_secret, String refresh_token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = url = "http://127.0.0.1:6677/myauth/oauth/token?client_id=" + client_id + "&grant_type="
                + grant_type + "&client_secret=" + client_secret;
        if ("password".equals(grant_type)) {
            url = url + "&username=admin&password=admin1234";
        } else if ("client_credentials".equals(grant_type)) {

        } else if ("refresh_token".equals(grant_type)) {
            url = url + "&refresh_token=" + refresh_token;
        }

        HttpPost httpPost = new HttpPost(url);
       // httpPost.setHeader("Authorization", "Basic " + );
        HttpResponse response = null;
        JSONObject jsonObject = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String result = "";
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
                // 解析token的json数据
                jsonObject = JSONObject.parseObject(result);
                /*AccessToken accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setToken_type(jsonObject.getString("token_type"));
                accessToken.setRefresh_token(jsonObject.getString("refresh_token"));
                accessToken.setExpires_in(jsonObject.getString("expires_in"));
                accessToken.setScope(jsonObject.getString("scope"));*/
                return jsonObject;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
