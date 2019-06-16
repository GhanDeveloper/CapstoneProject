
package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.ItemDao;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ItemEntity> getItemByCategoryId(long restaurantId, long categoryId)  {

        List<ItemEntity> restaurantsByCategoryId =  itemDao.getItemByCategoryId(restaurantId, categoryId);

        /*if (categoryUuid.isEmpty()) {
            throw new CategoryNotFoundException("CNF-001", "Category id field should not be empty");
        }else if(restaurantsByCategoryId == null){
            throw new CategoryNotFoundException("CNF-002","No category by this id");
        }*/

        return restaurantsByCategoryId;
    }

}

