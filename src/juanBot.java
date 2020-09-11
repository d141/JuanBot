import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.*;


public class juanBot {


    public static String classify(Color c){
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        float [] hsv = new float[3];
                RGBtoHSB(r,g,b,hsv);

        float h = hsv[0];
        float s = hsv[1];
        float v = hsv[2];
        System.out.println(h);
        if(v>.87)return"white";
        if(v<.70&&v>=.20)return"black";
        if(v<.30)return"brown";
        if(s<.02)return"gray";

        if(h<.09)return "red";

        if(h<.1)return "yellow";
        if(h<.216)return "green";
        if(h<.313)return "cyan";
        if(h<.40)return "blue";
       // System.out.println("blue");
        if(h<.916)return "magenta";
        return "red";

    }

    public static Color changeColor(String string){
//System.out.println(string);
        switch(string){
            case "black":return black;
            case "brown": return darkGray;
            case "white":return white;
            case "gray":return gray;
            case "red":return red;
            case "yellow":return yellow;
            case "green":return green;
            case "cyan": return cyan;
            case "blue": return blue;
            case "magenta": return magenta;
            default: return black;
        }
    }

    public static void main (String[] args){

        JOptionPane.showMessageDialog(null,"Feed me a bitmap.");
        File file = null;
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }

        BufferedImage image = null;
        try{
           image = ImageIO.read(file);
        } catch (IOException e) {
             e.printStackTrace();
        }

        int height = image.getHeight();
        int width = image.getWidth();

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Color current = new Color(image.getRGB(i,j));
                image.setRGB(i,j,changeColor(classify(current)).getRGB());
            }
        }

        String fullFileName = "/Users/davevananda/Desktop/Juan.bmp";
        File outputfile = new File(fullFileName);
        try {
            ImageIO.write(image, "bmp", outputfile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}

