package bg.bulgarlegacy.service.schedulers;

import bg.bulgarlegacy.service.ArticleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticlesCleanerScheduler {


    private final ArticleService articleService;

    public ArticlesCleanerScheduler(ArticleService articleService) {
        this.articleService = articleService;
    }


    @Scheduled(cron = "0 0 0 1 * ?")
    public void cleanUp() {
        articleService.deleteAllArticles();
        System.out.println("All articles deleted!");
    }

}
