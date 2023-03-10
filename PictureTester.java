/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 *
 * @author Barbara Ericson
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }

  public static void testOnlyBlue() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }

  public static void testNegate() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }

  public static void testGrayscale() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }

  public static void testWatermark() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.watermark();
    beach.explore();
  }

  public static void testPixelate() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.pixelate(20);
    beach.explore();
  }

  public static void testBlur() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.blur(11);
    beach.explore();
  }

  public static void testEnhance() {
    Picture water = new Picture("images/water.jpg");
    water.explore();
    water = water.enhance(20);
    water.explore();
  }

  public static void testSwapLeftRight() {
    Picture bike = new Picture("images/redMotorcycle.jpg");
    bike.explore();
    bike = bike.swapLeftRight();
    bike.explore();
  }

  public static void testStairStep() {
    Picture bike = new Picture("images/redMotorcycle.jpg");
    bike.explore();
    bike = bike.stairStep(10, 10);
    bike.explore();
  }

  public static void testLiquify() {
    Picture bike = new Picture("images/redMotorcycle.jpg");
    bike.explore();
    bike = bike.liquify(100);
    bike.explore();
  }

  public static void testWavy() {
    Picture bike = new Picture("images/redMotorcycle.jpg");
    bike.explore();
    bike = bike.wavy(25);
    bike.explore();
  }

  public static void testEdgeDetectionBelow() {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan = swan.edgeDetectionBelow(10);
    swan.explore();
  }

  public static void testGreenScreen() {
    Picture background = new Picture("GreenScreenCatMouse/IndoorJapeneseRoomBackground.jpg");
    background = background.greenScreen();
    background.explore();
  }

  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }

  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }

  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }

  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }

  /** Main method for testing.  Every class can have a main
   * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testOnlyBlue();
    //testNegate();
    //testGrayscale();
    //testWatermark();
    //testPixelate();
    //testBlur();
    //testEnhance();
    //testSwapLeftRight();
    //testStairStep();
    //testLiquify();
    //testWavy();
    //testEdgeDetectionBelow();
    testGreenScreen();

    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}