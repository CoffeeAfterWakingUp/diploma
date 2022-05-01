package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.ContentDTO;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.mapper.ContentMapper;
import com.example.diplomaprojectgeneticcode.mapper.CourseVideoMapper;
import com.example.diplomaprojectgeneticcode.service.interfaces.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/content")
@RequiredArgsConstructor
public class ContentRestController extends AbstractRestController {

    private final ContentService contentService;
    private final ContentMapper contentMapper;

    @GetMapping("{courseId}/contents")
    public ResponseEntity<ResponseDTO<List<ContentDTO>>> getContentsByCourseId(@PathVariable UUID courseId) {
        return successOK(
                contentMapper.toDto(
                        contentService.getContentsByCourseId(courseId)
                )
        );
    }


    @GetMapping("/{contentId}")
    public ResponseEntity<ResponseDTO<ContentDTO>> getContentById(@PathVariable UUID contentId) {
        return successOK(
                contentMapper.toDto(
                        contentService.getContentById(contentId)
                )
        );
    }

    @GetMapping("/{contentId}/{studentId}")
    public ResponseEntity<ResponseDTO<Boolean>> getCompletedOfContentByStudentId(@PathVariable UUID contentId,
                                                                                 @PathVariable UUID studentId) {
        return successOK(contentService.getCompletedOfContentByStudentId(contentId, studentId));
    }

    @GetMapping("{courseId}/{studentId}/contents/completed")
    public ResponseEntity<ResponseDTO<List<ContentDTO>>> getCompletedContentsOfStudent(@PathVariable UUID courseId,
                                                                                       @PathVariable UUID studentId) {

        return successOK(contentMapper.toDto(contentService.getCompletedContentsOfStudent(courseId, studentId)));
    }


    @PutMapping("/{contentId}")
    public ResponseEntity<ResponseDTO<Boolean>> updateCompletedOfContent(@PathVariable UUID contentId,
                                                                         @RequestParam UUID studentId,
                                                                         @RequestParam boolean isUserCompleted) {
        return successOK(contentService.updateCompletedOfContent(contentId, studentId, isUserCompleted));
    }


    @PostMapping("/add/{courseId}/contents")
    public ResponseEntity<ResponseDTO<Boolean>> addContentsToStudent(@PathVariable UUID courseId,
                                                                     @RequestParam String username) {

        return successOK(contentService.addContentsToStudent(courseId, username));

    }







}
