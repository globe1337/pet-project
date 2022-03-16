package eu.senla.feignclient.secured;

import feign.Client;
import lombok.SneakyThrows;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLSocketFactory;
//данная конфигурация общения по ssl говорит доверять всем сертификатам
//но при желании можно настроить работу на определенный сертификат
public class FeignSecuredConfig {
    @Bean
    public Client feignClient() {
        return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
    }

    @SneakyThrows
    private SSLSocketFactory getSSLSocketFactory() {
        return SSLContexts.custom().loadTrustMaterial(null, new TrustAllStrategy()).build().getSocketFactory();
    }
}
