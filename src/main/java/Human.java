import java.util.Arrays;

public class Human extends Player {

    public Human() {
        String s = Game.ask("Как Вас зовут?", null);
        this.setName(s);

        String s1 = Game.ask("Каким знаком хотите играть? X или O", Arrays.asList("X","O"));
        this.setSymbol(s1);
    }

    @Override
    public void turn(Integer x, Integer y, Game game) {
        while (true) {
            String s = Game.ask("Укажите, куда поставить \"" + this.getSymbol() + "\", xy", null);
            x = Integer.valueOf(s.substring(0, 1));
            y = Integer.valueOf(s.substring(1, 2));


            if (!(x.intValue() >= 1 && x.intValue() <= 3)) {
                System.out.println("Введите слитно два числа от 1 до 3, указывающее на незаполненную ячейку");
                continue;
            }

            if (!(y.intValue() >= 1 && y.intValue() <= 3)) {
                System.out.println("Введите слитно два числа от 1 до 3, указывающее на незаполненную ячейку");
                continue;
            }

            x = x - 1;
            y = y - 1;

            if (!setMark(x.intValue(), y.intValue(), game)) {
                continue;
            }

            break;
        }

    }
}
