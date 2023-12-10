package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.dto.CommentViewDTO;
import bg.bulgarlegacy.model.dto.CreateArticleDTO;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.model.entites.UserRoleEntity;
import bg.bulgarlegacy.service.ArticleService;
import bg.bulgarlegacy.service.CommentService;
import bg.bulgarlegacy.service.UserService;
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

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserService userService;
    protected UUID placeHolderUUID;

    public ArticleController(ArticleService articleService, CommentService commentService, UserService userService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.userService = userService;
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

        //Page for comments view under each article.
        Page<CommentViewDTO> comments = commentService.getAllCommentsByArticleUuid(uuid, pageable);

        //Here we take the logged-in user to access his details to check if he has access to delete articles.
        UserEntity currentUser = userService.getCurrentUser();
        List<UserRoleEntity> roles = currentUser.getRoles();
        UserRoleEntity currentUserRoleEnum = roles.get(0);
        String currentUserRole = currentUserRoleEnum.getRole().toString();


        model.addAttribute("currentUserRole", currentUserRole);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("comments", comments);
        model.addAttribute("articleDetails", articleViewDTO);

        //Here we keep the UUID of the current article cuz we need it for the redirect path after delete comment.
        this.placeHolderUUID = articleViewDTO.getUuid();

        return "article-details";
    }



    @DeleteMapping("/{uuid}")
    public String deleteArticle(@PathVariable("uuid") UUID uuid) {
        commentService.deleteCurrentArticleComments(uuid);
        articleService.deleteArticle(uuid);
        return "redirect:/articles/all";

    }

    @DeleteMapping("/delete")
    public String deleteComment(@RequestParam Long commentId) {

        commentService.deleteCommentFromArticleById(commentId);
        return "redirect:/article/" + this.placeHolderUUID;

    }

}
