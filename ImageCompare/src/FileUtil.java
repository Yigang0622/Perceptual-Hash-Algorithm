import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by mike on 4/4/16.
 */
public class FileUtil {

    public static BufferedImage loadImg(String path,String fileName){

        File imgFile = new File(path+fileName);
        try {
            return ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage loadImg(String filePath){

        File imgFile = new File(filePath);
        try {
            return ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList loadImgFormPath(String path){
        ArrayList list = new ArrayList();
        File file=new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
              //  System.out.println("File: " + tempList[i].getName());
                list.add(loadImg(tempList[i].getPath()));
                list.add(tempList[i].getName());
            }

        }
        return list;
    }

    public static void main(String[] args){
        loadImgFormPath("C:\\Users\\Mike\\Desktop\\testSample\\");
    }

}
