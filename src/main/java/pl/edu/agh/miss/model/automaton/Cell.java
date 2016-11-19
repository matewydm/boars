package pl.edu.agh.miss.model.automaton;

public class Cell {
    private Position position;
    private State state;

    public Cell(Position position, State state) {
        this.position = position;
        this.state = state;
    }

    public Position getPosition() {
        return position;
    }
    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (position != null ? !position.equals(cell.position) : cell.position != null) return false;
        return state != null ? state.equals(cell.state) : cell.state == null;

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
