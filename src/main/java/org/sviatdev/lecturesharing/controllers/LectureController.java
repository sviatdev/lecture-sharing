package org.sviatdev.lecturesharing.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sviatdev.lecturesharing.models.Lecture;
import org.sviatdev.lecturesharing.services.LectureSharingService;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    private final LectureSharingService lectureSharingService;

    public LectureController(LectureSharingService lectureSharingService) {
        this.lectureSharingService = lectureSharingService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lecture>> getAll() {
        return lectureSharingService.getLectures();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Lecture> get(@PathVariable Long id) {
        return lectureSharingService.getLecture(id);
    }

    @PostMapping("/insert/{lecture}")
    public ResponseEntity<String> add(@PathVariable String lecture) {
        return lectureSharingService.insertLecture(lecture);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        lectureSharingService.removeLecture(id);
    }
}
