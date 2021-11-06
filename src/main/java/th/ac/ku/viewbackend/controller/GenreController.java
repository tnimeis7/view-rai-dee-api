package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Genre;
import th.ac.ku.viewbackend.service.BlockService;
import th.ac.ku.viewbackend.service.GenreService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Genre")
public class GenreController {

    @Autowired
    private BlockService service;

    @Autowired
    private GenreService genreService;

    @PostMapping
    public BlockComponents saveGenre(@RequestBody Genre genre) throws ExecutionException, InterruptedException {
        return service.save(genre, "Genre");
    }

    @GetMapping
    public List<BlockComponents> getAllGenre() throws ExecutionException, InterruptedException {
        return service.getAll(Genre.class, "Genre");
    }

    @GetMapping("/{genreId}")
    public BlockComponents getGenre(@PathVariable String genreId) throws ExecutionException, InterruptedException {
        return service.getById(genreId, Genre.class, "Genre");
    }

    @DeleteMapping("/{genreId}")
    public String deleteGenre(@PathVariable String genreId) throws ExecutionException, InterruptedException {
        return service.delete(genreId, "Genre");
    }

    @GetMapping("/{atcId}/genre")
    public List<BlockComponents> getAllGenreByAtcId(@PathVariable String atcId) throws ExecutionException, InterruptedException{
        return genreService.getGenreByAtcId(atcId);
    }

}
