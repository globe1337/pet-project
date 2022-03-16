package eu.senla.feignclient.secured;

import eu.senla.feignclient.server.api.DemoControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "feignSecured", url = "${demo-service.url.https}", configuration = FeignSecuredConfig.class)
public interface FeignSecuredClient extends DemoControllerApi {
}
