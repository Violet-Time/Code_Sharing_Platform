package platform.controller;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.service.CodeService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger LOG = Logger.getLogger(ApiController.class.getName());

    CodeService codeService;

    public ApiController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code")
    public Code[] getCodeApi() {
        LOG.info("/code");
        return codeService.getCode();
    }

    @GetMapping("/code/{id}")
    public Code getCodeApiById(@PathVariable final UUID id) {
        LOG.info("/code/" + id);
        return codeService.getCodeById(id);
    }

    @GetMapping("/code/latest")
    public Code[] getLatestCode() {
        LOG.info("/code/latest");
        LOG.info(codeService.getLastCode().length + "");
        LOG.info(Arrays.toString(codeService.getLastCode()));
        return codeService.getLastCode();
    }

    @PostMapping("/code/new")
    public Map<String, String> newCodeApi(@RequestBody Map<String, String> body) {
        LOG.info("/code/new");
        LOG.info(body.toString());
        Map<String, String> code = codeService.saveCode(new Code(body.get("code") != null ? body.get("code") : "",
                                                                    Integer.parseInt(body.get("time")),
                                                                    body.get("views") != null ? Integer.parseInt(body.get("views")) : 0));
        LOG.info(code.toString());
        return code;
    }
}
