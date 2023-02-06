package ru.alxstn.irbisnews.controller.html;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public String getNews(@PathVariable(required = false) String topic,
                          @PathVariable(required = false) String source,
                          @PathVariable(required = false) Optional<Integer> page,
                          @PathVariable(required = false) Optional<Integer> size,
                          Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

        try {
            Page<NewsDTO> newsPage = service.findNews(source, topic, currentPage - 1, pageSize);
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