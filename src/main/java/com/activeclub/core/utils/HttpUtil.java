package com.activeclub.core.utils;

import com.activeclub.core.bean.BaseException;
import com.activeclub.core.bean.dto.RequestDto;
import com.activeclub.core.constants.ErrorCode;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author 59456
 * @Date 2021/9/19
 * @Descrip
 * @Version 1.0
 */
@Component
public class HttpUtil {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 直接请求简化
     * @param url
     * @param params
     * @return
     */
    public String doGet(String url,Map<String,String > params){
        RequestDto requestDto = new RequestDto();
        requestDto.setUrl(url);
        requestDto.setParams(params);
        return doGet( requestDto,  null);
    }

    /**
     * get请求
     *
     * @param requestDto      输入参数
     * @param servletResponse 反馈响应（用于文件下载）
     * @return 文本输出
     */
    public String doGet(RequestDto requestDto, HttpServletResponse servletResponse) {
        String url = requestDto.getUrl();
        String jsonStr = requestDto.getJsonStr();
        Map<String,String> params = requestDto.getParams();
        Map<String,String> headers = requestDto.getHeaders();
        Boolean hasServletResponse = requestDto.getHasServletResponse();

        // 中间变量
        String resultString = "";
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        InputStream is = null;
        OutputStream os = null;

        try {

            // 1 判断url的类型
            if (url.startsWith("https")) {
                httpclient = getIgnoeSslClient();
            } else {
                httpclient = HttpClients.createDefault();
            }

            // 2 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                params.forEach(builder::addParameter);
            }
            URI uri = builder.build();

            // 3 创建http GET请求 并且 设置token
            HttpGet httpGet = new HttpGet(uri);
            if (headers != null) {
                headers.forEach(httpGet::setHeader);
            }

            // 4 执行请求
            response = httpclient.execute(httpGet);

            // 5 直接进行转换
            // 5.1 有文件下载
            if (hasServletResponse) {
                for (Header header : response.getAllHeaders()) {
                    servletResponse.addHeader(header.getName(), header.getValue());
                }

                byte[] respStr = EntityUtils.toByteArray(response.getEntity());
                is = new ByteArrayInputStream(respStr);
                os = servletResponse.getOutputStream();
                byte[] buffer = new byte[1024 * 1024];
                int count;
                while ((count = is.read(buffer)) != -1) {
                    os.write(buffer, 0, count);
                }
                os.flush();

            } else {// 5.2 没有有文件下载
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }

            if (url.startsWith("https")) {
                EntityUtils.consume(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭服务
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("CloseableHttpResponse close happend a error! ", e);
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error("httpclient close happend a error! ", e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("InputStream close happend a error! ", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("OutputStream close happend a error! ", e);
                }
            }
        }
        return resultString;
    }

    /**
     * 直接请求
     * @param url
     * @param jsonString
     * @return
     */
    public String doPostJson(String url,String jsonString){
        RequestDto requestDto = new RequestDto();
        requestDto.setUrl(url);
        requestDto.setJsonStr(jsonString);
        return doPost( requestDto, null,null);
    }

    /**
     * post请求
     *
     * @param requestDto      输入参数
     * @param multipartFile   上传文件
     * @param servletResponse 反馈响应（用于文件下载）
     * @return 文本输出
     */
    public String doPost(RequestDto requestDto, MultipartFile multipartFile, HttpServletResponse servletResponse) {
        String url = requestDto.getUrl();
        String jsonStr = requestDto.getJsonStr();
        Map<String,String> params = requestDto.getParams();
        Map<String,String> headers = requestDto.getHeaders();
        Boolean hasServletResponse = requestDto.getHasServletResponse();

        // 中间变量
        File localFile = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        InputStream is = null;
        OutputStream os = null;
        String resultString = "";

        try {

            // 1 创建Httpclient对象
            if (url.startsWith("https")) {
                httpClient = getIgnoeSslClient();
            } else {
                httpClient = HttpClients.createDefault();
            }

            // 2 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                params.forEach(builder::addParameter);
            }
            URI uri = builder.build();

            // 3 创建Http Post请求
            HttpPost httpPost = new HttpPost(uri);
            if (headers != null) {
                headers.forEach(httpPost::addHeader);
            }

            // 设置jsonBody
            if (StringUtils.hasText(jsonStr)) {
                StringEntity entity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }

            // 4 创建请求内容[如果有文件上传]
            if (null != multipartFile) {
                // 把文件转换成流对象FileBody
                localFile = multipartFileToFile(multipartFile);
                assert localFile != null;
                FileBody fileBody = new FileBody(localFile);
                // 以浏览器兼容模式运行，防止文件名乱码。
                MultipartEntityBuilder builder2 = MultipartEntityBuilder.create();
                builder2.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder2.addPart("file", fileBody);
                HttpEntity entity = builder2.build();
                httpPost.setEntity(entity);
            }

            // 5 执行http请求
            response = httpClient.execute(httpPost);
            // 5.1 有文件下载
            if (hasServletResponse) {
                for (Header header : response.getAllHeaders()) {
                    servletResponse.addHeader(header.getName(), header.getValue());
                }
                byte[] respStr = EntityUtils.toByteArray(response.getEntity());
                is = new ByteArrayInputStream(respStr);
                os = servletResponse.getOutputStream();
                byte[] buffer = new byte[1024 * 1024];
                int count;
                while ((count = is.read(buffer)) != -1) {
                    os.write(buffer, 0, count);
                }
                os.flush();
            } else {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            // https结束
            if (url.startsWith("https")) {
                EntityUtils.consume(response.getEntity());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭服务
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("response close happend a error! ", e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("httpClient close happend a error! ", e);
                }
            }
            if (localFile != null) {
                try {
                    localFile.delete();
                } catch (Exception e) {
                    logger.error("localFile close happend a error! ", e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("inputStream close happend a error! ", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("outputStream close happend a error! ", e);
                }
            }
        }
        return resultString;
    }

    /**
     * https 操作
     * @return
     * @throws Exception
     */
    private CloseableHttpClient getIgnoeSslClient() throws Exception {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s)->true).build();
        return HttpClients.custom().setSSLContext(sslContext).
                setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
    }

    /**
     * MultipartFile 转换成为File (需要手动删除delete)
     * @param multiFile
     * @return
     */
    private File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();

        if(NullUtil.strIsNull(fileName)){
            throw new BaseException(ErrorCode.PARAM_NULL.code, "获取的文件名称为空! ");
        }

        try {
            String path = System.getProperty("user.dir");

            fileName = URLDecoder.decode(fileName, "utf-8");
            fileName = URLEncoder.encode(fileName, "utf-8");

            File file = new File(path + "\\" + fileName);
            file.createNewFile();
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            logger.error("file transfer error:", e);
        }
        return null;
    }

}
