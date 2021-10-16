package com.lasa.data.entity.utils.projection;

import com.lasa.data.entity.Topic;

import java.util.Collection;

public interface MajorWithListTopicIdAndName {
    String getId();
    String getName();
    Collection<TopicWithNameAndId> getTopics();

    interface TopicWithNameAndId{
        Integer getId();
        String getName();
    }
}
