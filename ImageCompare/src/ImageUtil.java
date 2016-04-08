import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mike on 4/4/16.
 */
public class ImageUtil {

    public static BufferedImage resizeImg(BufferedImage img,int width,int height){

        int inputWidth = img.getWidth();
        int inputHeight = img.getHeight();

        System.out.println("\nLoad Image:"+inputWidth+" x "+inputHeight);
        System.out.println("Resizing Image to:"+width+" x "+height);

        int xJump = inputWidth/width;
        int yJump = inputHeight/height;


        BufferedImage outputImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        int counter  = 0;
        for (int i=0;i<inputHeight;i = i+yJump){
            for (int j=0;j<inputWidth; j=j+xJump){
                int rgb = img.getRGB(j,i);

                if (counter!=width*height){
                    outputImg.setRGB(counter%width,counter/height,rgb);
                    counter++;
                }

            }
        }

        return outputImg;
//        File file = new File("/Users/Mike/Desktop/8x8.jpg");
//
//        try {
//            ImageIO.write(outputImg,"jpg",file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private static int getGray(int rgb){
        int a = rgb & 0xff000000;//将最高位（24-31）的信息（alpha通道）存储到a变量
        int r = (rgb >> 16) & 0xff;//取出次高位（16-23）红色分量的信息
        int g = (rgb >> 8) & 0xff;//取出中位（8-15）绿色分量的信息
        int b = rgb & 0xff;//取出低位（0-7）蓝色分量的信息
        rgb = (r * 77 + g * 151 + b * 28) >> 8;    // NTSC luma，算出灰度值
        return a | (rgb << 16) | (rgb << 8) | rgb;//将灰度值送入各个颜色分

//        int _red = (rgb >> 16) & 0xFF;
//        int _green = (rgb >> 8) & 0xFF;
//        int _blue = (rgb) & 0xFF;
//        return (int) (0.3 * _red + 0.59 * _green + 0.11 * _blue);

    }

    private static char binaryToHex(int binary) {
        char ch = ' ';
        switch (binary)
        {
            case 0:
                ch = '0';
                break;
            case 1:
                ch = '1';
                break;
            case 2:
                ch = '2';
                break;
            case 3:
                ch = '3';
                break;
            case 4:
                ch = '4';
                break;
            case 5:
                ch = '5';
                break;
            case 6:
                ch = '6';
                break;
            case 7:
                ch = '7';
                break;
            case 8:
                ch = '8';
                break;
            case 9:
                ch = '9';
                break;
            case 10:
                ch = 'a';
                break;
            case 11:
                ch = 'b';
                break;
            case 12:
                ch = 'c';
                break;
            case 13:
                ch = 'd';
                break;
            case 14:
                ch = 'e';
                break;
            case 15:
                ch = 'f';
                break;
            default:
                ch = ' ';
        }
        return ch;
    }

    private static ArrayList getGrayList(BufferedImage img){
        ArrayList list = new ArrayList<Integer>();

        for (int i=0;i<img.getWidth();i++){
            for (int j=0;j<img.getHeight();j++){
                int rgb = img.getRGB(i,j);
                int gray = getGray(rgb);
                list.add(gray);
            }
        }

        return list;
    }

    private static int getGrayAvg(ArrayList list){

        int totalGray = 0;

        for (int i=0;i<list.size();i++){
            totalGray += (int)list.get(i);
        }

        int avgGray = totalGray/list.size();

        return avgGray;
    }

    public static String generateHash(BufferedImage img){

        String hashVal = "";

        ArrayList list = getGrayList(img);

        int avgGray = getGrayAvg(list);

        for (int i=0;i<list.size();i++){
            if ((int)list.get(i)>=avgGray){
                hashVal += "1";
            }else {
                hashVal += "0";
            }
        }

        //Binary to Hex
//        StringBuffer hashCode = new StringBuffer();
//        for (int i = 0; i < hashVal.length(); i+= 4) {
//            int result =Integer.parseInt( String.valueOf(hashVal.charAt(i)) ) * (int) Math.pow(2, 3) + Integer.parseInt( String.valueOf(hashVal.charAt(i+1)) ) * (int) Math.pow(2, 2) + Integer.parseInt( String.valueOf(hashVal.charAt(i+2)) ) * (int) Math.pow(2, 1) + Integer.parseInt( String.valueOf(hashVal.charAt(i+3)) );
//            hashCode.append(binaryToHex(result));
//        }
//
//
//        System.out.println("Hash:"+hashCode);
//        return hashCode.toString();

        System.out.println("Hash:"+hashVal);
        return hashVal;
    }



}
