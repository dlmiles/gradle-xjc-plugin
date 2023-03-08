package org.unbrokendome.gradle.plugins.xjc

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.platform.commons.annotation.Testable
import org.unbrokendome.gradle.plugins.xjc.samples.TestEachDslFlavor
import org.unbrokendome.gradle.plugins.xjc.samples.UseSampleProject
import org.unbrokendome.gradle.plugins.xjc.testutil.GradleProjectDir
import java.io.File


@UseSampleProject("xjc-tool-future-latest")
class XjcToolFutureLatestIntegrationTest : AbstractBasicIntegrationTest() {

    @TestEachDslFlavor
    @Testable
    fun test(runner: GradleRunner, @GradleProjectDir projectDir: File) {
        val projectName = "xjc-tool-future-latest"

        val buildResult = super.test(runner, projectDir, projectName)

        val msg = "xjcVersionUnsupportedStrategy is set latest"
        val bf = buildResult.output.contains(msg)
        assertTrue(bf, "Expected message in build output log: $msg")
    }
}
