package platform.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.model.Code;
import platform.service.CodeService;

import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class HtmlController {

    private static final Logger LOG = Logger.getLogger(HtmlController.class.getName());

    CodeService codeService;

    public HtmlController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code")
    public String getCodeHtml(Model model) {
        LOG.info("/code");
        model.addAttribute("title", "Show");
        model.addAttribute("blocks", codeService.getCode());
        return "showCode";
    }

    @GetMapping("/code/{id}")
    public String getCodeHtmlById(Model model, @PathVariable final UUID id) {
        LOG.info("/code/" + id);
        model.addAttribute("title", "Code");
        model.addAttribute("blocks", codeService.getCodeById(id));
        return "showCode";
    }

    @GetMapping("/code/new")
    public String newCodeHtml(Model model) {
        LOG.info("/code/new");
        model.addAttribute("title", "Create");
        //model.addAttribute("code", codeService.getCode().getCode());
        return "newCode";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeHtml(Model model) {
        LOG.info("/code/latest");
        LOG.info(Arrays.toString(codeService.getLastCode()));
        model.addAttribute("title", "Latest");
        model.addAttribute("blocks", codeService.getLastCode());
        return "showCode";
    }
}
