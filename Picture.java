import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
  ///////////////////// constructors //////////////////////////////////

  /**
   * Constructor that takes no arguments
   */
  public Picture() {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor
     */
    super();
  }

  /**
   * Constructor that takes a file name and creates the picture
   *
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName) {
    // let the parent class handle this fileName
    super(fileName);
  }

  /**
   * Constructor that takes the width and height
   *
   * @param height the height of the desired picture
   * @param width  the width of the desired picture
   */
  public Picture(int height, int width) {
    // let the parent class handle this width and height
    super(width, height);
  }

  /**
   * Constructor that takes a picture and creates a
   * copy of that picture
   *
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture) {
    // let the parent class do the copy
    super(copyPicture);
  }

  /**
   * Constructor that takes a buffered image
   *
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image) {
    super(image);
  }

  ////////////////////// methods ///////////////////////////////////////

  /**
   * Method to return a string with information about this picture.
   *
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString() {
    String output = "Picture, filename " + getFileName() +
            " height " + getHeight()
            + " width " + getWidth();
    return output;

  }

  /**
   * Method to set the blue to 0
   */
  public void zeroBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setBlue(0);
      }
    }
  }

  /**
   * Method to set the non-blue values to 0
   */
  public void keepOnlyBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }

  /**
   * Method to negate all pixels
   */
  public void negate() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        int r = pixelObj.getRed();
        int g = pixelObj.getGreen();
        int b = pixelObj.getBlue();
        pixelObj.setColor(new Color(255 - r, 255 - g, 255 - b));
      }
    }
  }

  /**
   * Method to make picture grayscale
   */
  public void grayscale() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        int avg = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen()) / 3;
        pixelObj.setColor(new Color(avg, avg, avg));
      }
    }
  }

  /**
   * Method to create a watermark
   */
  public void watermark() {
    Pixel[][] pixels = this.getPixels2D();
    boolean dark = false;
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        if (j % 40 == 0) dark = !dark;

        int r = pixels[i][j].getRed();
        int g = pixels[i][j].getGreen();
        int b = pixels[i][j].getBlue();

        if (dark) {
          r += 15;
          if (r > 255) r = 255;
          g += 15;
          if (g > 255) g = 255;
          b += 15;
          if (b > 255) b = 255;
        } else {
          r -= 15;
          if (r < 0) r = 0;
          g -= 15;
          if (g < 0) g = 0;
          b -= 15;
          if (b < 0) b = 0;
        }

        pixels[i][j].setColor(new Color(r, g, b));
      }

      if (i % 40 == 0) dark = !dark;
    }
  }

  /**
   * To pixelate by dividing area into size x size.
   *
   * @param size Side length of square area to pixelate.
   */
  public void pixelate(int size) {
    Pixel[][] pixels = this.getPixels2D();
    int r, g, b;
    int count = 0;
    for (int l = 0; l < pixels[0].length; l += size) {
      for (int i = 0; i < pixels.length; i += size) {
        r = g = b = 0;
        for (int k = i; k < i + size; k++) {
          for (int j = l; j < l + size; j++) {
            if (k < pixels.length && j < pixels[0].length) {
              Pixel pixel = pixels[k][j];
              r += pixel.getRed();
              g += pixel.getGreen();
              b += pixel.getBlue();
              count++;
            }
          }
        }

        r /= count;
        g /= count;
        b /= count;
        count = 0;

        for (int k = i; k < i + size; k++) {
          for (int j = l; j < l + size; j++) {
            if (k < pixels.length && j < pixels[0].length)
              pixels[k][j].setColor(new Color(r, g, b));
          }
        }
      }
    }
  }

  /**
   * Method that blurs the picture
   *
   * @param size Blur size, greater is more blur
   * @return Blurred picture
   */
  public Picture blur(int size) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int r, g, b;
        r = g = b = 0;
        int count = 0;
        for (int k = j; k < j + (size / 2); k++) {
          if (k < pixels[0].length) {
            for (int l = i; l < i + (size / 2); l++) {
              if (l < pixels.length) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
            for (int l = i - 1; l > i - (size / 2); l--) {
              if (l > 0) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
          }
        }
        for (int k = j - 1; k > j - (size / 2); k--) {
          if (k > 0) {
            for (int l = i; l < i + (size / 2); l++) {
              if (l < pixels.length) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
            for (int l = i - 1; l > i - (size / 2); l--) {
              if (l > 0) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
          }
        }

        r /= count;
        g /= count;
        b /= count;
        resultPixels[i][j].setColor(new Color(r, g, b));
      }
    }

    return result;
  }

  /**
   * Method that enhances a picture by getting average Color around
   * a pixel then applies the following formula:
   * <p>
   * pixelColor <- 2 * currentValue - averageValue
   * <p>
   * size is the area to sample for blur.
   *
   * @param size Larger means more area to average around pixel
   *             and longer compute time.
   * @return enhanced picture
   */
  public Picture enhance(int size) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int r, g, b;
        r = g = b = 0;
        int count = 0;
        for (int k = j; k < j + (size / 2); k++) {
          if (k < pixels[0].length) {
            for (int l = i; l < i + (size / 2); l++) {
              if (l < pixels.length) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
            for (int l = i - 1; l > i - (size / 2); l--) {
              if (l > 0) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
          }
        }
        for (int k = j - 1; k > j - (size / 2); k--) {
          if (k > 0) {
            for (int l = i; l < i + (size / 2); l++) {
              if (l < pixels.length) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
            for (int l = i - 1; l > i - (size / 2); l--) {
              if (l > 0) {
                r += pixels[l][k].getRed();
                g += pixels[l][k].getGreen();
                b += pixels[l][k].getBlue();
                count++;
              }
            }
          }
        }

        r /= count;
        g /= count;
        b /= count;
        int newR, newG, newB;
        Pixel pixel = pixels[i][j];
        newR = 2 * pixel.getRed() - r;
        if (newR > 255) newR = 255;
        if (newR < 0) newR = 0;
        newG = 2 * pixel.getGreen() - g;
        if (newG > 255) newG = 255;
        if (newG < 0) newG = 0;
        newB = 2 * pixel.getBlue() - b;
        if (newB > 255) newB = 255;
        if (newB < 0) newG = 0;

        //System.out.println(newR + " " + newG + " " + newB);

        resultPixels[i][j].setColor(new Color(newR, newG, newB));
      }
    }

    return result;
  }

  public Picture swapLeftRight() {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    for(int i = 0; i < pixels.length; i++) {
      int index = 0;
      for(int j = pixels[0].length / 2; j < pixels[0].length; j++) {
        resultPixels[i][index].setColor(pixels[i][j].getColor());
        index++;
      }
      for(int j = 0; j < pixels[0].length / 2; j++) {
        resultPixels[i][index].setColor(pixels[i][j].getColor());
        index++;
      }
    }

    return result;
  }

  /** Method that mirrors the picture around a
   * vertical mirror in the center of the picture
   * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }

  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();

    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {

        leftPixel = pixels[row][col];
        rightPixel = pixels[row]
                [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }

  /** copy from the passed fromPic to the
   * specified startRow and startCol in the
   * current picture
   * @param fromPic the picture to copy from
   * @param startRow the start row to copy to
   * @param startCol the start col to copy to
   */
  public void copy(Picture fromPic,
                   int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow;
         fromRow < fromPixels.length &&
                 toRow < toPixels.length;
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol;
           fromCol < fromPixels[0].length &&
                   toCol < toPixels[0].length;
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }


  /** Method to show large changes in color
   * @param edgeDist the distance for finding edges
   */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0;
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) >
                edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }


  /* Main method for testing - each class in Java can have a main
   * method
   */
  public static void main(String[] args)
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }

} // this } is the end of class Picture, put all new methods before this