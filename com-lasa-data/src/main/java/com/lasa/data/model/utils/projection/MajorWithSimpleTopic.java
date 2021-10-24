package com.lasa.data.model.utils.projection;

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
