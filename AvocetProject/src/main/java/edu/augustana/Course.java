package edu.augustana;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private List<LessonPlan> plans;
    private List<String> selectedEvents;
    //private String title;


    public Course() {
        this.plans = new ArrayList<LessonPlan>();
        this.selectedEvents = new ArrayList<>();

    }

    public List<LessonPlan> getPlans() {
        return plans;
    }

    public void addLessonPlan(LessonPlan plan) {
        this.plans.add(plan);
    }


    public List<String> getSelectedEvents() {
        return selectedEvents;
    }


}
