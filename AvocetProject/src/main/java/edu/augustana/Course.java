package edu.augustana;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private List<LessonPlan> plans;
    //private String title;

    public Course() {
        this.plans = new ArrayList<LessonPlan>();
    }

    public List<LessonPlan> getPlans() {
        return plans;
    }

    public void addLessonPlan(LessonPlan plan) {
        this.plans.add(plan);
    }


}
