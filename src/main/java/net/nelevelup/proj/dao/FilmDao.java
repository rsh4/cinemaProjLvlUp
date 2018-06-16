package net.nelevelup.proj.dao;


import net.nelevelup.proj.entity.Film;
import net.nelevelup.proj.entity.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmDao {

    @Autowired
    SessionFactory sessionFactory;

    public List<Film> getFilms(){
        Session session = sessionFactory.getCurrentSession();
        List<Film> films = session.createQuery("from Film").list();
        return films;
    }

    public Film getFilmByName(String filmName){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Film where title = :filmname");
        query.setParameter("filmname", filmName);
        return (Film)query.uniqueResult();
    }

    public void saveFilm(Film film){
        Session session = sessionFactory.getCurrentSession();
        film.setPoster(film.getImage().getOriginalFilename());
        String[] filmGenres = film.getFilmGenres().split(",");
        ArrayList<Genre> genres = new ArrayList<Genre>();
        Query query = session.createQuery("from Genre where genre = :currentGenre");
        for(int amount = 0; amount < filmGenres.length; amount++){
            query.setParameter("currentGenre", filmGenres[amount]);
            if(query.list().size() != 0){
                genres.add((Genre)query.uniqueResult());
            }
        }
        film.setGenres(genres);
        session.save(film);
    }

    public void deleteFilmById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        Film film = session.get(Film.class, id);
        session.remove(film);
    }

    public boolean filmExistsByName(String filmname){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Film where title = :filmname");
        query.setParameter("filmname", filmname);
        return query.list().size() != 0;
    }

    public boolean filmExistsById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id) != null;
    }

    public Film getFilmById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
    }

}
