import java.util.*;

public class Game {
    private int[][] grid = new int[3][3];
    private ArrayList<Player> players = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static String antiSymbol (String symbol) {
        return symbol.equals("X") ? "O": "X";
    }

    public static int digSymbol (String s) {
        return s.equals("X") ? 1: 2;
    }

    public static String strSymbol (int x) {
        if (x==0) return " ";
        if (x==1) return "X";
        if (x==2) return "O";
        return null;
    }

    public static String ask (String question, List<String> options) {
        System.out.println(question);
        String s;

        String s1 = null;

        if (options==null) {
            s1 = "";
        } else {
            for (String a: options) {
                s1 += a;
            }
        }

        while (true){
            s = scanner.nextLine();

            if (s1==null) {
                break;
            }

            if (!s1.isEmpty() && !s1.contains(s)) {
                System.out.println("Неверный ответ)");
            } else {
                break;
            }
        }

        return s;

    }

    public boolean win (Player player) {
        int symbol = digSymbol(player.getSymbol());
        boolean win;

        // перебор столбцов
        for (int y = 0; y < 3; y++) {
            win = true;

            for (int x = 0; x < 3; x++) {
                if (grid[x][y] != symbol) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return true;
            }
        }

        // перебор строк
        for (int x = 0; x < 3; x++) {
            win = true;

            for (int y = 0; y < 3; y++) {
                if (grid[x][y] != symbol) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return true;
            }
        }

        // диагонали
        if (symbol == grid[0][0] && symbol == grid[1][1] && symbol == grid[2][2]) {
            return true;
        }

        // диагонали
        if (symbol == grid[2][0] && symbol == grid[1][1] && symbol == grid[0][2]) {
            return true;
        }

        return false;
    }


    public Game() {
        String s1;

        s1 = ask("Кто ходит первый? К - компьютер, Ч - человек", Arrays.asList("К","Ч"));

        if ("К".equalsIgnoreCase(s1)) {
            players.add(new Computer());
            players.add(new Human());
        } else {
            players.add(new Human());
            players.add(new Computer());
        }

        for (int i = 0; i < 2; i++) {
            if (players.get(i) instanceof Computer) {
                int second = Math.abs(i-1);
                players.get(i).setSymbol(antiSymbol(players.get(second).getSymbol()));
            }
        }

        Integer x = null, y = null;

        showGrid();

        for (int i = 0; i < 9; i++) {
            int currentPlayer = i % 2;

            players.get(currentPlayer).turn(x, y, this);
            showGrid();

            if (win(players.get(i % 2))) {
                System.out.println("Победил "+players.get(currentPlayer).getName()+"!");
                break;
            }
        }

    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGridNull (int x, int y) {
        this.grid[x][y] = 0;
    }

    public void showGrid () {
        System.out.println("--------------------");
        for (int i = 0; i < 3; i++) {
            System.out.println("|"+strSymbol(grid[0][i])+"|"+strSymbol(grid[1][i])+"|"+strSymbol(grid[2][i])+"|");
        }
    }
}
