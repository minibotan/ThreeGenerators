import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ирек on 24.11.2016.
 */
public class Digger {
    int pc = 5;  //chance to change direction
    int pr = 5;  //chance to dig a room
    int x = -1, y = 0; //move direction
    int n = 50;
    int posx = 0, posy = 5;
    boolean map[][];
    public Digger(int k, long t){
        posx = n/2;
        map = new boolean[n][n];
        BufferedImage img = new BufferedImage(n,n, BufferedImage.TYPE_3BYTE_BGR);
        String foldName = "images\\digger\\images" + t;  //folder
        try{
            File d = new File(foldName);
            d.mkdir();
        }  catch (Exception e) {
        }
        int counter = 0;
        while(true) {
            if(Dig()){
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
               return;
            }
            int r = (int) (Math.random() * 100);
            if (r < pc) {
                ChangeDirection();
                pc = 0;
            } else {
                pc += 5;
            }
            r = (int) (Math.random() * 100);
            if (r < pr) {
                CreateRoom();
                pr = 0;
            } else {
                pr += 5;
            }
            counter++;
        }

    }

    private boolean Dig() {
        posx+=x;
        posy+=y;
        if(posx >= n || posx < 0) return true;
        if(posy >= n || posy < 0) return true;
        map[posx][posy] = true;
        return false;
    }

    private void CreateRoom() {
        int w = (int)(Math.random()*4) + 3;
        int h = (int)(Math.random()*4) + 3;
        if(posx+(h+1)/2 >= n || posx-(h)/2 < 0) return;
        if(posy+(w+1)/2 >= n || posy-(w)/2 < 0) return;
        for(int i = -h/2; i<(h+1)/2; i ++){
            for(int j = -w/2; j<(w+1)/2; j ++){
                map[posx+i][posy+j] = true;
            }
        }
    }

    private void ChangeDirection() {
        if(Math.random() > 0.5) {
            x = (Math.random() < 0.5)?(-1):(1);
            y = 0;
        } else {
            y = (Math.random() < 0.5)?(-1):(1);
            x = 0;
        }
    }

}
