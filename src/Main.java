import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Main {
    public static int width;
    public static int height;
    private static boolean hasAlphaChannel;
    private static int pixelLength;
    private static byte[] pixels;

    public static void main(String[] args) throws IOException {
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
                }
            }
        }
        try {
            File outputfile = new File("/home/user/saved.jpg");
            ImageIO.write(imgBuffer, "jpg", outputfile);
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
}