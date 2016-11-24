package pers.rododo.nativegradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

/**
 * Created by rododo on 16-11-24.
 */
class NativeGradle implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.create("compile",  Exec.class);
        project.tasks.create("assemble");
        project.tasks.create("link", Exec.class);
    }
}
