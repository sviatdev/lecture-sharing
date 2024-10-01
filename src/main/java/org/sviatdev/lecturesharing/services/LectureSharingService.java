package org.sviatdev.lecturesharing.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.sviatdev.lecturesharing.dao.LectureDao;
import org.sviatdev.lecturesharing.models.Lecture;
import org.sviatdev.lecturesharing.models.LessonName;

import java.util.List;

@Service
public class LectureSharingService {

    private final LectureDao lectureDao;

    public LectureSharingService(LectureDao lectureDao) {
        this.lectureDao = lectureDao;
    }

    public ResponseEntity<List<Lecture>> getLectures() {
        return ResponseEntity.ok(lectureDao.findAll());
    }

    public ResponseEntity<String> insertLecture(String lecture) {
        Lecture demolecture = new Lecture();
        demolecture.setLessonName(LessonName.LITERATURE);
        demolecture.setTitle("Literature Title" + lecture);
        demolecture.setLessonDescription("Literature Desription");
        lectureDao.save(demolecture);
        return ResponseEntity.ok("INSERTED");
    }

    public ResponseEntity<Lecture> getLecture(Long id) {
        return ResponseEntity.ok(lectureDao.findLectureById(id));
    }

    public void removeLecture(Long id) {
        lectureDao.removeLecture(id);
    }
}
