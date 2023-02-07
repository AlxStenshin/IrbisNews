package ru.alxstn.irbisnews.controller.html;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alxstn.irbisnews.dto.NewsDTO;
import ru.alxstn.irbisnews.service.NewsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping
public class NewsHtmlController {

    private final NewsService service;

    public NewsHtmlController(NewsService service) {
        this.service = service;
    }

    @GetMapping(value = {"", "/", "/html", "/html/"}, produces = MediaType.TEXT_HTML_VALUE)
    public String redirect() {
        return "redirect:/html/news";
    }


    @GetMapping(value = "/html/news{page}{size}{topic}{source}",
            produces = MediaType.TEXT_HTML_VALUE)
    public String getNews(@RequestParam(required = false) String topic,
                          @RequestParam(required = false) String source,
                          @RequestParam(required = false) Integer page,
                          @RequestParam(required = false) Integer size,
                          Model model) {
        try {
            page = Optional.ofNullable(page).map(integer -> integer - 1).orElse(0);
            Page<NewsDTO> newsPage = service.findNews(source, topic, page, size);
            model.addAttribute("newsPage", newsPage);
            int totalPages = newsPage.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "news_form";
    }

}