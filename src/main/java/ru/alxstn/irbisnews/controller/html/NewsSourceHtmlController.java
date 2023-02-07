package ru.alxstn.irbisnews.controller.html;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alxstn.irbisnews.service.NewsSourceService;

@Controller
@RequestMapping(value = "/html/sources")
public class NewsSourceHtmlController {

    private final NewsSourceService service;

    public NewsSourceHtmlController(NewsSourceService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getAllSources(Model model) {
        try {
            model.addAttribute("sources", service.findAllSources());
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "sources_form";
    }

}
