package sg.edu.nus.iss.lovecalculator.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.lovecalculator.model.Lc;
import sg.edu.nus.iss.lovecalculator.repository.LcRepository;

@Service
public class LcService {

    @Autowired
    private LcRepository lRepo;
    
    @Value("${lc.love.calculator.host}")
    private String loveCalculatorHost;

    @Value("${lc.love.calculator.url}")
    private String loveCalculatorUrl;

    @Value("${lc.love.calculator.api.key}")
    private String loveCalculatorApiKey;

    public Optional<Lc> getResult(String fname, String sname) throws IOException {
        String lcUrl = UriComponentsBuilder
                        .fromUriString(loveCalculatorUrl)
                        .queryParam("fname", fname)
                        .queryParam("sname", sname)
                        .toUriString();
        RequestEntity req = RequestEntity
                        .get(lcUrl)
                        .header("X-RapidAPI-Host", loveCalculatorHost)
                        .header("X-RapidAPI-Key", loveCalculatorApiKey)
                        .build();

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> resp = template.exchange(req, String.class);
    Lc l = Lc.create(resp.getBody());
    if (null == l) {
        return Optional.empty();
    }
    return Optional.of(l);
    }

    // public Lc createRefId(String json) throws IOException {
    //     String refId = UUID.randomUUID().toString().substring(0, 8);
    //     Lc l = new Lc();
    //     l.setRefId(refId);
    //     l = Lc.create(json);
    //     return l;
    // }

    public Lc save(Lc l) {
        String refId = UUID.randomUUID().toString().substring(0, 8);
        l.setRefId(refId);
        lRepo.save(l);
        return l;
    }

    // public Optional<Lc> getDetailsByRefId(String refId) throws IOException {
    //     return lRepo.get(refId);
    // }
}
