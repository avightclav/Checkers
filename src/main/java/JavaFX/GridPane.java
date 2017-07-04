package JavaFX;

import javafx.scene.Node;

public class GridPane extends javafx.scene.layout.GridPane {
    private int size;

    GridPane(int size) {
        super();
        this.size = size;
    }

    @Override
    public void add(Node child, int columnIndex, int rowIndex) {
        super.add(child, columnIndex, size - 1 - rowIndex);
    }

}
