package org.jitsi.jibri

import org.jitsi.jibri.capture.ffmpeg.executor.FfmpegExecutorParams
import org.jitsi.jibri.capture.ffmpeg.executor.impl.MacFfmpegExecutor
import org.jitsi.jibri.selenium.JibriSelenium
import org.jitsi.jibri.selenium.JibriSeleniumOptions
import org.jitsi.jibri.service.impl.StreamingJibriService
import org.jitsi.jibri.service.impl.StreamingOptions
import org.jitsi.jibri.sink.impl.FileSink
import org.testng.annotations.Test
import java.io.File

class JibriTest
{
    @Test
    fun jibriTest()
    {
//        val jibri = Jibri()
//        jibri.loadConfig("/Users/bbaldino/jitsi/jibri-new/")
//        jibri.startRecording(JibriServiceOptions(
//                recordingSinkType = RecordingSinkType.FILE,
//                callName = "jibritest",
//                baseUrl = "https://brian2.jitsi.net"
//        ))
//        Thread.sleep(10000)
//        jibri.stopRecording()
    }

    @Test
    fun jibriSeleniumTest()
    {
        val options = JibriSeleniumOptions(
                baseUrl = "https://meet.jit.si")
        val selenium = JibriSelenium(options)

        selenium.joinCall("test")
        Thread.sleep(30000)
        selenium.leaveCallAndQuitBrowser()
    }

    @Test
    fun ffmpegTest()
    {
        val ffmpeg = MacFfmpegExecutor()
        ffmpeg.launchFfmpeg(
                ffmpegExecutorParams = FfmpegExecutorParams(),
                sink = FileSink(
                        recordingsDirectory = File("/tmp/recordings"),
                        callName = "unit"
                )
        )
        Thread.sleep(10000)
        ffmpeg.stopFfmpeg()
    }

    @Test
    fun streamTest() {
        val jibriStreamingService = StreamingJibriService(
                StreamingOptions(
                        youTubeStreamKey = "rtmp://a.rtmp.youtube.com/live2",
                        callUrlInfo = CallUrlInfo("https://meet.jit.si", "brianTest")
                )
        )
        jibriStreamingService.start()
        Thread.sleep(Long.MAX_VALUE)
        jibriStreamingService.stop()
    }

    @Test
    fun configTest() {
//        val jibri = Jibri()
//        jibri.loadConfig("/Users/bbaldino/jitsi/jibri-new/")
    }

    @Test
    fun healthTest() {
//        val jibri = Jibri()
//        val healthJson = jibri.healthCheck()
//        println(healthJson)
    }
}