package sg.edu.nus.iss.lovecalculator.repository;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.lovecalculator.model.Lc;

@Repository
public class LcRepository {
    
    @Autowired @Qualifier("love")
    private RedisTemplate<String, String> template;
    
    public void save(Lc l) {
        this.template.opsForValue()
            .set(l.getRefId(), l.toJSON().toString());
    }

    // public Optional<Lc> get(String refId) throws IOException {
    //     String json = template.opsForValue().get(refId);
    //     if((null == json || json.trim().length() <= 0)) {
    //         return Optional.empty();
    //     }
    //     return Optional.of(Lc.create(json));
    // }
}
