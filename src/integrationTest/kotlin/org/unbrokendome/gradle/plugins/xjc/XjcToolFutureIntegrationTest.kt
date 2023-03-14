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

    // Disabled to enable this test with we ideally need to supply an XJC
    // Tool implementation that has a future version such as in the
    // MANIFEST.MF:
    //  Specification-Version: 98.0
    // Maybe it is possible to achieve this in the sample project tree
    //  and have gradle build a subproject before using it to run this test.
    //@TestEachDslFlavor
    //@Testable
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
