import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static int width;
    public static int height;
    private static boolean hasAlphaChannel;
    private static int pixelLength;
    private static byte[] pixels;
    private static ArrayList<Float> arraylist;

    public static void main(String[] args) throws IOException {
        arraylist = new ArrayList();
        BufferedImage imgBuffer = ImageIO.read(new File("/home/user/image.jpg"));

        hasAlphaChannel = imgBuffer.getAlphaRaster() != null;
        pixelLength = 3;
        if (hasAlphaChannel)
            pixelLength = 4;
        pixels = (byte[])imgBuffer.getRaster().getDataElements(0, 0, imgBuffer.getWidth(), imgBuffer.getHeight(), null);

        for(int y=0;y<imgBuffer.getHeight();y++)
        {
            for(int x=0;x<imgBuffer.getWidth();x++)
            {
                int v = imgBuffer.getRGB(x,y);

                Color color = new Color(v);
                if(color.getRGB()>Color.BLACK.getRGB()&&color.getRGB()<Color.DARK_GRAY.getRGB())
                {
                    imgBuffer.setRGB(x,y,Color.GREEN.getRGB());
                    arraylist.add((float)y);
                    arraylist.add((float)x);
                }
            }
        }
        try {
            File outputfile = new File("/home/user/saved.jpg");
            ImageIO.write(imgBuffer, "jpg", outputfile);
            writeToFile();
        } catch (IOException e) {
            // handle exception
        }
    }


    static short[] getRGB(int x, int y) {
        int pos = (y * pixelLength * width) + (x * pixelLength);
        short rgb[] = new short[4];
        if (hasAlphaChannel)
            rgb[3] = (short) (pixels[pos++] & 0xFF); // Alpha
        rgb[2] = (short) (pixels[pos++] & 0xFF); // Blue
        rgb[1] = (short) (pixels[pos++] & 0xFF); // Green
        rgb[0] = (short) (pixels[pos++] & 0xFF); // Red
        return rgb;
    }

    private static void writeToFile() {
        try {
            File file = new File("/home/user/test.obj");

            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.append("START");
            writer.write("\r\n");
            for(int i=1;i<=arraylist.size();i+=2)
            {
                arraylist.get(i);
                //if(i%2==0)
                //{
                //   writer.write("x");
                //}
                //else
                //{
                //    writer.write("y");
                //}
                //top right
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");
                //top left
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");
                //bottom left
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");
                //bottom right
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                //bottom
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                //Front
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                //back
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                //left
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                //right
                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(1.0));
                writer.write("\r\n");

                writer.write("v ");
                writer.write(String.valueOf(arraylist.get(i)+1));
                writer.write(" ");
                writer.write(String.valueOf(arraylist.get(i+1)+1));
                writer.write(" ");
                writer.write(String.valueOf(-1.0));
                writer.write("\r\n");
            }
            writer.append("END");
            writer.write("\r\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }
}