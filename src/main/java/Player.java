public abstract class Player {
    private String symbol;
    private int order;
    private String name;

    public abstract void turn (Integer x, Integer y, Game game);

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean setMark (Integer x, Integer y, Game game) {
        // Пусто
        if (game.getGrid()[x][y]==0){
            game.getGrid()[x][y]=Game.digSymbol(this.getSymbol());
            return true;
        } else {
            return false;
        }
    }
}
