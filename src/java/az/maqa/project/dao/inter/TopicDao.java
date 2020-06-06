package az.maqa.project.dao.inter;

import az.maqa.project.model.AdvancedSearch;
import az.maqa.project.model.Topic;


import java.util.List;

public interface TopicDao {
    List<Topic> getTopicList() throws Exception;

    boolean add(Topic topic) throws Exception;

    Topic getTopicById(Long id) throws Exception;

    boolean update(Topic topic, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<Topic> search(String keyword) throws Exception;


}
