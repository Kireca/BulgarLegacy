package bg.bulgarlegacy.service.schedulers;

import bg.bulgarlegacy.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ArticlesCleanerSchedulerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticlesCleanerScheduler articlesCleanerScheduler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCleanUpScheduledExecution() {
        articlesCleanerScheduler.cleanUp();

        verify(articleService, times(1)).deleteAllArticles();
    }
}
