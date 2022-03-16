package eu.senla.feignclient.classic;

import eu.senla.feignclient.classic.model.ClassicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "feignClassicClient", url = "${demo-service.url.http}")
public interface FeignClassicClient {
    @GetMapping("multiply")
//    предоставление параметра value для @RequestParam равное имени параметров вызываемого эндпоинта обязательно
    ClassicResponse getResponse(@RequestParam("param1") Integer param1, @RequestParam("param2") Integer param2);
}
