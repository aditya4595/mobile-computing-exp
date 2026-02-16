# Experiment No. 2: Frequency Reuse Simulation

## Aim
To design and develop an interactive Python application that simulates frequency reuse in cellular networks using a hexagonal cell layout, allowing users to visualize and identify co-channel cells.

## Objective
- Implement a graphical simulation of cellular network frequency reuse using hexagonal cells.
- Calculate the cluster size (N) based on user-provided i and j values using the formula N = i² + i*j + j².
- Provide an interactive interface where users can select hexagons to identify co-channel cells.
- Visualize the connections between co-channel cells with lines.
- Include features for resetting the simulation and providing feedback on correct/incorrect selections.

## Theory

### Frequency Reuse in Cellular Networks
Frequency reuse is a technique used in cellular networks to maximize spectrum efficiency by reusing the same frequency channels in different cells, provided they are sufficiently separated to avoid interference. The reuse distance is the minimum distance between two cells using the same frequency.

### Hexagonal Cell Layout
Cellular networks often use a hexagonal grid to model cell coverage because hexagons approximate circular coverage areas better than squares or triangles. Each hexagon represents a cell, and the distance between cell centers is uniform.

### Cluster Size Calculation
The cluster size N determines how many cells share the available frequency channels. It is calculated using the formula:

$$N = i^2 + i \times j + j^2$$

Where i and j are integers that define the reuse pattern. Common values include (1,0), (1,1), (2,0), etc.

### Co-Channel Cells
Co-channel cells are cells that use the same frequency channel. They must be separated by at least the reuse distance to minimize interference. The application calculates and highlights these cells based on the selected cluster size.

## Tech Stack

| Component | Technology |
|---|---|
| Language | Python 3 |
| GUI Framework | Tkinter |
| Math Library | math (built-in) |
| Execution | Command Line / Python Interpreter |

## Features

### 1. Hexagonal Grid Generation
- Creates a grid of 16x10 hexagons representing cellular cells.
- Each hexagon is drawn using Tkinter's Canvas with calculated coordinates.

### 2. Interactive Selection
- Users click on hexagons to select them.
- First click selects the initial cell and calculates co-channel cells.
- Subsequent clicks check if the selected cell is a co-channel cell.

### 3. Co-Channel Visualization
- Highlights co-channel cells in green.
- Draws lines connecting all co-channel cells to the initial cell.
- Provides feedback on correct/incorrect selections via a text box.

### 4. Cluster Size Input
- Command-line input for i and j values to calculate N.
- Validates input (i and j cannot both be zero, j ≤ i).

### 5. Reset Functionality
- Press Shift-R to reset the grid and start a new simulation.
- Clears all highlights and lines.

## App Screens

| Screen | Description |
|---|---|
| **Main Window** | Tkinter window with canvas showing hexagonal grid, bottom text box for instructions/feedback. |
| **Selection Feedback** | Text box updates with messages like "Select a Hexagon", "Correct! Cell is a co-cell", etc. |
| **Line Visualization** | Lines drawn between co-channel cells after all are correctly identified. |

## Project Structure

```
Exp 2 Frequency reuse/
├── main.py              # Main script containing Hexagon class and FrequencyReuse GUI
```

### Classes and Methods
- **Hexagon Class**: Handles drawing individual hexagons on the canvas.
  - `__init__()`: Initializes hexagon properties.
  - `draw_hex()`: Calculates and draws the hexagon polygon.
- **FrequencyReuse Class (inherits from Tk)**: Main GUI application.
  - `__init__()`: Sets up the Tkinter window, canvas, and initial grid.
  - `create_grid()`: Generates the hexagonal grid.
  - `call_back()`: Handles mouse clicks for cell selection.
  - `show_lines()`: Draws lines connecting co-channel cells.
  - `reset_grid()`: Resets the simulation state.
  - `cluster_reuse_calc()`: Calculates reuse distance and center distances.

## How to Run

1. Ensure Python 3 is installed on your system.
2. Open a command prompt or terminal and navigate to the project directory.
3. Run the script: `python main.py`
4. Enter values for i and j when prompted (e.g., i=1, j=0 for N=1).
5. The Tkinter window will open with the hexagonal grid.
6. Click on hexagons to interact with the simulation.
7. Press Shift-R to reset and start over.

## Conclusion

The experiment demonstrates the implementation of frequency reuse concepts in cellular networks through an interactive Python application. Key learnings include understanding hexagonal cell layouts, calculating cluster sizes, identifying co-channel interference patterns, and building GUI applications with Tkinter. The simulation provides a visual and interactive way to grasp the principles of frequency reuse, which are fundamental to modern cellular network design. The use of Tkinter for graphics and event handling showcases Python's capabilities for educational and simulation tools.