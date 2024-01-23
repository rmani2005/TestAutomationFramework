package com.clover.solution.ui.util.extentreports;


import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import com.clover.solution.ui.configuration.ConfigProvider;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class MyScreenRecorder extends ScreenRecorder {
    public static ScreenRecorder screenRecorder;
    public String name;

    public MyScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                            Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;

    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        return new File(movieFolder,
                name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));

    }

    public static void startRecording(String methodName) throws Exception {
    	
    	String userDir=System.getProperty("user.dir");
    	//String project=ConfigProvider.getProject().toLowerCase();
    	String project="searchengine";
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date myDate = new Date();
        String parsedDate = formatter.format(myDate);
        
        String path= userDir + "//Report//" + project+ "//" + parsedDate;
        File theDir = new File(path);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        
        formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        myDate = new Date();
        parsedDate = formatter.format(myDate);
    	
        String finalPathandfile=path + "//" + project;
    	
    	
        File file = new File(finalPathandfile);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new MyScreenRecorder(gc, captureSize,
                new Format(FormatKeys.MediaTypeKey, MediaType.FILE, FormatKeys.MimeTypeKey, FormatKeys.MIME_AVI),
                new Format(FormatKeys.MediaTypeKey, MediaType.VIDEO, FormatKeys.EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FormatKeys.FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, FormatKeys.KeyFrameIntervalKey, 15 * 60),
                new Format(FormatKeys.MediaTypeKey, MediaType.VIDEO, FormatKeys.EncodingKey, "black", FormatKeys.FrameRateKey, Rational.valueOf(30)),
                null, file, methodName);

        screenRecorder.start();

    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
    }
}