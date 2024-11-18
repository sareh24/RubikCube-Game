package rubikcube;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Random;

/**
 * The Rubik class represents a 2D Rubik's cube.
 * The cube is an n x n grid, where each cell has of n colors.
 * The player can rotate row or columns to make row or columns homogeneous.
 * The game ends when all rows or all columns contain cells of a single color.
 * 
 * @author sarehsoltani
 */
public class Rubik {
    private int[][] cube;
    private int[] color;
    private int[] colorcount;
    private int n;  // Size of the board
 

    
    /**
     * Constructs a Rubik game with a specified board size and initial step count.
     * 
     * @param n the size of the cube (n x n).
     * @throws IllegalArgumentException if the size is less than 2.
     */
    public Rubik(int n) {
        if (n < 2){
           throw new IllegalArgumentException("Cube size must be 2 or larger.");
        }
        this.n = n;
         create(n);
    }


    public int[][] getCube() {
        return cube;
    }
    
    
    /**
     * Initialize the Rubik game board by setting up the grid and distributing colors randomly.
     * Each color appears exactly n times on the board, ensuring that every cell is filled.
     * 
     * @param n the size of the cube (n x n)
     */
    public final void create(int n){
        try{
        cube = new int[n][n];
        color = new int[n];
        colorcount = new int[n];
        //rows
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                cube[i][j] = -1;
            }
                  
        }
        //for collors
        for(int i = 0; i < n; i++){
            color[i] = i;
            colorcount[i] = 0;
        }
        // Define the colors 
        
        Random rand = new Random();
        //give a random color to each cell in the cube
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int select;
                do{
                 select = rand.nextInt(n);
                }while(colorcount[select] >= n); 
                 cube[i][j] = select; 
                 colorcount[select]++; 
                }
        }
        }catch(Exception e){
          System.err.println("Error during cube initialization: " + e.getMessage());
        }
          
        
    }
    /**
     * Rotate the colors in the specified row cyclically to the right by one position.
     * This action increments the step counter and checks if the game is solved.
     * 
     * @param rowIndex the index of the row to rotate.
     */
    public void rotateRow(int rowIndex) {
        // Method to rotate a row to the right
        try{
        int temp = cube[rowIndex][n - 1];
        for (int i = n - 1; i > 0; i--) {
            cube[rowIndex][i] = cube[rowIndex][i - 1];
        }
        cube[rowIndex][0] = temp;
       // steps++;
        checkWin();
        }catch(Exception e){
           System.err.println("Error during row rotation: " + e.getMessage());
        }
    }
    /**
     * Rotate the colors in the specified column cyclically to the down by one position.
     * 
     * @param colIndex the index of the column to rotate.
     */
    public void rotateColumn(int colIndex){
        try{
        int temp = cube[n-1][colIndex];
        for (int i = n-1; i > 0; i--){
            cube[i][colIndex] = cube[i - 1][colIndex];
        }
        cube[0][colIndex] = temp;
        //steps++;
        checkWin();
        }catch(Exception e){
             System.err.println("Error during column rotation: " + e.getMessage());
        }
    }
    
    /**
     * Checks if the game is solved. The game is considered solved when each row
     * or each column contains cells of a single color. 
     * The method first checks if all rows are homogeneous (each cell in a row 
     * has the same color). If this condition is satisfied, the game is considered 
     * solved and the method returns {@code true}.
     * 
     * If not all rows are homogeneous, it then checks if all columns are homogeneous
     * (each cell in a column has the same color). If this condition is satisfied, 
     * the game is considered solved and the method returns {@code true}.
     * If neither rows nor columns are homogeneous, the game is not solved, 
     * and the method returns {@code false}.
     *
     * @return {@code true} if the game is solved, {@code false} otherwise.
     *
     */
    public Boolean checkWin(){
        boolean rowSolved = true;
        //if rows are homogeneous
        for (int i = 0; i < n; i++){
            int rowColor = cube[i][0];
            for (int j = 0; j < n; j++){
                if(cube[i][j] != rowColor){
                    rowSolved = false;
                    break;
                }
            }
              if (!rowSolved){
                break;
               }
        
        }
        if(rowSolved){
           return true;
        }
        // If rows are not homogeneous, check columns
        
            boolean colSolved = true;
            for (int j = 0; j < n; j++){
                 int colColor = cube[0][j];
                 for (int i = 0; i < n; i++){
                     if(cube[i][j] != colColor){
                         colSolved = false;
                         break;
                     }
                 }
                 if(!colSolved){
                     break;
                 }
            }
        
       
            
            
        return colSolved;
    
   }

  
 /**
 * Resets the game by clearing the current board,
 * and reinitializing the board with a new random distribution of colors.
 */
    
    public void resetGame(){
       try{
       create(n);
       }catch(Exception e){
          System.err.println("Error during game reset: " + e.getMessage());
       }
     
    }
    
}

    
    
    
    
    
    


