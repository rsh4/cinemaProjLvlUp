package net.nelevelup.proj.service;

import net.nelevelup.proj.dao.ExtraDao;
import net.nelevelup.proj.entity.Cost;
import net.nelevelup.proj.entity.HallList;
import net.nelevelup.proj.entity.SessionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExtraService {

    @Autowired
    ExtraDao extraDao;

    @Transactional
    public boolean costExistsById(Integer id){
        return extraDao.costExistsById(id);
    }

    @Transactional
    public boolean hallExistsById(Integer id){
        return extraDao.hallExistsById(id);
    }

    @Transactional
    public Cost getCostById(Integer id){
        return extraDao.getCostById(id);
    }

    @Transactional
    public HallList getHallListById(Integer id){
        return extraDao.getHallListById(id);
    }

    @Transactional
    public void saveSessionList(SessionList sessionList){
        extraDao.saveSessionList(sessionList);
    }

    @Transactional
    public List<Cost> getCosts(){
        return extraDao.getCosts();
    }

    @Transactional
    public List<HallList> getHallList(){
        return extraDao.getHallList();
    }
}
