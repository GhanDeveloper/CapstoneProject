package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.*;
//import com.upgrad.FoodOrderingApp.service.businness.ItemService;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//RestController annotation specifies that this class represents a REST API(equivalent of @Controller + @ResponseBody)
@RestController
//"@CrossOrigin” annotation enables cross-origin requests for all methods in that specific controller class.
@CrossOrigin
@RequestMapping("/")
public class RestaurantController {

    //Required services are autowired to enable access to methods defined in respective Business services
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StateService stateService;

    @Autowired
    private AddressService addressService;


    @RequestMapping(method = RequestMethod.GET, path = "/restaurant",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getAllRestaurants(){

        List<RestaurantEntity> restaurantEntityList = restaurantService.getRestaurants();

       /* final RestaurantListResponse restaurantListResponse = new RestaurantListResponse();
        for (RestaurantEntity restaurantEntity : restaurantEntityList) {

            final RestaurantList restaurantList = new RestaurantList();
            restaurantList.id(UUID.fromString(restaurantEntity.getUuid()));
            restaurantList.restaurantName(restaurantEntity.getRestaurantName());
            restaurantList.photoURL(restaurantEntity.getPhotoUrl());
            restaurantList.customerRating(restaurantEntity.getCustomerRating());
            restaurantList.averagePrice(restaurantEntity.getAveragePriceForTwo());
            restaurantList.numberCustomersRated(restaurantEntity.getNumberOfCustomersRated());



            final AddressEntity addressEntity = addressService.getAddressById(restaurantEntity.getAddress().getId());
            final RestaurantDetailsResponseAddress restaurantDetailsResponseAddress = new RestaurantDetailsResponseAddress();
            restaurantDetailsResponseAddress.id(UUID.fromString(addressEntity.getUuid()));
            restaurantDetailsResponseAddress.city(addressEntity.getCity());
            restaurantDetailsResponseAddress.flatBuildingName(addressEntity.getFlatBuilNumber());
            restaurantDetailsResponseAddress.locality(addressEntity.getLocality());
            restaurantDetailsResponseAddress.pincode(addressEntity.getPinCode());


            final StateEntity stateEntity =addressService.getStateById(restaurantEntity.getAddress().getState().getId());
            final RestaurantDetailsResponseAddressState restaurantDetailsResponseAddressState = new RestaurantDetailsResponseAddressState();
            restaurantDetailsResponseAddressState.id(UUID.fromString(stateEntity.getUuid()));
            restaurantDetailsResponseAddressState.stateName(stateEntity.getStateName());
            restaurantDetailsResponseAddress.state(restaurantDetailsResponseAddressState);
            restaurantList.setAddress(restaurantDetailsResponseAddress);


            List<CategoryEntity> categoryEntityList = categoryService.getCategoriesByRestaurant(restaurantEntity.getUuid());
            CategoryList categoryList = new CategoryList();
            List<String> arrayList = new ArrayList<>();
            for(CategoryEntity categoryEntity : categoryEntityList){
                //categoryList.categoryName(categoryEntity.getCategoryName());
                //restaurantList.categories(categoryList.getCategoryName());
                categoryList.categoryName(categoryEntity.getCategoryName());
                arrayList.add(categoryList.getCategoryName());
            }

            restaurantList.categories(arrayList.toString());


            restaurantListResponse.addRestaurantsItem(restaurantList);
        }*/

        RestaurantListResponse restaurantResponse = Restaurantlist(restaurantEntityList);

        return new ResponseEntity<RestaurantListResponse>(restaurantResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurant/name/{reastaurant_name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getRestaurantById(@PathVariable("reastaurant_name") final String reastaurantName) throws RestaurantNotFoundException {


        final List<RestaurantEntity> allRestaurant = restaurantService.getRestaurantsByName(reastaurantName);


        RestaurantListResponse restaurantResponse = Restaurantlist(allRestaurant);

        return new ResponseEntity<RestaurantListResponse>(restaurantResponse, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurant/category/{category_id}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getRestaurantByCategoryId(@PathVariable("category_id") final String categoryUuid) throws CategoryNotFoundException {


        final List<RestaurantEntity> allRestaurant = restaurantService.getRestaurantByCategoryId(categoryUuid);


        RestaurantListResponse restaurantResponse = Restaurantlist(allRestaurant);

        return new ResponseEntity<RestaurantListResponse>(restaurantResponse, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/api/restaurant/{restaurant_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantDetailsResponse>> getRestaurantByRestaurantId(@PathVariable("restaurant_id") final String restaurantUuid) throws RestaurantNotFoundException {
        final List<RestaurantEntity> restaurantByRestaurantId = restaurantService.getRestaurantByRestaurantId(restaurantUuid);

        List<RestaurantDetailsResponse> restaurantResponse = allResponseList(restaurantByRestaurantId);

        return new ResponseEntity<List<RestaurantDetailsResponse>>(restaurantResponse, HttpStatus.OK);
    }

    public RestaurantListResponse Restaurantlist(List<RestaurantEntity> allRestaurant){

        final RestaurantListResponse restaurantListResponse = new RestaurantListResponse();
        for (RestaurantEntity restaurantEntity : allRestaurant) {

            final RestaurantList restaurantList = new RestaurantList();
            restaurantList.id(UUID.fromString(restaurantEntity.getUuid()));
            restaurantList.restaurantName(restaurantEntity.getRestaurantName());
            restaurantList.photoURL(restaurantEntity.getPhotoUrl());
            restaurantList.customerRating(restaurantEntity.getCustomerRating());
            restaurantList.averagePrice(restaurantEntity.getAveragePriceForTwo());
            restaurantList.numberCustomersRated(restaurantEntity.getNumberOfCustomersRated());



            final AddressEntity addressEntity = addressService.getAddressById(restaurantEntity.getAddress().getId());
            final RestaurantDetailsResponseAddress restaurantDetailsResponseAddress = new RestaurantDetailsResponseAddress();
            restaurantDetailsResponseAddress.id(UUID.fromString(addressEntity.getUuid()));
            restaurantDetailsResponseAddress.city(addressEntity.getCity());
            restaurantDetailsResponseAddress.flatBuildingName(addressEntity.getFlatBuilNumber());
            restaurantDetailsResponseAddress.locality(addressEntity.getLocality());
            restaurantDetailsResponseAddress.pincode(addressEntity.getPinCode());


            final StateEntity stateEntity =addressService.getStateById(restaurantEntity.getAddress().getState().getId());
            final RestaurantDetailsResponseAddressState restaurantDetailsResponseAddressState = new RestaurantDetailsResponseAddressState();
            restaurantDetailsResponseAddressState.id(UUID.fromString(stateEntity.getUuid()));
            restaurantDetailsResponseAddressState.stateName(stateEntity.getStateName());
            restaurantDetailsResponseAddress.state(restaurantDetailsResponseAddressState);
            restaurantList.setAddress(restaurantDetailsResponseAddress);


            List<CategoryEntity> categoryEntityList = categoryService.getCategoriesByRestaurant(restaurantEntity.getUuid());
            CategoryList categoryList = new CategoryList();
            List<String> arrayList = new ArrayList<>();
            for(CategoryEntity categoryEntity : categoryEntityList){
                //categoryList.categoryName(categoryEntity.getCategoryName());
                //restaurantList.categories(categoryList.getCategoryName());
                categoryList.categoryName(categoryEntity.getCategoryName());
                arrayList.add(categoryList.getCategoryName());
            }

            restaurantList.categories(arrayList.toString());


            restaurantListResponse.addRestaurantsItem(restaurantList);

        }
        return restaurantListResponse;
    }

    public List<RestaurantDetailsResponse> allResponseList(List<RestaurantEntity> allRestaurant){

        List<RestaurantDetailsResponse> listofrestaurant = new ArrayList<>();

        for ( RestaurantEntity restaurantEntity : allRestaurant){
            RestaurantDetailsResponse Response = new RestaurantDetailsResponse();
            Response.id(UUID.fromString(restaurantEntity.getUuid()));
            Response.restaurantName(restaurantEntity.getRestaurantName());
            Response.photoURL(restaurantEntity.getPhotoUrl());
            Response.customerRating(restaurantEntity.getCustomerRating());
            Response.averagePrice(restaurantEntity.getAveragePriceForTwo());
            Response.numberCustomersRated(restaurantEntity.getNumberOfCustomersRated());
            AddressEntity addressEntity = addressService.getAddressById(restaurantEntity.getAddress().getId());

            RestaurantDetailsResponseAddress responseAddress = new RestaurantDetailsResponseAddress();
            responseAddress.id(UUID.fromString(addressEntity.getUuid()));
            responseAddress.flatBuildingName(addressEntity.getFlatBuilNumber());
            responseAddress.locality(addressEntity.getLocality());
            responseAddress.city(addressEntity.getCity());
            responseAddress.pincode(addressEntity.getPinCode());

            StateEntity stateEntity =  stateService.getStateById(restaurantEntity.getAddress().getState().getId());

            RestaurantDetailsResponseAddressState responseAddressState = new RestaurantDetailsResponseAddressState();
            responseAddressState.id(UUID.fromString(stateEntity.getUuid()));
            responseAddressState.stateName(stateEntity.getStateName());
            responseAddress.state(responseAddressState);
            Response.address(responseAddress);



            List<CategoryList> categoryLists = new ArrayList<>();
            List<CategoryEntity> categoryEntityList = categoryService.getCategoriesByRestaurant(restaurantEntity.getUuid());
            for(CategoryEntity categoryEntity : categoryEntityList){
                CategoryList categoryList = new CategoryList();
                categoryList.id(UUID.fromString(categoryEntity.getUuid()));
                categoryList.categoryName(categoryEntity.getCategoryName());


                List<ItemEntity> itemLists = itemService.getItemByCategoryId(restaurantEntity.getId(),categoryEntity.getId());
                for(ItemEntity itemEntity : itemLists) {
                    ItemList itemList = new ItemList();
                    itemList.id(UUID.fromString(itemEntity.getUuid()));
                    itemList.itemName(itemEntity.getItemName());
                    itemList.price(itemEntity.getPrice());
                    itemList.itemType(ItemList.ItemTypeEnum.fromValue(itemEntity.getType()));
                   categoryList.addItemListItem(itemList);
               }
                categoryLists.add(categoryList);
            }

            Response.categories(categoryLists);

            listofrestaurant.add(Response);
        }
        return listofrestaurant;
    }

}
