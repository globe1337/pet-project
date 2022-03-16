package eu.senla.feignclient;

import eu.senla.feignclient.classic.FeignClassicClient;
import eu.senla.feignclient.classic.model.ClassicResponse;
import eu.senla.feignclient.generated.FeignGeneratedClient;
import eu.senla.feignclient.secured.FeignSecuredClient;
import eu.senla.feignclient.server.model.DemoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class Controller {
    @Value("${demo-service.url.http}")
    private String demoServerUrl;

    private final FeignGeneratedClient feignGeneratedClient;
    private final FeignSecuredClient feignSecuredClient;
    private final FeignClassicClient feignClassicClient;

    @GetMapping("generated")
    public ResponseEntity<DemoResponse> getFromClientGenerated(@RequestParam Integer param1, @RequestParam Integer param2) {
        return feignGeneratedClient.getResponse(param1, param2);
    }

    @GetMapping("secured")
    public ResponseEntity<DemoResponse> getFromClientSecured(@RequestParam Integer param1, @RequestParam Integer param2) {
        return feignSecuredClient.getResponse(param1, param2);
    }

    @GetMapping("classic")
    public ClassicResponse getFromClientClassic(@RequestParam Integer param1, @RequestParam Integer param2) {
        return feignClassicClient.getResponse(param1, param2);
    }

    @GetMapping("rest-template")
    public ResponseEntity<ClassicResponse> getFromRestTemplate(@RequestParam Integer param1, @RequestParam Integer param2) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = demoServerUrl + "/multiply";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("param1", param1)
                .queryParam("param2", param2);

        return restTemplate.getForEntity(builder.build().encode().toUri(), ClassicResponse.class);
    }
}
