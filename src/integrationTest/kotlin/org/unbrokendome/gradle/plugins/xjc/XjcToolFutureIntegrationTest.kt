package org.unbrokendome.gradle.plugins.xjc

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.platform.commons.annotation.Testable
import org.unbrokendome.gradle.plugins.xjc.samples.TestEachDslFlavor
import org.unbrokendome.gradle.plugins.xjc.samples.UseSampleProject
import org.unbrokendome.gradle.plugins.xjc.testutil.GradleProjectDir
import org.unbrokendome.gradle.plugins.xjc.testutil.runGradle
import java.io.File


@UseSampleProject("xjc-tool-future")
class XjcToolFutureIntegrationTest : AbstractBasicIntegrationTest() {

    @TestEachDslFlavor
    @Testable
    fun test(runner: GradleRunner, @GradleProjectDir projectDir: File) {
        //val projectName = "xjc-tool-future"

        val buildResult = runner.runGradle("build", expectFailure=true)

        assertEquals(TaskOutcome.FAILED, buildResult.task(":xjcGenerate")?.outcome)
        assertNull(buildResult.task(":build")?.outcome)

        val msg = "xjcVersionUnsupportedStrategy"
        val bf = buildResult.output.contains(msg)
        Assertions.assertTrue(bf, "Expected message in build output log: $msg")
    }
}
