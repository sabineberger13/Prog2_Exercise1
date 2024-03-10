package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.util.*;
import java.net.URL;
import java.util.stream.Collectors;


public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public SortedState sortedState = SortedState.NONE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        observableMovies.addAll(allMovies);         // add dummy data to obsegrvable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
       // genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setPromptText("Filter by Genre");

        genreComboBox.getItems().addAll("ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY", "COMEDY", "CRIME",
                "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR", "MUSICAL", "MYSTERY", "ROMANCE",
                "SCIENCE_FICTION", "SPORT", "THRILLER", "WAR", "WESTERN");


        // TODO add event handlers to buttons and call the regarding methods
        // EventHandler für den Suchbutton
        searchBtn.setOnAction(actionEvent -> filterMovies());
        genreComboBox.setOnAction(actionEvent -> filterMovies());
        sortBtn.setOnAction(actionEvent -> sortMovies());

        {
            String searchText = searchField.getText().toLowerCase().trim();

            List<Movie> filteredMovies = allMovies.stream()
                    .filter(movie -> movie.getTitle().toLowerCase().contains(searchText))
                    .collect(Collectors.toList());

            observableMovies.setAll(filteredMovies);
            movieListView.refresh();


        };

        // Sort button example funktoniert noch nicht ganz:
        sortBtn.setOnAction(actionEvent ->
        {
            String searchText = searchField.getText().toLowerCase().trim();
            if (!searchText.isEmpty())
            {
                // Filter die observableMovies Liste basierend auf dem Suchtext
                List<Movie> filteredMovies = allMovies.stream()
                        .filter(movie -> movie.getTitle().toLowerCase().contains(searchText))
                        .collect(Collectors.toList());

                observableMovies.setAll(filteredMovies);
            } else
            {
                // Wenn kein Suchtext eingegeben wurde, setze die Liste auf alle Filme zurück
                observableMovies.setAll(allMovies);
            }

            movieListView.refresh();
        });
    }

    private void filterMovies() {
    }

    public void sortMovies() {
        observableMovies.sort(Comparator.comparing(Movie::getTitle));
        sortedState = SortedState.ASCENDING;
    }
}