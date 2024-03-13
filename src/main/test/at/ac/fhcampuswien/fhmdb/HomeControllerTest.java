package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;
import java.util.List;

class HomeControllerTest {

    //private static HomeController homeController;
    @BeforeAll
    static class HomeControllerTest {
        HomeController controller = new HomeController();
        @Test
        void display_only_movies_with_selected_genre(){

            //we need multiple Lists
            //one for the example input movies
            List<Movie> example = new ArrayList<>();
            //another one for the movies we expect
            List<Movie> expected = new ArrayList<>();
            //and one for the actual movies we get
            List<Movie> actual;

            //Given
            Movie movie1 = new Movie("Film-1", "Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
            Movie movie2 = new Movie("Film-2", "Description of film-2", Arrays.asList(Genres.DRAMA));
            Movie movie3 = new Movie("Film-3", "Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));
            Movie movie4 = new Movie("Film-4", "Description of film-4", Arrays.asList(Genres.BIOGRAPHY, Genres.DRAMA));
            example.add(movie1);
            example.add(movie2);
            example.add(movie3);
            example.add(movie4);

            //When
            actual = controller.filterMoviesGenre(example, Genres.DRAMA);

            //Then
            expected.add(movie2);
            expected.add(movie3);
            expected.add(movie4);

            //checking if true
            assertEquals(expected, actual);

        }

        @Test
        void display_only_movies_with_right_searchQuery_either_title_or_description(){

            //we need multiple Lists
            //one for the example input movies
            List<Movie> example = new ArrayList<>();
            //another one for the movies we expect
            List<Movie> expected = new ArrayList<>();
            //and one for the actual movies we get
            List<Movie> actual;

            //Given
            Movie movie1 = new Movie("Film-1", "Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
            Movie movie2 = new Movie("Film-2", "Description of film-2 yes", Arrays.asList(Genres.DRAMA));
            Movie movie3 = new Movie("Film-3", "Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));
            Movie movie4 = new Movie("Film-4", "Description of film-4 yes", Arrays.asList(Genres.BIOGRAPHY, Genres.DRAMA));
            example.add(movie1);
            example.add(movie2);
            example.add(movie3);
            example.add(movie4);

            //When
            actual = controller.filterMoviesSearchQuery(example, "yes");

            //Then
            expected.add(movie2);
            expected.add(movie4);

            //checking if true
            assertEquals(expected, actual);
        }

    @Test
    void at_initialization_allMovies_and_observableMovies_should_be_filled_and_equal() {
        homeController.initializeState();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }



}