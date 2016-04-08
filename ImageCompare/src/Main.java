import java.awt.image.BufferedImage;

/**
 * Created by mike on 4/4/16.
 */

//WARN: This class is only for Test Unit
public class Main {

    public static void main(String[] args){
        String path = "C:\\Users\\Mike\\Desktop\\testSample\\";
        BufferedImage img = ImageUtil.resizeImg(FileUtil.loadImg(path,"star1.jpg"),8,8);
        BufferedImage img2 = ImageUtil.resizeImg(FileUtil.loadImg(path,"star2.jpg"),8,8);
        ImgCompare.getDistance(img,img2);
        //ImgCompare.compareByPath(img,path);
    }
}
