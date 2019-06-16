package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ItemEntity> getItemByCategoryId(long restaurantId, long categoryId){
        try{

            return this.entityManager.createNamedQuery("allItemBycategory", ItemEntity.class).setParameter("restaurantId", restaurantId).setParameter("categoryId", categoryId).getResultList();
        }catch (NoResultException nre){
            return null;
        }
    }

}
