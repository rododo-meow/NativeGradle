import org.gradle.api.Project
import org.gradle.api.invocation.Gradle
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.GradleRunner
import org.junit.rules.TemporaryFolder
import pers.rododo.nativegradle.NativeGradle

import static org.junit.Assert.*
import org.junit.Test

/**
 * Created by rododo on 16-11-24.
 */
class NativeGradleTest {
    @Test
    public void testApply() {
        ProjectBuilder builder = ProjectBuilder.builder();
        Project project = builder.build();
        project.pluginManager.apply("pers.rododo.nativegradle");
    }

    @Test
    public void testGeneratedTasks() {
        ProjectBuilder builder = ProjectBuilder.builder();
        Project project = builder.build();
        project.pluginManager.apply("pers.rododo.nativegradle");
        assertFalse(project.getTasksByName("assemble", false).empty);
        assertFalse(project.getTasksByName("compile", false).empty);
        assertFalse(project.getTasksByName("link", false).empty);
    }

    private GradleRunner runner(String script) {
        TemporaryFolder tmpDir = new TemporaryFolder();
        tmpDir.create();
        File tmp = tmpDir.newFile("build.gradle");
        tmp << script;
        tmp.deleteOnExit();
        tmpDir.root.deleteOnExit();
        return GradleRunner.create().withGradleVersion("2.13").withPluginClasspath().withProjectDir(tmpDir.root);
    }

    @Test
    public void testEvaluation() {
        runner("""
plugins { id 'pers.rododo.nativegradle' }
""").withArguments("assemble").build();
    }
}
