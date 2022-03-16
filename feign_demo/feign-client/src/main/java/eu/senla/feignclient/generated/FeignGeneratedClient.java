package eu.senla.feignclient.generated;

import eu.senla.feignclient.server.api.DemoControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "feignGenerated", url = "${demo-service.url.http}")
public interface FeignGeneratedClient extends DemoControllerApi {
}
