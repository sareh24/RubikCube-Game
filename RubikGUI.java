
package rubikcube;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * * The {@code RubikGUI} class provides a graphical user interface (GUI) for playing a Rubik's cube game.
 * It enables users to interact with the Rubik's cube through buttons, allowing row and column rotations,
 * and displays the current state of the cube visually.
 * <p>
 * The GUI includes:
 * <ul>
 * <li>A grid of buttons representing the Rubik's cube cells, with colors corresponding to the cube's state.</li>
 * <li>A menu bar for changing the cube size (2x2, 4x4, or 6x6) and exiting the game.</li>
 * <li>A step counter to track the number of user actions taken.</li>
 * </ul>
 * </p>
 * The game ends when the player solves the cube by making all rows or columns homogeneous.
 *
 * @author sarehsoltani
 * @see Rubik
 */
public class RubikGUI implements ActionListener{

    Rubik rubik;
    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private JMenuBar menuBar;
    private JMenu sizeMenu;
    private JMenuItem two;
    private JMenuItem four;
    private JMenuItem six;
    private JMenuItem exitMenu;
    private int steps = 0;
    //Array of colors corresponding to cube values.
    private static final Color[] colors = 
    {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.PINK, Color.YELLOW
    };
    
     /**
     * Constructs a {@code RubikGUI} instance with a default 4x4 Rubik's cube.
     * Initializes the GUI components, sets up the menu bar, and displays the frame.
     */
    public RubikGUI(){
       int n = 4; 
       rubik = new Rubik(4);
       frame = new JFrame("rubik");
       
       panel = new JPanel();
       panel.setLayout(new GridLayout(n, n));
       frame.add(panel);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
       buttons = new JButton[n][n];
       initulizeButtons(n);
       frame.add(panel, BorderLayout.CENTER);
       menuBar = new JMenuBar();
       sizeMenu = new JMenu("size");
       menuBar.add(sizeMenu);
       two = new JMenuItem("2 x 2");
       sizeMenu.add(two);
       two.addActionListener(this);
       four = new JMenuItem("4 x 4");
       sizeMenu.add(four);
       four.addActionListener(this);
       six = new JMenuItem("6 x 6");
       sizeMenu.add(six);
       six.addActionListener(this);
       exitMenu = new JMenuItem("Exit");
       sizeMenu.add(exitMenu);
       exitMenu.addActionListener(this);
      // frame.add(exitButton, BorderLayout.SOUTH);
       frame.setJMenuBar(menuBar);
       frame.pack();
       frame.setSize(500, 500);
       frame.setVisible(true);
       
    }
    
    /**
     * Initializes the buttons on the grid panel. Each button's background color 
     * corresponds to the cube's current state, and clicking a button triggers rotation.
     * 
     * @param n the size of the cube (n x n).
     */
    public void initulizeButtons(int n){
       Border cellBorder = BorderFactory.createLineBorder(Color.WHITE);
       for (int i = 0; i < n; i++){
           for (int j = 0; j < n; j++){
               buttons[i][j] = new JButton();
               buttons[i][j].setBackground(getColor(rubik.getCube()[i][j]));
               // Force the color to display
               buttons[i][j].setOpaque(true); 
               buttons[i][j].setContentAreaFilled(true);

               buttons[i][j].setBorder(cellBorder);
               
               final int row = i;
               final int col = j;
               buttons[i][j].addActionListener(e -> buttonClicked(row, col));
               panel.add(buttons[i][j]);
           }
       }
    }
    
     /**
     * Gets the corresponding for a given cube value.
     * If the value is invalid, returns {@code Color.GRAY}.
     * 
     * @param colorValue the value representing a color in the cube.
     * @return the  Color corresponding to the given value.
     */
    public Color getColor(int colorValue){
       if (colorValue >= 0 && colorValue <  colors.length){
           return colors[colorValue];
       }
       return Color.GRAY;
    }
    
     /**
     * Handles the event of a button being clicked. 
     * Rotates the row and column corresponding to the clicked button, updates the colors, 
     * increments the step counter, and checks for a win condition.
     * 
     * @param row the row index of the clicked button.
     * @param col the column index of the clicked button.
     */
    public void buttonClicked(int row, int col){
       rubik.rotateRow(row);
       rubik.rotateColumn(col);
      
       steps++;
       updateButtonColors();
       
       if(rubik.checkWin()){
           javax.swing.JOptionPane.showMessageDialog(frame, 
            "You solved it in " + steps + " steps!");
           rubik.resetGame(); // Reset the game
           steps = 0;
           updateButtonColors();
       }
    }
    
    /**
     * Updates the colors of the buttons based on the current state of the Rubik's cube.
     */
    public void updateButtonColors(){
       int[][] cube = rubik.getCube();
        for (int i = 0; i < cube.length; i++) {
        for (int j = 0; j < cube[i].length; j++) {
            buttons[i][j].setBackground(getColor(cube[i][j]));
        }
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
          if (e.getSource() == two) {
                setBoardSize(2);
        } else if (e.getSource() == four) {
                setBoardSize(4);
        } else if (e.getSource() == six) {
                setBoardSize(6);
        } else if (e.getSource() == exitMenu) {
                System.exit(0);
        }
    }
    /**
     * Changes the board size to the specified value, reinitializes the Rubik's cube 
     * and the grid panel, and updates the GUI accordingly.
     * 
     * @param size the new size of the cube (e.g., 2, 4, or 6).
     */
    public void setBoardSize(int size){
        frame.remove(panel);
         rubik = new Rubik(size);
         panel = new JPanel(new GridLayout(size, size));
         buttons = new JButton[size][size];
         initulizeButtons(size);
         frame.add(panel, BorderLayout.CENTER);
         frame.revalidate();
         frame.repaint();
    }
}
