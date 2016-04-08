import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mike on 4/4/16.
 */
public class ImgCompare {

    public static int getDistance(BufferedImage img1, BufferedImage img2){
        String hash1 = ImageUtil.generateHash(img1);
        String hash2 = ImageUtil.generateHash(img2);

        int distance = 0;

        for (int i=0 ; i<hash1.length();i++){
            if (hash1.charAt(i) != hash2.charAt(i)){
                distance++;
            }
        }

        System.out.println("Distance: "+distance);

        double similarity = (64-distance)/64.0;
        System.out.println("Similarity: "+similarity*100+"%");
        return distance;
    }

    public static void compareByPath(BufferedImage img,String path){
        String hash = ImageUtil.generateHash(img);

        ArrayList imgList = FileUtil.loadImgFormPath(path);

        for (int i=0;i<imgList.size();i=i+2 ){

            BufferedImage img2 = ImageUtil.resizeImg(FileUtil.loadImg(path, (String) imgList.get(i+1)),8,8);
            String hash2 = ImageUtil.generateHash(img2);

            int distance = 0;
            for (int j=0 ; j<hash.length();j++){
                if (hash.charAt(j) != hash2.charAt(j)){
                    distance++;
                }
            }

            System.out.println("Comparing with file:"+imgList.get(i+1));
            double similarity = (64-distance)/64.0;
            System.out.println("Similarity: "+similarity*100+"%");

            if (distance<13){
                System.out.println("File:"+imgList.get(i+1)+" matches!");
            }

        }





    }

}
