package net.nelevelup.proj.service;

import net.nelevelup.proj.dao.FilmDao;
import net.nelevelup.proj.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmDao filmDao;

    @Transactional
    public List<Film> getFilms(){
        List<Film> films = filmDao.getFilms();
        return films;
    }

    @Transactional
    public boolean filmExistsByName(String filmname){
        return filmDao.filmExistsByName(filmname);
    }

    @Transactional
    public void deleteFilmById(Integer id){
        filmDao.deleteFilmById(id);
    }

    @Transactional
    public boolean filmExistsById(Integer id){
        return filmDao.filmExistsById(id);
    }

    @Transactional
    public Film getFilmByName(String filmName){
        return filmDao.getFilmByName(filmName);
    }

    @Transactional
    public void saveFilm(Film film){
        filmDao.saveFilm(film);
    }

    @Transactional
    public Film getFilmById(int id){
        return filmDao.getFilmById(id);
    }
}
