import com.epam.Per1.DbException;
import com.epam.Per1.dao.TopicDao;
import com.epam.Per1.service.impl.TopicService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class testDao {

    @Test
    public void testTopicDao() throws DbException {

        TopicDao topicDao = Mockito.mock(TopicDao.class);

        TopicDao spiedTopicDao = Mockito.spy(topicDao);

        TopicService topicService = new TopicService();

        Mockito.when(topicService.countAllTopics()).thenReturn(3);

        int topicCount = spiedTopicDao.countAllTopics();

        assert (topicCount == 3);
    }
}
