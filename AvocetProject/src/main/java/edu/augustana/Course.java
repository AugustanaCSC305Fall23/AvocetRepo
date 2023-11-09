package edu.augustana;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class Course {
    private static List<LessonPlan> plans;
    //private String title;

    public Course() {
        this.plans = new ArrayList<LessonPlan>();
    }

    public static List<LessonPlan> getPlans() {
        return plans;
    }

    public void addLessonPlan(LessonPlan plan) {
        this.plans.add(plan);
    }

    public static String toJson() {
        Gson gson = new Gson();
        return gson.toJson(getPlans());
    }


}
