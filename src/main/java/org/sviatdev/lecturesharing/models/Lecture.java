package org.sviatdev.lecturesharing.models;

import jakarta.persistence.*;

@Entity
@Table(name="LS_LECTURE")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 16)
    private LessonName lessonName;
    @Column(nullable = false, length = 32)
    private String title;
    @Column(nullable = false, length = 64)
    private String lessonDescription;

    public Lecture() {
    }

    public Lecture(LessonName lessonName, String title, String lessonDescription) {
        this.lessonName = lessonName;
        this.title = title;
        this.lessonDescription = lessonDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LessonName getLessonName() {
        return lessonName;
    }

    public void setLessonName(LessonName lessonName) {
        this.lessonName = lessonName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }
}
