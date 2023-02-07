package ru.alxstn.irbisnews.controller.html;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alxstn.irbisnews.dto.builder.NewsTopicDTOBuilder;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

@Controller
@RequestMapping(value = "/html/topics")
public class NewsTopicHtmlController {

    private final NewsTopicRepository repository;
    private final NewsTopicDTOBuilder dtoBuilder;

    public NewsTopicHtmlController(NewsTopicRepository repository,
                                   NewsTopicDTOBuilder dtoBuilder) {
        this.repository = repository;
        this.dtoBuilder = dtoBuilder;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getAllTopics(Model model) {
        try {
            model.addAttribute("topics", repository.findAll().stream()
                    .map(dtoBuilder::fromNewsTopic));
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "topics_form";
    }

}
