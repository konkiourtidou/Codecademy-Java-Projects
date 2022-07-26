import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;  // Import the Scanner class
import javax.imageio.ImageIO;
public class ImageProcessing {
    public static void main(String[] args) {
        Scanner scanChoice = new Scanner(System.in);
        System.out.println("Image Processing: ");
        System.out.println("1 - Type an image's URL or the path to a local image");
        System.out.println("0 - Exit");
        System.out.println("any other number - https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png - will be loaded \n");
        System.out.println("Input your choice: ");
        int choice = scanChoice.nextInt();
        while (choice != 0 ) {
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            int[][] imageData = imgToTwoD("https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png");
            if (choice == 1) {
                String path;
                System.out.println("\n Input URL or path: ");
                path = scanChoice.nextLine();
                path = scanChoice.nextLine();
                imageData = imgToTwoD(path);
            }
            //int[][] imageData = imgToTwoD(path);
            System.out.println("\n Loading Image...");

            System.out.println("1 - View Image 2D Array");
            System.out.println("2 - Trim Borders");
            System.out.println("3 - Negative Color");
            System.out.println("4 - Stretch Horizontally");
            System.out.println("5 - Shrink Vertically");
            System.out.println("6 - Invert Image");
            System.out.println("7 - Color Filter");
            System.out.println("0 - New Image or Exit");
            System.out.println();
            System.out.println("Enter \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\" or \"0\"");
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            int choiceEntry = scanChoice.nextInt();
            while (choiceEntry != 0){
                //int[][] imageData = imgToTwoD("https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png");

                if (choiceEntry == 1) {
                    viewImageData(imageData);
                }else if (choiceEntry == 2) {
                    int[][] trimmed = trimBorders(imageData, 60);
                    twoDToImage(trimmed, "./trimmed_image.jpg");
                } else if (choiceEntry == 3) {
                    int[][] negative = negativeColor(imageData);
                    twoDToImage(negative, "./negative_image.jpg");
                } else if (choiceEntry == 4) {
                    int[][] stretched = stretchHorizontally(imageData);
                    twoDToImage(stretched, "./stretched_image.jpg");
                } else if (choiceEntry == 5) {
                    int[][] shrinked = shrinkVertically(imageData);
                    twoDToImage(shrinked, "./shrinked_image.jpg");
                } else if (choiceEntry == 6) {
                    int[][] inverted = invertImage(imageData);
                    twoDToImage(inverted, "./inverted_image.jpg");
                } else if (choiceEntry == 7) {
                    System.out.println("Give the three change values for red, green and blue ");
                    //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                    int redC = scanChoice.nextInt();
                    int greenC = scanChoice.nextInt();
                    int blueC = scanChoice.nextInt();
                    int[][] filtered = colorFilter(imageData, redC, greenC, blueC);
                    twoDToImage(filtered, "./filtered_image.jpg");
                }

                System.out.println("1 - View Image 2D Array");
                System.out.println("2 - Trim Borders");
                System.out.println("3 - Negative Color");
                System.out.println("4 - Stretch Horizontally");
                System.out.println("5 - Shrink Vertically");
                System.out.println("6 - Invert Image");
                System.out.println("7 - Color Filter");
                System.out.println();
                System.out.println("Enter \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\" or \"0\"");
                //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                choiceEntry = scanChoice.nextInt();
            }
            System.out.println("Terminating...");


            System.out.println("Image Processing: ");
            System.out.println("1 - Type an image's URL or the path to a local image");
            System.out.println("0 - Exit");
            System.out.println("any other number - https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png - will be loaded \n");
            System.out.println("Input your choice: ");
            choice = scanChoice.nextInt();
        }
    }
    // Image Processing Methods
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        // Example Method
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            for (int i = 0; i < trimmedImg.length; i++) {
                for (int j = 0; j < trimmedImg[i].length; j++) {
                    trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
            }
            return trimmedImg;
        } else {
            System.out.println("Cannot trim that many pixels from the given image.");
            return imageTwoD;
        }
    }
    public static int[][] negativeColor(int[][] imageTwoD) {
        // TODO: Fill in the code for this
        int rows = imageTwoD.length;
        int columns = imageTwoD[0].length;
        int[][] negativeImage = new int[rows][columns];
        for (int i=0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = 255 - rgba[0];
                rgba[1] = 255 - rgba[1];
                rgba[2] = 255 - rgba[2];
                negativeImage[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return negativeImage;
    }
    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        // TODO: Fill in the code for this method
        int[][] stretchedImage = new int[imageTwoD.length][imageTwoD[0].length*2];
        int rows = imageTwoD.length;
        int columns = imageTwoD[0].length;
        for (int i=0; i<rows; i++) {
            for (int j = 0; j < columns; j++) {
                stretchedImage[i][j * 2] = imageTwoD[i][j];
                stretchedImage[i][j * 2 + 1] = imageTwoD[i][j];
            }
        }
        return stretchedImage;
    }
    public static int[][] shrinkVertically(int[][] imageTwoD) {
        // TODO: Fill in the code for this method
        int[][] shrinkedImage = new int[imageTwoD.length/2][imageTwoD[0].length];
        int rows = imageTwoD.length;
        int columns = imageTwoD[0].length;
        for (int i=0; i<columns; i++) {
            for (int j = 0; j < rows - 1 ; j+=2) {
                shrinkedImage[j/2][i] = imageTwoD[j][i];
            }
        }
        return shrinkedImage;
    }
    public static int[][] invertImage(int[][] imageTwoD) {
        // TODO: Fill in the code for this method
        int[][] invertedImage = new int[imageTwoD.length][imageTwoD[0].length];
        int rows = imageTwoD.length;
        int columns = imageTwoD[0].length;
        for (int i=0; i<rows; i++) {
            for (int j = 0; j < columns; j++) {
                invertedImage[rows - 1 - i][columns - 1 - j] = imageTwoD[i][j];
            }
        }
        return invertedImage;
    }
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
        // TODO: Fill in the code for this method
        int[][] filteredImage = new int[imageTwoD.length][imageTwoD[0].length];
        int rows = imageTwoD.length;
        int columns = imageTwoD[0].length;
        for (int i=0; i<rows; i++) {
            for (int j = 0; j < columns; j++) {
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = rgba[0] + redChangeValue;
                if (rgba[0] < 0) {
                    rgba[0] = 0;
                } else if (rgba[0] > 255) {
                    rgba[0] = 255;
                }
                rgba[1] = rgba[1] + greenChangeValue;
                if (rgba[1] < 0) {
                    rgba[1] = 0;
                } else if (rgba[1] > 255) {
                    rgba[1] = 255;
                }
                rgba[2] = rgba[2] + blueChangeValue;
                if (rgba[2] < 0) {
                    rgba[2] = 0;
                } else if (rgba[2] > 255) {
                    rgba[2] = 255;
                }
                filteredImage[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return filteredImage;
    }
    // Painting Methods
    public static int[][] paintRandomImage(int[][] canvas) {
        // TODO: Fill in the code for this method
        return null;
    }
    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
        // TODO: Fill in the code for this method
        return null;
    }
    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        // TODO: Fill in the code for this method
        return null;
    }
    // Utility Methods
    public static int[][] imgToTwoD(String inputFileOrLink) {
        try {
            BufferedImage image = null;
            if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
                URL imageUrl = new URL(inputFileOrLink);
                image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.out.println("Failed to get image from provided URL.");
                }
            } else {
                image = ImageIO.read(new File(inputFileOrLink));
            }
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }
    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    result.setRGB(j, i, imgData[i][j]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }
    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }
    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }
    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rawPixels[i][j] = imageTwoD[i][j];
                }
            }
            System.out.println("Raw pixel data from the top left corner.");
            System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
            int[][][] rgbPixels = new int[3][3][4];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                }
            }
            System.out.println();
            System.out.println("Extracted RGBA pixel data from top the left corner.");
            for (int[][] row : rgbPixels) {
                System.out.print(Arrays.deepToString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}