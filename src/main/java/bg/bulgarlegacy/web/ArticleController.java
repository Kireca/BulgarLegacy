package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.dto.CommentViewDTO;
import bg.bulgarlegacy.model.dto.CreateArticleDTO;
import bg.bulgarlegacy.model.dto.CreateCommentDTO;
import bg.bulgarlegacy.service.ArticleService;
import bg.bulgarlegacy.service.CommentService;
import bg.bulgarlegacy.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private UUID placeHolderUUID;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;

        this.commentService = commentService;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("createArticleDTO")) {
            model.addAttribute("createArticleDTO", new CreateArticleDTO());

        }

        return "article-add";
    }

    @PostMapping("/add")
    public String add(@Valid CreateArticleDTO createArticleDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createArticleDTO", createArticleDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createArticleDTO", bindingResult);
            return "redirect:/article/add";
        }


        UUID newArticleUUID = articleService.createArticle(createArticleDTO);

        return "redirect:/article/" + newArticleUUID;
    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model,
                          @PageableDefault(size = 100, sort = "id") Pageable pageable
    ) {
        ArticleViewDTO articleViewDTO = articleService.getArticleDetail(uuid)
                .orElseThrow(() -> new ObjectNotFoundException("Article with uuid " + uuid + " was not found!"));

        Page<CommentViewDTO> comments = commentService.getAllCommentsByArticleUuid(uuid, pageable);

        model.addAttribute("comments", comments);
        model.addAttribute("articleDetails", articleViewDTO);


        placeHolderUUID = uuid;
        return "article-details";
    }

    @ModelAttribute
    public CreateCommentDTO createCommentDTO(){
        return new CreateCommentDTO();
    }


    @GetMapping("{uuid}/comment")
    public String addComment( Model model) {

        if (!model.containsAttribute("createCommentDTO")) {
            model.addAttribute("createCommentDTO", new CreateCommentDTO());
        }
        return "redirect:/article/" + placeHolderUUID;
    }

    @PostMapping("/{uuid}/comment")
    public String addComment(@Valid CreateCommentDTO createCommentDTO,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createCommentDTO", createCommentDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createCommentDTO", bindingResult);
            return "redirect:/article/" + placeHolderUUID;
        }
        Long id = articleService.getArticleDetail(placeHolderUUID).orElseThrow().getAuthor().getId();
        commentService.createComment(createCommentDTO, placeHolderUUID, id);
        return "redirect:/article/" + placeHolderUUID;
    }


    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid) {
        commentService.deleteCurrentArticleComments(uuid);
        articleService.deleteArticle(uuid);
        return "redirect:/articles/all";

    }

    @DeleteMapping("/delete")
    public String deleteComment(@RequestParam Long commentId) {

        commentService.deleteCommentFromArticleById(commentId);
        return "redirect:/article/" + placeHolderUUID;

    }

}
