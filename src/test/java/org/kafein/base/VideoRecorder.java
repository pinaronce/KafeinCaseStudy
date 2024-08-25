package org.kafein.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class VideoRecorder {
    private final String videoFilePath;
    private Process process;

    public VideoRecorder() {
        String directory = ".gauge/videos";

        File dir = new File(directory);

        if (!dir.exists() && !dir.mkdirs()) {
            System.out.println("Failed to create directory: " + directory);
        }
        this.videoFilePath = directory + "/testVideo_" + System.currentTimeMillis() + ".mp4";
    }

    public void startRecording() {
        String command = "ffmpeg -f avfoundation -i \"1\" -r 30 -s 1280x720 -t 300 " + videoFilePath;

        try {
            ProcessBuilder builder = new ProcessBuilder("/bin/sh", "-c", command);
            builder.redirectErrorStream(true);

            this.process = builder.start();

            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.process.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("ffmpeg error: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            System.err.println("Failed to start video recording: " + e.getMessage());
        }
    }

    public void stopRecording() {
        if (this.process != null) {
            this.process.destroy();
            System.out.println("Video recording stopped: " + videoFilePath);
        }
    }
}
