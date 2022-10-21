package minesweeper.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import minesweeper.core.HighscoreEntry;

public class HighscoresView {

    /**
     * Fills the table with all the highscores in the highscore-list.
     * The highscore-entry-attributes will be placed in their corresponding
     * columns.
     * @param list the table to be populated
     * @param name the column containing names to be populated
     * @param score the column containing scores to be populated
     * @param highscores the highscores used to populate the table
     */
    public void setCells(
        final TableView<HighscoreEntry> list,
        final TableColumn<HighscoreEntry, String> name,
        final TableColumn<HighscoreEntry, Integer> score,
        final ObservableList<HighscoreEntry> highscores
    ) {

        name.setCellValueFactory(new PropertyValueFactory<HighscoreEntry,
            String>("name")
        );
        score.setCellValueFactory(new PropertyValueFactory<HighscoreEntry,
            Integer>("score")
        );
        list.setItems(highscores);
    }
}
