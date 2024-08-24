package org.kafein.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class VideoRecorder {
    private final String videoFilePath; // Başlatma değeri atanacak
    private Process process;

    public VideoRecorder() {
        // Video dosyalarının kaydedileceği dizin
        String directory = "src/test/java/org/kafein/base/videos";
        File dir = new File(directory);

        if (!dir.exists() && !dir.mkdirs()) {
            System.out.println("Dizin oluşturma başarısız: " + directory);
        }
        this.videoFilePath = directory + "/testVideo_" + System.currentTimeMillis() + ".mp4";  // Dosya yolu her durumda başlatılıyor
    }

    public void startRecording() {
        String command = "ffmpeg -f avfoundation -i \"0\" -r 300 -s 1280x720 -pix_fmt uyvy422 -t 300 out.mp4";
        try {
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            builder.redirectErrorStream(true);
            this.process = builder.start();

            // Hata mesajlarını logla
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("ffmpeg error: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            System.err.println("Video kaydı başlatılamadı: " + e.getMessage());
        }
    }

    public void stopRecording() {
        if (this.process != null) {
            this.process.destroy();
            System.out.println("Video recording stopped: " + videoFilePath);
        }
    }

    public void startRecordin2g() {
        String command = "ffmpeg -y -f avfoundation -i \"0\" -r 30 -s 1280x720 -pix_fmt uyvy422 -t 300 " + videoFilePath;

        System.out.println("command: " + command);
        System.out.println("videoFilePath: " + videoFilePath);

        try {
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            builder.redirectErrorStream(true);
            this.process = builder.start();

            this.process.getErrorStream().transferTo(System.out);


            // Hata mesajlarını oku ve logla
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("ffmpeg error: " + line);
                    }
                } catch (IOException e) {
                    System.out.println("ffmpeg err error: " + e);

                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            System.err.println("Video kaydı başlatılamadı: " + e.getMessage());
        }
    }

    public void startRecording3() {
        String command = "ffmpeg -f avfoundation -i \"1\" -r 30 -s 1280x720 -pix_fmt uyvy422 -t 300 " + videoFilePath;
        try {
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            builder.redirectErrorStream(true);
            this.process = builder.start();

            // Hata mesajlarını logla
            InputStream stderr = process.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stderr));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("ffmpeg error: " + line);
            }
        } catch (IOException e) {
            System.err.println("Video kaydı başlatılamadı: " + e.getMessage());
        }
    }


    public void startRecording2() {
        String command = "ffmpeg -f avfoundation -i \"1\" -r 30 -s 1280x720 -pix_fmt uyvy422 -t 300 " + videoFilePath;

        try {
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            builder.redirectErrorStream(true);  // Hata ve standart akışı birleştir
            this.process = builder.start();

            // Çıktıyı okuyup logla

            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("ffmpeg output: " + line);
            }
            int exitCode = process.waitFor();  // Process bitene kadar bekle
            if (exitCode != 0) {
                System.err.println("ffmpeg exited with error code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred starting video recording: " + e.getMessage());
        }
    }


    public void stopRecording3() {
        if (this.process != null) {
            this.process.destroy();
            System.out.println("Video recording stopped: " + videoFilePath);
        }
    }
}