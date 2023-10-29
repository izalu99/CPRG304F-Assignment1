
package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import shape.ThreeDShape;
import shape.Cone;
import shape.Cylinder;

import shape.*;

/**
 *
 * @author dlg12
 */
public class AppDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
       /**
        * this method reads a text file of unsorted shapes
        * and add them to an array
        */ 
       
       // command line args; initialized with some value; to be changed later
       String fileName = "";
       String comparisonType = "";
       String sortingAlgo = "";
       
       
       // Parse command line args
       for (int i=0; i < args.length; i++){
           if(args[i].toLowerCase().startsWith("-f")){
               fileName = args[i].substring(2);
           }
           else if(args[i].toLowerCase().startsWith("-t")){
               comparisonType = args[i].substring(2);
           }
           else if(args[i].toLowerCase().startsWith("-s")){
               sortingAlgo = args[i].substring(2).toLowerCase(); // make them all lower case for easier handling
           }
       }
       
       // just in case the filename is not entered
       if (fileName.isEmpty() || comparisonType.isEmpty() || !isValidSortingAlgo(sortingAlgo)){
           System.out.println("Use the command: java AppDriver -f<file name> -t<comparison type> -s<sorting algorithm>");
           System.exit(1); // failed run and terminate
       }
       
       
       ////////// reading the file
       
       // create an array to store the threeD shapes
       ThreeDShape[] shapes3D = new ThreeDShape[100]; // will be changed later
       
       
       // Read the shapes from the given file and add to the array
       // used scanner to read the shapes and properties by " " instead of by line
       // since NOT each line is organized where the first up is a shapeType
       try{
           //create scanner to read the texts
           Scanner scanner = new Scanner(new File(fileName));
           // use " " as the delimiter
           //source: https://www.javatpoint.com/post/java-scanner-usedelimiter-method
           scanner.useDelimiter(" ");
           
           // Read the shapes from the file and add them to the list
           int shape3DCount = 0;
           // recall that the very first text/token is the num of shapes
           int numOfShapes = Integer.parseInt(scanner.next());
           shapes3D = new ThreeDShape[numOfShapes];
           while (scanner.hasNext()) {
               
               
               // use the numOfShapes to create the array
               //shapes3D = new ThreeDShape[numOfShapes]; moved it to line 75
               // the next thing would then be a shapeType
               String shapeType = scanner.next();
               // find out what 3D shape it is to create it and add to the list
               if(shapeType.contains("Cylinder")){
                   double height = scanner.nextDouble();
                   double radius = scanner.nextDouble();
                   shapes3D[shape3DCount] = new Cylinder(height, radius);
                   shape3DCount++;
               }
               else if(shapeType.contains("Cone")){
                   //handle Cone
                   double height = scanner.nextDouble();
                   double radius = scanner.nextDouble();
                   shapes3D[shape3DCount] = new Cone(height, radius);
                   shape3DCount++;
               }
               else if(shapeType.contains("Pyramid")){
                   //handle Pyramid
                    double height = scanner.nextDouble();
                    double edgeLength = scanner.nextDouble();
                    shapes3D[shape3DCount] = new Pyramid(height, edgeLength);
                    shape3DCount++;
               }
               else if(shapeType.equalsIgnoreCase("Prism") || 
                       shapeType.contains("Prism")){
                   // handle Prism
                   // Get the base shape type
                    double height = scanner.nextDouble();
                    double edgeLength = scanner.nextDouble();

                    // Create the corresponding prism based on the base shape type
                    switch (shapeType) {
                        case "SquarePrism":
                            shapes3D[shape3DCount] = new SquarePrism(height, edgeLength);
                            break;
                        case "TriangularPrism":
                            shapes3D[shape3DCount] = new TriangularPrism(height, edgeLength);
                            break;
                        case "PentagonalPrism":
                            shapes3D[shape3DCount] = new PentagonalPrism(height, edgeLength);
                            break;
                        case "OctagonalPrism":
                            shapes3D[shape3DCount] = new OctagonalPrism(height, edgeLength);
                            break;
                        default:
                            System.out.println("Invalid base shape type for Prism: " + shapeType);
                            return;
                    }

                    shape3DCount++;
               }
               
            //print the 3d to check
           //for (int i = 0; i< shapes3D.length; i++){
           //    System.out.println(shapes3D[i].getHeight());
           //}
           }
           
           //close scanner
           scanner.close();
           
           //print the 3d to check
           for (int i = 0; i< shapes3D.length; i++){
               System.out.println(shapes3D[i].getHeight());
           }
           //print shape3D count
           System.out.println(shape3DCount);
       }
       catch (FileNotFoundException e){
           System.out.println("Error reading the file: " + e.getMessage());
           System.exit(1); // run is failed so terminate
       }
       
       
       
       
       // Process and sort the shapes on the given criteria
       // use ThreeDShapeComparator
       // or the comparable
       
       
       
       //print the sorted shapes

       
        
        
    }// end of main method
    
    
    //helper method for determining valid sorting algorithm
    public static boolean isValidSortingAlgo(String sortingAlgo){
        // return true if the given sortingAlgo is conatined in the string
        // that has the supposed command arg for the sorting algorithms
        // - "b" or "B" for bubble sort
        // "s" or "S" for selection sort
        // "i" or "I" for insertion sort
        // "q" or "Q" for quick sort
        // "m" or "M" for merge sort
        // "z" or "Z" for some other sort
        
        return "bsiqmz".contains(sortingAlgo.toLowerCase());
    }
    
    
    
    
} // end of AppDriver class

