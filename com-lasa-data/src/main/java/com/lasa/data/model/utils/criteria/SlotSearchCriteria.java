package com.lasa.data.model.utils.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SlotSearchCriteria {
    
    @ApiModelProperty(name = "lecId", dataType = "Integer", value = "Get lecturer by id")
    private List<@Min(value = 1)Integer> lecId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "timeStart", dataType = "String", value = "Slot begins at the time")
    private LocalDateTime timeStart;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "timeEnd", dataType = "String", value = "Slot ends at the time")
    private LocalDateTime timeEnd ;

    @ApiModelProperty(name = "getTopic", dataType = "Boolean", value = "Get information of topic")
    private Boolean getTopic = false;

    @ApiModelProperty(name = "getLecturer", dataType = "Boolean", value = "Get information of lecturer")
    private Boolean getLecturer = false;

    @ApiModelProperty(name = "slotId", dataType = "Integer", value = "Get slot by a list slotId")
    private List<@Min(1) Integer> slotId;

    @ApiModelProperty(name = "topicId", dataType = "Integer", value = "Get slot by a list slotId")
    private List<@Min(1) Integer> topicId;

    private Integer status;

    @Builder
    public SlotSearchCriteria(List<@Min(value = 1) Integer> lecId, LocalDateTime timeStart, LocalDateTime timeEnd, Boolean getTopic, Boolean getLecturer, List<@Min(1) Integer> slotId, List<@Min(1) Integer> topicId, Integer status) {
        this.lecId = lecId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.getTopic = getTopic;
        this.getLecturer = getLecturer;
        this.slotId = slotId;
        this.topicId = topicId;
        this.status = status;
    }
}
