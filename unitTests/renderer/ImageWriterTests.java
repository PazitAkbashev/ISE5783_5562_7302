package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTests {


     Color indigo = new Color(75, 0, 130);
     Color silver = new Color(192, 192, 192);

    @Test
    void writePixel(){
       ImageWriter imageWriter = new ImageWriter("firs image : grid and background", 800, 500);
       for(int i = 0; i <imageWriter.getNx(); i++){
           for(int j = 0; j < imageWriter.getNy(); j++){
               // 800 / 16 = 50
               if(i % 50 == 0){
                   imageWriter.writePixel(i, j, indigo);
                   // 500 / 10 = 50
               } else if (j % 50 == 0) {
                   imageWriter.writePixel(i, j, indigo);
               }
               else{
                   imageWriter.writePixel(i, j, silver);
               }
           }
        }
       imageWriter.writeToImage();
   }
}
