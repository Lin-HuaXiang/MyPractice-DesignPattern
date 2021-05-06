package com.example.designpatternsnap.util;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class TScreenCapture2 {

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        // BufferedImage fullScreenImage = robot
        //         .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        BufferedImage fullScreenImage = robot
                .createScreenCapture(new Rectangle(110,95,150,20));

        File fileSave = new File("d:", "temp_z.jpg");
        ImageIO.write(fullScreenImage, "JPEG", fileSave);

        Thread.sleep(1000);

         //如果未将tessdata放在根目录下需要指定绝对路径
         ITesseract instance = new Tesseract();

         String path=System.getProperty("user.dir");
         System.out.println(path);

         //设置训练库的位置
         instance.setDatapath(path + "/design-pattern-snap/tessdata");
 
         //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
         // chi_sim ：简体中文， eng    根据需求选择语言库
         instance.setLanguage("eng");
 
         // 指定识别图片
         File img = new File(path + "/temp_z.jpg");
         long startTime = System.currentTimeMillis();
         String result = instance.doOCR(img);
 
         // 输出识别结果
         long endTime = System.currentTimeMillis();
         System.out.println("识别结果: \n" + result + "\n 耗时：" + (endTime - startTime) + "ms");
    }

}
