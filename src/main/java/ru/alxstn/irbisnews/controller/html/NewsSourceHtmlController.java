package ru.alxstn.irbisnews.controller.html;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alxstn.irbisnews.dto.builder.NewsSourceDTOBuilder;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;

@Controller
@RequestMapping(value = "/html/sources")
public class NewsSourceHtmlController {

    private final NewsSourceRepository repository;
    private final NewsSourceDTOBuilder dtoBuilder;

    public NewsSourceHtmlController(NewsSourceRepository repository,
                                    NewsSourceDTOBuilder dtoBuilder) {
        this.repository = repository;
        this.dtoBuilder = dtoBuilder;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getAllSources(Model model) {
        try {
            model.addAttribute("sources", repository.findAll().stream()
                    .map(dtoBuilder::fromNewsSource).toList());
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "sources_form";
    }

}
