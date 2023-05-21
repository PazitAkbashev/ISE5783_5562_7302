package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Test for ImageWriter class
 *
 * @author Pazit and Leah
 */
class ImageWriterTests {

    Color indigo = new Color(75, 0, 130);
    Color silver = new Color(192, 192, 192);

    /**
     * Test method for {@link ImageWriter#writeToImage()}.
     */
    @Test
    void writePixelTest() {
        ImageWriter imageWriter = new ImageWriter("Grid and background", 800, 500);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                // 800 / 16 = 50
                if (j % 50 == 0) {
                    imageWriter.writePixel(j, i, indigo);
                    // 500 / 10 = 50
                } else if (i % 50 == 0) {
                    imageWriter.writePixel(j, i, indigo);
                } else {
                    imageWriter.writePixel(j, i, silver);
                }
            }
        }
        imageWriter.writeToImage();
    }
}
