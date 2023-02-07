package ru.alxstn.irbisnews.controller.html;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alxstn.irbisnews.service.NewsTopicService;

@Controller
@RequestMapping(value = "/html/topics")
public class NewsTopicHtmlController {

private final NewsTopicService service;

    public NewsTopicHtmlController(NewsTopicService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getAllTopics(Model model) {
        try {
            model.addAttribute("topics", service.findAllTopics());
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "topics_form";
    }

}
