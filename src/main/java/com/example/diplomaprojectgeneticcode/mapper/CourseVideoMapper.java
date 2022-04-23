package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.CourseVideoDTO;
import com.example.diplomaprojectgeneticcode.entity.CourseVideo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CourseVideoMapper {

    public CourseVideoDTO toDto(CourseVideo courseVideo) {
        log.info("CourseVideo: {}", courseVideo);
        Optional<CourseVideo> courseVideoOpt = Optional.ofNullable(courseVideo);
        CourseVideoDTO courseVideoDTO = new CourseVideoDTO();

        courseVideoOpt.map(CourseVideo::getId).ifPresent(courseVideoDTO::setId);
        courseVideoOpt.map(CourseVideo::getTitle).ifPresent(courseVideoDTO::setTitle);
        courseVideoOpt.map(CourseVideo::getVideoUrl).ifPresent(courseVideoDTO::setVideoUrl);
        courseVideoOpt.map(CourseVideo::getDuration).ifPresent(courseVideoDTO::setDuration);

        log.info("CourseVideoDto: {}", courseVideoDTO);

        return courseVideoDTO;
    }

    public List<CourseVideoDTO> toDto(List<CourseVideo> courseVideos) {
        return Optional.ofNullable(courseVideos)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
