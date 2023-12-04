package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.service.ArticleService;
import bg.bulgarlegacy.service.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model) {

        ArticleViewDTO articleViewDTO = articleService.getArticleDetail(uuid)
                .orElseThrow(() -> new ObjectNotFoundException("Article with uuid " + uuid + " was not found!"));

        model.addAttribute("articleDetails", articleViewDTO);

        return "article-details";
    }


}
