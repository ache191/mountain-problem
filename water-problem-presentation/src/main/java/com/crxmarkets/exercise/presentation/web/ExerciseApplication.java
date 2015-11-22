package com.crxmarkets.exercise.presentation.web;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Resteasy Application class which determines api start point (context path)
 *
 * @author oleksandr.chekanskyi
 */
@ApplicationPath("/api")
public class ExerciseApplication extends Application {
}
