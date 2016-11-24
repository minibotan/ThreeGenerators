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


public class CA {
    int n = 100;
    int m = 1;
    int t = ((m+m+1)*(m+m+1))/2;
    boolean map[][];
    boolean tmap[][];
    public CA(int k, long h){
        map = new boolean[n][n];
        tmap = new boolean[n][n];
        for(int i = 0; i<n; i ++){
            for (int j = 0; j < n; j++) {
                map[i][j] = (Math.random()>0.5);
            }
        }
        BufferedImage img = new BufferedImage(n,n, BufferedImage.TYPE_3BYTE_BGR);
        String foldName = "images\\CA\\images" + h;  //folder
        try{
            File d = new File(foldName);
            d.mkdir();
        }  catch (Exception e) {
        }
        int counter = 0;
        counter = 0;
        while(counter < 11 && (!map.equals(tmap))){
            String fileName = foldName+"\\image_"+k+"_"+counter+".png";
            for(int i = 0; i<n; i ++){
                for (int j = 0; j < n; j++) {
                    img.setRGB(i,j,(map[i][j])?((200 << 16) | (200 << 8) | 200):(0));
                }
            }
            try {
                File f = new File(fileName);
                ImageIO.write(img, "png", f);
            } catch (IOException e) {
            }
            counter++;

            tmap = map.clone();
            for(int i = 0; i<n; i ++){
                for (int j = 0; j < n; j++) {
                    map[i][j] = count(i,j);
                }
            }

        }



    }

    boolean count(int k, int l){
        int ans = 0;
        for(int i = -m; i <= m; i++){
            if(i+k < 0 || i+k >= n) continue;
            for(int j = -m; j<=m; j++) {
                if(j+l < 0 || j+l >= n) continue;
                if(tmap[i+k][j+l]) ans++;
            }
        }
        return ans > t;
    }
}
