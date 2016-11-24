/**
 * Created by Ирек on 24.11.2016.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
public class Main {
    public static void main(String args[]){
        long k = System.currentTimeMillis();
        for(int i = 0; i < 20; i ++) {
         //   new CA(i,k);
            new Digger(i,k);
           // new BSP(i,k);
        }
    }
}
