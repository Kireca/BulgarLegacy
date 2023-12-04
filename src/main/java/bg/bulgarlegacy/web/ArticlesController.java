package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticlesController {

    private final ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/articles")
    public String articles() {
        return "articles";
    }


    @GetMapping("/all")
    public String all(Model model,
                      @PageableDefault(
                              size = 3,
                              sort = "uuid"
                      ) Pageable pageable) {
        Page<ArticleViewDTO> allArticles = articleService.getAllArticles(pageable);

        model.addAttribute("articles", allArticles);

        return "articles";
    }

}
