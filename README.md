# RubikCube-Game

## Description

This project is a 2D variant of the classic Rubik's Cube game. The board consists of an `n x n` grid where `n` is the size of the cube (e.g., 2x2, 4x4, or 6x6). Each cell is filled with one of `n` colors, with each color appearing exactly `n` times on the board. The cells are shuffled randomly at the start of the game.

Players can rotate a row or a column cyclically (e.g., moving a row to the right, where the color of the first field will become the color of the last field) to try to make the rows and columns homogeneous. The game ends when each row or each column contains cells of the same color.

### Features:
- **Game Size Options**: Choose from 2x2, 4x4, or 6x6 board sizes.
- **Rotation Mechanics**: Rotate rows and columns cyclically.
- **Win Condition**: The game ends when all rows or all columns are homogeneous (contain a single color).
- **Step Counter**: Keeps track of how many steps it took to solve the puzzle.
- **Automatic Reset**: Once the game is solved, it automatically resets to a new random configuration and starts again.

---

## Game Play

- Rotate rows or columns to align colors.
- The game ends when all rows or columns contain the same color in each cell.
- Upon winning, a message box will show the number of steps taken to solve the game.
- After the game ends, a new game will start automatically.

---

## Getting Started

### Prerequisites
Make sure you have the following installed on your machine:
- Java 8 or later
- A suitable IDE (e.g., IntelliJ IDEA, Eclipse) or text editor for Java development.

