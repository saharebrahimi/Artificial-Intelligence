
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static org.opencv.core.Core.*;
import static org.opencv.core.CvType.CV_8U;
import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.core.Mat.ones;
import static org.opencv.core.Mat.zeros;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.*;

public class OpenCvTest {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Scanner sc = new Scanner(System.in);
        System.out.println("what is  your demand? " + "\n" +
                "a.load and show file" + "\n" +
                "b.blue channle filtering" + "\n" +
                "c.greyscal" + "\n" +
                "d.gaussian filter" + "\n" +
                "e.rotate 90 degree" + "\n" +
                "f.resize" + "\n" +
                "g.detecting edge" + "\n" +
                "h.image segmentation" + "\n" +
                "i.face detection" + "\n" +
                "j.load video and show 5 frames" + "\n" +
                "q.exit");

        String file = "C:/Users/ASUS/Desktop/test.jpg";
        Mat matrix = imread(file);
        char option = 0;
        while (true) {
            option = sc.next().charAt(0);
            if (option == 'q') {
                System.exit(0);
            }
            if (option == 'a') {

                file = "C:/Users/ASUS/Desktop/test.jpg";
                matrix = imread(file);
                imwrite("dest.jpg", matrix);
                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'b') {
                matrix = blueChannel(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'c') {
                matrix = greyscale(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'd') {
                matrix = gaussian(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'e') {
                matrix = rotate(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'f') {
                matrix = resize(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'g') {
                matrix = edgeDetector(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'h') {
                //  matrix = segment(matrix);
                matrix = segment2(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }
            if (option == 'i') {
                matrix = faceDetection(matrix);
                imwrite("dest.jpg", matrix);

                imshow("test", matrix);
                int c = HighGui.waitKey(100);
            }


            if (option == 'j') {
                video();
            }

            // OpenCvTest.displayImage(mat2BufferedImage(matrix));
        }

        //System.out.println("Image Loaded");

    }

    public static Mat blueChannel(Mat matrix) {
        java.util.List<Mat> bgr = new ArrayList<Mat>(3);
        Core.split(matrix, bgr);
        bgr.set(1, zeros(matrix.rows(), matrix.cols(), CV_8UC1));
        bgr.set(2, zeros(matrix.rows(), matrix.cols(), CV_8UC1));
        Core.merge(bgr, matrix);


//        matrix=bgr.get(0);
//        imwrite("blue.png",bgr.get(0)); //blue channel


        return matrix;

    }

    public static Mat greyscale(Mat matrix) {
        Mat mGray = new Mat();
        cvtColor(matrix, mGray, COLOR_BGR2GRAY);
//        int width = mGray.width(), height = mGray.height(), channels = mGray.channels();
//        byte[] source = new byte[width * height * channels];
//        mGray.get(0, 0, source);
        return mGray;

    }

    public static Mat gaussian(Mat matrix) {
        Mat dst = new Mat();
        Imgproc.GaussianBlur(matrix, dst, new Size(45, 45), 0, 0);

        return dst;

    }

    public static Mat rotate(Mat matrix) {
        Mat dst = new Mat();
        Core.rotate(matrix, dst, Core.ROTATE_90_CLOCKWISE);
        return dst;

    }

    public static Mat resize(Mat matrix) {
        Mat dst = new Mat();
        Imgproc.resize(matrix, dst, new Size(matrix.rows() / 2, matrix.cols()), 0, 0, Imgproc.INTER_AREA);
        return dst;

    }

    public static Mat edgeDetector(Mat matrix) {
//        Mat dst = new Mat();
//        cvtColor(matrix, matrix, COLOR_RGB2GRAY, 0);
//        Canny(matrix, dst, 50, 100, 3, false);
//        return dst;
        //   Mat gray = new Mat();
        Mat dst = new Mat();
        //   Imgproc.cvtColor(matrix, gray, Imgproc.COLOR_GRAY2BGR);
        Imgproc.Canny(matrix, dst, 50, 150, 3, false);
        return dst;

    }


    public static Mat segment(Mat matrix) {

        Mat src = matrix.clone();
        byte[] srcData = new byte[(int) (src.total() * src.channels())];
        src.get(0, 0, srcData);
        for (int i = 0; i < src.rows(); i++) {
            for (int j = 0; j < src.cols(); j++) {
                if (srcData[(i * src.cols() + j) * 3] == (byte) 255 && srcData[(i * src.cols() + j) * 3 + 1] == (byte) 255
                        && srcData[(i * src.cols() + j) * 3 + 2] == (byte) 255) {
                    srcData[(i * src.cols() + j) * 3] = 0;
                    srcData[(i * src.cols() + j) * 3 + 1] = 0;
                    srcData[(i * src.cols() + j) * 3 + 2] = 0;
                }
            }
        }
        src.put(0, 0, srcData);
        // Show output image
        imshow("Black Background Image", src);

        // Create a kernel that we will use to sharpen our image
        Mat kernel = new Mat(3, 3, CvType.CV_32F);
        // an approximation of second derivative, a quite strong kernel
        float[] kernelData = new float[(int) (kernel.total() * kernel.channels())];
        kernelData[0] = 1;
        kernelData[1] = 1;
        kernelData[2] = 1;
        kernelData[3] = 1;
        kernelData[4] = -8;
        kernelData[5] = 1;
        kernelData[6] = 1;
        kernelData[7] = 1;
        kernelData[8] = 1;
        kernel.put(0, 0, kernelData);
        // do the laplacian filtering (convert everything deeper than CV_8U
        // because the kernel has some negative values,
        // and we can expect in general to have a Laplacian image with negative values

        Mat imgLaplacian = new Mat();
        Imgproc.filter2D(src, imgLaplacian, CvType.CV_32F, kernel);
        Mat sharp = new Mat();
        src.convertTo(sharp, CvType.CV_32F);
        Mat imgResult = new Mat();
        Core.subtract(sharp, imgLaplacian, imgResult);

        // convert back to 8bits gray scale

        imgResult.convertTo(imgResult, CvType.CV_8UC3);
        imgLaplacian.convertTo(imgLaplacian, CvType.CV_8UC3);
        imshow("New Sharped Image", imgResult);

        Mat bw = new Mat();
        Imgproc.cvtColor(imgResult, bw, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(bw, bw, 40, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        imshow("Binary Image", bw);

        // Perform the distance algorithm
        Mat dist = new Mat();
        Imgproc.distanceTransform(bw, dist, Imgproc.DIST_L2, 3);

        // Normalize the distance image for range = {0.0, 1.0} so we can visualize and threshold it
        Core.normalize(dist, dist, 0.0, 1.0, Core.NORM_MINMAX);
        Mat distDisplayScaled = new Mat();
        Core.multiply(dist, new Scalar(255), distDisplayScaled);
        Mat distDisplay = new Mat();
        distDisplayScaled.convertTo(distDisplay, CvType.CV_8U);
        imshow("Distance Transform Image", distDisplay);

        // Threshold to obtain the peaks for the foreground objects
        Imgproc.threshold(dist, dist, 0.4, 1.0, Imgproc.THRESH_BINARY);

        // Dilate a bit the dist image
        Mat kernel1 = Mat.ones(3, 3, CvType.CV_8U);
        Imgproc.dilate(dist, dist, kernel1);
        Mat distDisplay2 = new Mat();
        dist.convertTo(distDisplay2, CvType.CV_8U);
        Core.multiply(distDisplay2, new Scalar(255), distDisplay2);
        imshow("Peaks", distDisplay2);

        // Create the CV_8U version of the distance image
        Mat dist_8u = new Mat();
        dist.convertTo(dist_8u, CvType.CV_8U);

        // Find total markers
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(dist_8u, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Create the marker image for the watershed algorithm
        Mat markers = Mat.zeros(dist.size(), CvType.CV_32S);

        // Draw the foreground markers
        for (int i = 0; i < contours.size(); i++) {
            Imgproc.drawContours(markers, contours, i, new Scalar(i + 1), -1);
        }
        // Draw the background marker
        Mat markersScaled = new Mat();
        markers.convertTo(markersScaled, CvType.CV_32F);
        Core.normalize(markersScaled, markersScaled, 0.0, 255.0, Core.NORM_MINMAX);
        Imgproc.circle(markersScaled, new Point(5, 5), 3, new Scalar(255, 255, 255), -1);
        Mat markersDisplay = new Mat();
        markersScaled.convertTo(markersDisplay, CvType.CV_8U);
        imshow("Markers", markersDisplay);
        Imgproc.circle(markers, new Point(5, 5), 3, new Scalar(255, 255, 255), -1);

        // Perform the watershed algorithm
        Imgproc.watershed(imgResult, markers);
        Mat mark = Mat.zeros(markers.size(), CvType.CV_8U);
        markers.convertTo(mark, CvType.CV_8UC1);
        Core.bitwise_not(mark, mark);
        imshow("Markers_v2", mark);

        // Generate random colors
        Random rng = new Random(12345);
        List<Scalar> colors = new ArrayList<>(contours.size());
        for (int i = 0; i < contours.size(); i++) {
            int b = rng.nextInt(256);
            int g = rng.nextInt(256);
            int r = rng.nextInt(256);
            colors.add(new Scalar(b, g, r));
        }

        // Create the result image
        Mat dst = Mat.zeros(markers.size(), CvType.CV_8UC3);
        byte[] dstData = new byte[(int) (dst.total() * dst.channels())];
        dst.get(0, 0, dstData);

        // Fill labeled objects with random colors
        int[] markersData = new int[(int) (markers.total() * markers.channels())];
        markers.get(0, 0, markersData);
        for (int i = 0; i < markers.rows(); i++) {
            for (int j = 0; j < markers.cols(); j++) {
                int index = markersData[i * markers.cols() + j];
                if (index > 0 && index <= contours.size()) {
                    dstData[(i * dst.cols() + j) * 3 + 0] = (byte) colors.get(index - 1).val[0];
                    dstData[(i * dst.cols() + j) * 3 + 1] = (byte) colors.get(index - 1).val[1];
                    dstData[(i * dst.cols() + j) * 3 + 2] = (byte) colors.get(index - 1).val[2];
                } else {
                    dstData[(i * dst.cols() + j) * 3 + 0] = 0;
                    dstData[(i * dst.cols() + j) * 3 + 1] = 0;
                    dstData[(i * dst.cols() + j) * 3 + 2] = 0;
                }
            }
        }
        dst.put(0, 0, dstData);
        // Visualize the final image
        imshow("Final Result", dst);
        return dst;

    }


    public static Mat faceDetection(Mat matrix) {
        CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/ASUS/Downloads/opencv/sources/data/haarcascades/haarcascade_frontalface_alt2.xml");
        MatOfRect faceD = new MatOfRect();
        faceDetector.detectMultiScale(matrix, faceD, 1.3);
        for (Rect rect : faceD.toArray()) {
            Imgproc.rectangle(matrix, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(255, 191, 0), 5);
        }
        return matrix;

    }

    public static Mat segment2(Mat img) {
        Mat gray = new Mat();
        Mat opening = new Mat();
        Mat coinsBg = new Mat();
        Mat coinsFg = new Mat();
        Mat distTrans = new Mat();
        Mat unknown = new Mat();
        Mat markers = new Mat();
        cvtColor(img, gray, COLOR_RGBA2GRAY, 0);
        threshold(gray, gray, 0, 255, THRESH_BINARY_INV + THRESH_OTSU);
// get background
        Mat M = Mat.ones(3, 3, CV_8U);
        erode(gray, gray, M);
        dilate(gray, opening, M);
        dilate(opening, coinsBg, M, new Point(-1, -1), 3);
// distance transform
        distanceTransform(opening, distTrans, DIST_L2, 5);
        normalize(distTrans, distTrans, 1, 0, NORM_INF);
// get foreground
        threshold(distTrans, coinsFg, 0.7 * 1, 255, THRESH_BINARY);
        coinsFg.convertTo(coinsFg, CV_8U, 1, 0);
        subtract(coinsBg, coinsFg, unknown);
// get connected components markers
        connectedComponents(coinsFg, markers);

        ///Add one to all labels so that sure background is not 0, but 1
        Core.add(markers, ones(markers.rows(), markers.cols(), markers.type()), markers);


        Core.normalize(distTrans, distTrans, 0.0, 1.0, Core.NORM_MINMAX);
        Mat distDisplayScaled = new Mat();
        Core.multiply(distTrans, new Scalar(255), distDisplayScaled);
        Mat distDisplay = new Mat();
        distDisplayScaled.convertTo(distDisplay, CvType.CV_8U);
        imshow("Distance Transform Image", distDisplay);

        // Threshold to obtain the peaks for the foreground objects
        Imgproc.threshold(distTrans, distTrans, 0.4, 1.0, Imgproc.THRESH_BINARY);

        // Dilate a bit the dist image
        Mat kernel1 = Mat.ones(3, 3, CvType.CV_8U);
        Imgproc.dilate(distTrans, distTrans, kernel1);
        Mat distDisplay2 = new Mat();
        distTrans.convertTo(distDisplay2, CvType.CV_8U);
        Core.multiply(distDisplay2, new Scalar(255), distDisplay2);
        imshow("Peaks", distDisplay2);

        // Create the CV_8U version of the distance image
        Mat dist_8u = new Mat();
        distTrans.convertTo(dist_8u, CvType.CV_8U);

        // Find total markers
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(dist_8u, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Create the marker image for the watershed algorithm
        markers = Mat.zeros(distTrans.size(), CvType.CV_32S);

        // Draw the foreground markers
        for (int i = 0; i < contours.size(); i++) {
            Imgproc.drawContours(markers, contours, i, new Scalar(i + 1), -1);
        }

        // Draw the background marker
        Mat markersScaled = new Mat();
        markers.convertTo(markersScaled, CvType.CV_32F);
        Core.normalize(markersScaled, markersScaled, 0.0, 255.0, Core.NORM_MINMAX);
        Imgproc.circle(markersScaled, new Point(5, 5), 3, new Scalar(255, 255, 255), -1);
        Mat markersDisplay = new Mat();
        markersScaled.convertTo(markersDisplay, CvType.CV_8U);
        imshow("Markers", markersDisplay);
        Imgproc.circle(markers, new Point(5, 5), 3, new Scalar(255, 255, 255), -1);

        // Perform the watershed algorithm
        Imgproc.watershed(img, markers);
        Mat mark = Mat.zeros(markers.size(), CvType.CV_8U);
        markers.convertTo(mark, CvType.CV_8UC1);
        Core.bitwise_not(mark, mark);
        imshow("Markers_v2", mark);

        // Generate random colors
        Random rng = new Random(12345);
        List<Scalar> colors = new ArrayList<>(contours.size());
        for (int i = 0; i < contours.size(); i++) {
            int b = rng.nextInt(256);
            int g = rng.nextInt(256);
            int r = rng.nextInt(256);
            colors.add(new Scalar(b, g, r));
        }

        // Create the result image
        Mat dst = Mat.zeros(markers.size(), CvType.CV_8UC3);
        byte[] dstData = new byte[(int) (dst.total() * dst.channels())];
        dst.get(0, 0, dstData);

        // Fill labeled objects with random colors
        int[] markersData = new int[(int) (markers.total() * markers.channels())];
        markers.get(0, 0, markersData);
        for (int i = 0; i < markers.rows(); i++) {
            for (int j = 0; j < markers.cols(); j++) {
                int index = markersData[i * markers.cols() + j];
                if (index > 0 && index <= contours.size()) {
                    dstData[(i * dst.cols() + j) * 3 + 0] = (byte) colors.get(index - 1).val[0];
                    dstData[(i * dst.cols() + j) * 3 + 1] = (byte) colors.get(index - 1).val[1];
                    dstData[(i * dst.cols() + j) * 3 + 2] = (byte) colors.get(index - 1).val[2];
                } else {
                    dstData[(i * dst.cols() + j) * 3 + 0] = 0;
                    dstData[(i * dst.cols() + j) * 3 + 1] = 0;
                    dstData[(i * dst.cols() + j) * 3 + 2] = 0;
                }
            }
        }
        dst.put(0, 0, dstData);
        // Visualize the final image
        imshow("Final Result", dst);
        return dst;

    }


    public static void video() {
        String filePath = "C:/Users/ASUS/Desktop/test2.avi";
        if (!Paths.get(filePath).toFile().exists()) {
            System.out.println("File " + filePath + " does not exist!");
            return;
        }

        VideoCapture camera = new VideoCapture();
        camera.open(filePath);
        int video_length = (int) camera.get(Videoio.CAP_PROP_FRAME_COUNT);
        int frames_per_second = (int) camera.get(Videoio.CAP_PROP_FPS);
        int frame_number = (int) camera.get(Videoio.CAP_PROP_POS_FRAMES);

        if (!camera.isOpened()) {
            System.out.println("Error! Camera can't be opened!");
            return;
        }
        Mat frame = new Mat();
        while (frame_number < 5) {
            if (camera.read(frame)) {
                System.out.println("Frame Obtained");
                System.out.println("Captured Frame Width " +
                        frame.width() + " Height " + frame.height());
                System.out.println("Video is opened");
                System.out.println("Number of Frames: " + video_length);
                System.out.println(frames_per_second + " Frames per Second");
                System.out.println("Converting Video...");
                Imgcodecs.imwrite("camera" + frame_number + ".jpg", frame);
                System.out.println("OK");
                // showWindow(mat2BufferedImage(frame),frame_number);
                HighGui.imshow("Filter Demo " + frame_number, frame);
                HighGui.waitKey(500);
                frame_number++;
//                BufferedImage bufferedImage = matToBufferedImage(frame);
//                showWindow(bufferedImage);
                //  camera.release();
//

            }

        }


    }

    public static BufferedImage mat2BufferedImage(Mat m) {


        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        // get all the pixels
        m.get(0, 0, b);
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;


    }


    public static void displayImage(Image img2) {
        ImageIcon icon = new ImageIcon(img2);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(img2.getWidth(null) + 50, img2.getHeight(null) + 50);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static BufferedImage matToBufferedImage(Mat frame) {
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    private static void showWindow(BufferedImage img, int number) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight() + 30);
        frame.setTitle("Image captured" + " " + number);
        frame.setVisible(true);
    }


}