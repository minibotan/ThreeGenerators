import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ирек on 24.11.2016.
 */
public class BSP {

    class quad{
        int maxx = 0, minx = 0;
        int maxy = 0, miny = 0;
        public quad(int x0, int x1, int y0, int y1) {
            maxx = x1;
            minx = x0;
            maxy = y1;
            miny = y0;
        }
        boolean good(){
            return  (maxx - minx > min && maxy - miny > min);
        }

        void dig(){
            maxx -= Math.random()*2+1;
            minx += Math.random()*2+1;
            maxy -= Math.random()*2+1;
            miny += Math.random()*2+1;
            for(int i = minx; i < maxx; i ++){
                for(int j = miny; j < maxy; j++) {
                    map[i][j] = true;
                }
            }
        }
    }

    boolean map[][];
    int n = 100;
    int min = 20;
    BufferedImage img;
    String foldName;
    int k;
    String counter = "";
    public BSP(int k, long t){
        this.k = k;
        img = new BufferedImage(n,n, BufferedImage.TYPE_3BYTE_BGR);
        foldName = "images\\BSP\\images" + t;  //folder
        try{
            File d = new File(foldName);
            d.mkdir();
        }  catch (Exception e) {
        }
        map = new boolean[n][n];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j++) {
                map[i][j] = false;
            }
        }
        counter+= "0";

        tree(new quad(0,n,0,n));


        String fileName = foldName+"\\image_"+k+".png";
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
    }


    void tree(quad parent){
        String c = counter;
        double d = Math.random();
        boolean r;
        quad one, two;
        if(Math.random() > 0.5) {
            r = true;
            d = (d*(parent.maxy-parent.miny))+parent.miny;
            one = new quad(parent.minx, parent.maxx, parent.miny, (int)d);
            two = new quad(parent.minx, parent.maxx, (int)d, parent.maxy);
        } else {
            r = false;
            d = (d*(parent.maxx-parent.minx))+parent.minx;
            one = new quad(parent.minx, (int)d, parent.miny, parent.maxy);
            two = new quad((int)d, parent.maxx, parent.miny, parent.maxy);
        }
        if(one.good()){ tree(one); counter = c + "1";} else one.dig();
        if(two.good()){ tree(two); counter = c + "2";} else two.dig();
        makeway(one, two, (int)d, r);

    }

    private void makeway(quad one, quad two, int d, boolean r) {
        int mx, mn;
        if(r){
            mx = Math.min(one.maxx,two.maxx);
            mn = Math.max(one.minx,two.minx);
        } else {
            mx = Math.min(one.maxy,two.maxy);
            mn = Math.max(one.miny,two.miny);
        }
        int j = d;
        int i = (int)(Math.random()*(mx-mn))+mn;
        while (dig(i,j,r)) j++;
        j = d-1;
        while (dig(i,j,r)) j--;
    }

    private boolean dig(int i, int j, boolean r){
        if(i >=n || j>= n || i<0 || j < 0) return false;
        if(r) {
            if(map[i][j]) return false;
            else map[i][j] = true;
        } else {
            if(map[j][i]) return false;
            else map[j][i] = true;
        }
        return true;
    }

}
