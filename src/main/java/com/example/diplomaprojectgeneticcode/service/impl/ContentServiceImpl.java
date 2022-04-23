package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.entity.Content;
import com.example.diplomaprojectgeneticcode.entity.StudentContent;
import com.example.diplomaprojectgeneticcode.entity.StudentContentId;
import com.example.diplomaprojectgeneticcode.repo.ContentRepo;
import com.example.diplomaprojectgeneticcode.repo.StudentContentRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.ContentService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepo contentRepo;
    private final StudentContentRepo studentContentRepo;
    private final UserService userService;

    @Override
    public List<Content> getContentsByCourseId(UUID id) {
        return contentRepo.findContentsByCourseId(id);
    }

    @Override
    public Content getContentById(UUID id) {
        return contentRepo.findById(id)
                .orElse(new Content());
    }

    @Override
    public boolean updateCompletedOfContent(UUID contentId, UUID studentId, boolean isCompleted) {
        StudentContentId id = new StudentContentId(studentId, contentId);
        Optional<StudentContent> foundOpt = studentContentRepo.findById(id);
        if (foundOpt.isEmpty()) {
            createStudentContent(new StudentContent(id, userService.getById(studentId), contentRepo.findById(contentId).get(), isCompleted));
            return true;
        }
        StudentContent found = foundOpt.get();
        found.setCompleted(isCompleted);
        studentContentRepo.save(found);
        return true;
    }

    @Override
    public List<Content> getCompletedContentsOfStudent(UUID courseId, UUID studentId) {
        return contentRepo.getCompletedOfContentByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public boolean getCompletedOfContentByStudentId(UUID contentId, UUID studentId) {
        StudentContentId id = new StudentContentId(studentId, contentId);
        return studentContentRepo.findById(id)
                .map(StudentContent::getCompleted)
                .orElse(false);
    }

    public StudentContent createStudentContent(StudentContent studentContent) {
        return studentContentRepo.save(studentContent);
    }

    @Override
    public Content updateContent(UUID id, Content content) {
        return contentRepo.save(content);
    }
}
