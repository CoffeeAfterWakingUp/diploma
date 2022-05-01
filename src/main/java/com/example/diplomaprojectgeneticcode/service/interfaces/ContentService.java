package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.entity.Content;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ContentService {
    List<Content> getContentsByCourseId(UUID id);
    Content getContentById(UUID id);
    boolean updateCompletedOfContent(UUID contentId, UUID studentId, boolean isCompleted);
    Content updateContent(UUID id, Content content);
    boolean getCompletedOfContentByStudentId(UUID contentId, UUID studentId);

    List<Content> getCompletedContentsOfStudent(UUID courseId, UUID studentId);

    boolean addContentsToStudent(UUID courseId, String username);
}
