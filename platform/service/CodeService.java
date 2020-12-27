package platform.service;

import javassist.tools.web.BadHttpRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public interface CodeService extends CrudRepository<Code, UUID> {
    default Code[] getCode() {
        return this.findAllByTimeAndViewsOrderByDateDesc(0,0).toArray(Code[]::new);
    }
    default Code[] getLastCode() {
        return this.findTop10ByTimeAndViewsOrderByDateDesc(0,0).toArray(Code[]::new);
    }
    default Map<String, String> saveCode(Code code) {
        Map<String, String> map = new HashMap<>();
        map.put("id", this.save(code).getStringId());
        return map;
    }
    default Code getCodeById(UUID id) {
        Optional<Code> optional = this.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Code code = optional.get();

        if (code.getViews() > 0) {
            code.setViews(code.getViews() - 1);
            if (code.getViews() <= 0) {
                this.delete(code);
                code.setViews(-1);
            } else {
                this.save(code);
            }
        }
        if (code.getTime() > 0) {
            if (LocalDateTime.now().isAfter(code.getDate().plusSeconds(code.getTime()))) {
                this.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            code.setTime(code.getTime() + Duration.between(LocalDateTime.now(), code.getDate()).getSeconds());
            if (code.getTime() < 0) {
                code.setTime(0);
            }
        }
        return code;
    }

    Optional<Code> findById(UUID id);
    List<Code> findAllByTimeAndViewsOrderByDateDesc(long time, int views);
    List<Code> findTop10ByTimeAndViewsOrderByDateDesc(long time, int views);
}
