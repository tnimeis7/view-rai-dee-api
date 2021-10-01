package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Genre;
import th.ac.ku.viewbackend.service.GenreService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public String saveGenre(@RequestBody Genre genre) throws ExecutionException, InterruptedException {
        return genreService.saveGenre(genre);
    }

    @GetMapping
    public List<Genre> getAllGenre() throws ExecutionException, InterruptedException {
        return genreService.getAllGenre();
    }

    @GetMapping("{genreId}")
    public Genre getGenre(@PathVariable String genreId) throws ExecutionException, InterruptedException {
        return genreService.getGenre(genreId);
    }

    @DeleteMapping("{genreId}")
    public String deleteGenre(@PathVariable String genreId) throws ExecutionException, InterruptedException {
        return genreService.deleteGenre(genreId);
    }

}
