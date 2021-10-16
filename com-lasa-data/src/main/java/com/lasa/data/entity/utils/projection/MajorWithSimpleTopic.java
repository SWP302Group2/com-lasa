package com.lasa.data.entity.utils.projection;

import com.lasa.data.entity.Topic;

import java.util.Collection;

public interface MajorWithSimpleTopic {
    String getId();
    String getName();
    Collection<SimpleTopic> getTopics();

    interface SimpleTopic{
        Integer getId();
        String getName();
        String getCourseId();
    }
}
