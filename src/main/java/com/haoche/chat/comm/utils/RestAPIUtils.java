package com.haoche.chat.comm.utils;

import com.haoche.chat.comm.MyX509TrustManager;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestAPIUtils {
    /**
     * Create a httpClient instance
     *
     * @param isSSL if the request is protected by ssl
     * @return HttpClient instance
     */
    public static HttpClient getHttpClient(boolean isSSL, String CacertFilePath, String CacertFilePassword) {
        CloseableHttpClient client = null;

        if (isSSL) {
            try {
               /* X509HostnameVerifier verifier = new X509HostnameVerifier() {
                    public void verify(String host, SSLSocket ssl) throws IOException {
                    }

                    public void verify(String host, X509Certificate cert) throws SSLException {
                    }

                    public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                    }

                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                };*/

                TrustManager[] tm = new TrustManager[]{new MyX509TrustManager(CacertFilePath, CacertFilePassword)};

                SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, tm, new SecureRandom());

                client = HttpClients.custom().setSslcontext(sslContext).setHostnameVerifier(SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER).build();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            client = HttpClients.createDefault();
        }

        return client;
    }

    /**
     * Check illegal String
     *
     * @param regex reg expression
     * @param str   string to be validated
     * @return if matched
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.lookingAt();
    }


}
