import java.util.Random;

public class Computer extends Player {

    public Computer() {
        this.setName("Вини Пух");
    }

    private boolean test (Integer x, Integer y, Game game, boolean checkWin, Player player) {
        if (game.getGrid()[x][y]!=0) return false;

        setMark(x, y, game);

        if (checkWin) {
            if (game.win(player)) {
                return true;
            } else {
                game.setGridNull(x, y);
                return false;
            }
        }

        return true;
    }

    @Override
    public void turn(Integer x, Integer y, Game game) {
        int[][] algorithm = {{1,1},{0,0},{2,2},{2,0},{0,2},{0,1},{2,1},{1,0},{1,2}};

        for (int i = 0; i < algorithm.length; i++) {
            if (test(algorithm[i][0], algorithm[i][1], game, true, this)) return;
        }

        for (int i = 0; i < algorithm.length; i++) {
            if (test(algorithm[i][0], algorithm[i][1], game, false, this)) return;
        }

        Random random = new Random();

        while (true) {
            x = random.nextInt(2);
            y = random.nextInt(2);

            test(x, y, game, false, this);
        }
    }
}
