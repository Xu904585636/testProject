//package com.kingleadsw.ysm.utils;
//
///**
// * @Auther: zhoujie
// * @Date: 2018/10/30 16:08
// * @Description:  the File
// */
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.Hashtable;
//
//
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageConfig;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
//import lombok.extern.log4j.Log4j2;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ClassUtils;
//
//import javax.imageio.ImageIO;
//
//@Log4j2
//@Component
//public class Files
//{
//
//   // @Autowired
//   // IFileStorage fileStorage;
//
//    private static String OS_NAME = System.getProperty("os.name").toLowerCase();
//
//    private static int onColor = 0xFF000000;     //前景色
//    private static int offColor = 0xFFFFFFFF;    //背景色
//    private static int margin = 1;
//
//    private  static  String linux="/root/targets/";
//
//    private  static  String win="D:/targets/";
//
//    /**
//     *  调用此方法必须异步处理
//     * 获取指定视频的帧并保存为图片上传到oss,返回url
//     * @param videofile  源视频文件路径
//     * @throws Exception
//     * @return  oss url
//     */
//    public   String fetchFrame(String videofile) throws Exception {
//
//        String framefile=null;
//
//        if(OS_NAME.toLowerCase().startsWith("win")){
//            framefile =win+ Sequences.get32UUID().substring(0,6)+".jpg";
//        }else{
//            framefile = linux+ Sequences.get32UUID().substring(0,6)+".jpg";
//        }
//
//        File targetFile = new File(framefile);
//
//        if (!targetFile.exists()) {
//            targetFile.getParentFile().mkdirs();
//            targetFile.createNewFile();
//        }
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
//        ff.start();
//        String rotate =ff.getVideoMetadata("rotate");
//        int lenght = ff.getLengthInFrames();
//
//        int i = 0;
//        Frame frame = null;
//        while (i < lenght) {
//            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
//            frame = ff.grabFrame();
//            if ((i > 5) && (frame.image != null)) {
//                break;
//            }
//            i++;
//        }
//
//        String imgSuffix = "jpg";
//        if(framefile.indexOf('.') != -1){
//            String[] arr = framefile.split("\\.");
//            if(arr.length>=2){
//                imgSuffix = arr[1];
//            }
//        }
//        //如果截取的第一帧 带有旋转角度， 那么统一将他竖屏
//        if(null !=rotate &&rotate.length() > 1) {
//            IplImage src = null;
//            OpenCVFrameConverter.ToIplImage converter =new OpenCVFrameConverter.ToIplImage();
//
//            src =converter.convert(frame);
//
//            frame =converter.convert(rotate(src, Integer.valueOf(rotate)));
//
//        }
//
//
//        Java2DFrameConverter converter =new Java2DFrameConverter();
//        BufferedImage srcBi =converter.getBufferedImage(frame);
//        int owidth = srcBi.getWidth();
//        int oheight = srcBi.getHeight();
//        // 对截取的帧进行等比例缩放
//        int width = 800;
//        int height = (int) (((double) width / owidth) * oheight);
//        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        bi.getGraphics().drawImage(srcBi.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null);
//        try {
//            ImageIO.write(bi, imgSuffix, targetFile);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        ff.stop();
//
///*
//        OssResult ossResult = fileStorage.upload(targetFile);
//
//        return  ossResult.getUri();*/
//
//        return  null;
//
//    }
//
//    public static IplImage rotate(IplImage src, int angle) {
//
//        IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
//
//        opencv_core.cvTranspose(src, img);
//
//        opencv_core.cvFlip(img, img, angle);
//        return img;
//    }
//
//
//    /**
//     *  合成证书图片
//     *            原图片保存路径
//     * @param newPath
//     *            新图片保存的名字
//     * @param width
//     *            定义生成图片宽度
//     * @param height   String phone, String levelName, String no, String validTime,
//     *            定义生成图片高度
//     * @return
//     * @throws IOException
//     */
//    public String  createImage(String name,String phone, String levelName, String no, String validTime, String newPath, int width, int height) throws IOException {
//
//        InputStream stream = getClass().getClassLoader().getResourceAsStream("zhengshu.png");
//        Image image = ImageIO.read(stream);
//
//        String filePath=null;
//        if(OS_NAME.toLowerCase().startsWith("win")){
//            filePath =win+ newPath+".png";
//
//        }else {
//            filePath = linux + newPath + ".png";
//        }
//
//        File file = new File(filePath);
//        if (!file.exists()) {
//            file.getParentFile().mkdirs();
//            file.createNewFile();
//        }
//            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2 = bi.createGraphics();
//            g2.setBackground(Color.WHITE);
//            g2.clearRect(0, 0, width, height);
//            g2.drawImage(image, 0, 0, width , height, null);
//            /** 设置生成图片的文字样式 * */
//            Font font = new Font("宋体", Font.BOLD, 15);
//            g2.setFont(font);
//            g2.setPaint(Color.BLACK);
//
//
//            /** 防止生成的文字带有锯齿 * */
//            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//
//            /** 在图片上生成文字 * */
//            g2.drawString(name, 270, 325);
//            g2.drawString(phone,240,405);
//            g2.drawString(levelName,280,500);
//            g2.drawString(validTime,257,577);
//
//            g2.drawString(no,435,98);
//
//
//            ImageIO.write(bi, "png", file);
//
//            return  filePath;
//    }
//
//    /**
//     *  合并二张图片
//     * @param imgPath
//     * @param pmgPath2
//     */
//     public String  mergeImages(String imgPath, String pmgPath2) throws IOException {
//
//
//             BufferedImage imageLocal = ImageIO.read(new File(imgPath));
//             BufferedImage imageCode = ImageIO.read(new File(pmgPath2));
//
//             Graphics2D g = imageLocal.createGraphics();
//             g.drawImage(imageCode, 110, imageLocal.getHeight() - 230, 110, 110, null);
//             g.dispose();
//
//         String  filePath= null ;
//         if(OS_NAME.toLowerCase().startsWith("win")){
//             filePath =win+Dates.now()+".png";
//         }else{
//             filePath = linux+Dates.now()+".png";
//         }
//
//         // 获取新文件的地址
//             File outputfile = new File(filePath);
//             // 生成新的合成过的用户二维码并写入新图片
//             ImageIO.write(imageLocal, "png", outputfile);
//             /*
//             OssResult ossResult =  fileStorage.upload(outputfile);
//             ossResult.getUri();*/
//          return  null;
//    }
//
//    /**
//     *  生成二维码
//     * @param txt 文本或url
//     * @param imgName   名称
//     * @param suffix
//     */
//    public String generateQRImage(String txt, String imgName, String suffix) throws IOException, WriterException {
//
//        String filepath= null;
//
//        if(OS_NAME.toLowerCase().startsWith("win")){
//            filepath =win;
//        }else{
//            filepath = linux;
//        }
//
//        File filePath = new File(filepath);
//
//        if(!filePath.exists()){
//            filePath.getParentFile().mkdirs();
//            filePath.createNewFile();
//        }
//
//        File imageFile = new File(filepath,imgName);
//        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
//        // 指定纠错等级
//        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//        // 指定编码格式
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        hints.put(EncodeHintType.MARGIN, margin);   //设置白边
//
//        MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(txt, BarcodeFormat.QR_CODE, 130, 130, hints);
//        MatrixToImageWriter.writeToPath(bitMatrix, suffix, imageFile.toPath(), config);
//
//        return  filepath;
//    }
//
//
//    private Files()
//    {
//    }
//
//
//    public static InputStream getInput(String filePath)
//    {
//        try
//        {
//            return new ClassPathResource(filePath).getInputStream();
//        }
//        catch (IOException e)
//        {
//            log.error("Failed to getInput", e);
//            throw new NotfoundException(Integer.valueOf(500007));
//        }
//    }
//    public static boolean isWindows()
//    {
//        return OS_NAME.indexOf("windows") >= 0;
//    }
//
//    public static String getFilePathInClassPath(String fileName)
//    {
//        if (isWindows()) {
//            return ClassUtils.getDefaultClassLoader().getResource("").getPath() + fileName;
//        }
//        return ClassUtils.getDefaultClassLoader().getResource("").getPath() + "conf/" + fileName;
//    }
//
//
//}
