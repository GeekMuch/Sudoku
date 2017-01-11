

public class Sudoku extends SudokuGUI {

    private static final long serialVersionUID = 1L;

    public static String solved2;
    public static int[][] solvedf;

    public static void main(String[] args) throws SolvedException {
        SudokuGUI sudokugui = new SudokuGUI();
        sudokugui.setSize(550, 500);
        sudokugui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        sudokugui.setLocationRelativeTo(null);

        Field field = new Field();

        if (field == null) {
            field.fromFile(openfile.path);
        }
    }

    public static void solve(Field f, int i, int j) throws SolvedException, InterruptedException {


        if (i >= Field.SIZE) {
            solved2 = f.toString();
            solvedf = Field.model;
            System.out.println(f);
            throw new SolvedException();
        }
        if (Field.model[i][j] != 0) {
            nextCell(f, i, j);
            SolvedFieldView();

        } else {
            for (int val = 1; val < 10; val++) {
                if (f.tryValue(val, i, j)) {
                    nextCell(f, i, j);
                    SolvedFieldView();

                    Thread.sleep(50);
                }
            }


            f.clear(i, j);
            SolvedFieldView();
        }
    } // End of solve method

    // Calls solve for the next cell
    public static void nextCell(Field f, int i, int j) throws SolvedException, InterruptedException {
        if (j < 8)
            solve(f, i, j + 1);
        else
            solve(f, i + 1, 0);
    }


}//end of file
  
