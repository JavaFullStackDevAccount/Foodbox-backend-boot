package com.foodbox.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foodbox.dao.FoodBoxOrdersDao;
import com.foodbox.entities.FoodItem;
import com.foodbox.entities.FoodboxOrders;
import com.foodbox.models.CreateOrderRequestModel;
import com.foodbox.models.OrdersResponseModel;
import com.foodbox.utils.CommonUtils;

@Service
public class FoodBoxOrdersService {

	@Autowired
	private FoodBoxOrdersDao foodBoxOrdersDao;

	public List<OrdersResponseModel> getAllOrdersOfUser(long userId) {

		try {

			List<FoodboxOrders> allItemsInAllOrdersByUser = this.foodBoxOrdersDao.findOrdersByfoodBoxUserId(userId,
					Sort.by("orderUuid"));
			
			
			System.out.println("Number of items ordered by user -> "+ allItemsInAllOrdersByUser.size());
			FoodboxOrders terminatingOrder = new FoodboxOrders();
			
			terminatingOrder.setOrderUuid("terminating id");
			
			allItemsInAllOrdersByUser.add(terminatingOrder);
			
			System.out.println(allItemsInAllOrdersByUser);
			
			if (allItemsInAllOrdersByUser != null) {

				String prevOrderId = "";

				OrdersResponseModel ordersResponse = new OrdersResponseModel();

				double undiscountedTotalAmountToBePaid = 0.0;


				List<OrdersResponseModel> finalOrdersList = new ArrayList<OrdersResponseModel>();

				for (FoodboxOrders orderItem : allItemsInAllOrdersByUser) {

					if (!prevOrderId.equals(orderItem.getOrderUuid()) ) {

						ordersResponse.setTotal(undiscountedTotalAmountToBePaid);

						ordersResponse.setTotalPayable(Math.abs(undiscountedTotalAmountToBePaid - ((undiscountedTotalAmountToBePaid + 12) / 100 )* 10));

						prevOrderId = orderItem.getOrderUuid();

						finalOrdersList.add(ordersResponse);

						ordersResponse = new OrdersResponseModel();
						
						undiscountedTotalAmountToBePaid = 0.0;
						
						ordersResponse.setOrderId(orderItem.getOrderUuid());

						ordersResponse.setDiscount(10);

						ordersResponse.setDeliveryCharges(12);

						undiscountedTotalAmountToBePaid += orderItem.getPrice();

						

						List<FoodItem> existingItemsInOrder = ordersResponse.getItemsInOrder();

						if (existingItemsInOrder == null)

							existingItemsInOrder = new ArrayList<FoodItem>();

						existingItemsInOrder.add(orderItem);

						ordersResponse.setItemsInOrder(existingItemsInOrder);


					} else {

						ordersResponse.setOrderId(orderItem.getOrderUuid());

						ordersResponse.setDiscount(10);

						ordersResponse.setDeliveryCharges(12);

						undiscountedTotalAmountToBePaid += orderItem.getPrice();


						List<FoodItem> existingItemsInOrder = ordersResponse.getItemsInOrder();

						if (existingItemsInOrder == null)

							existingItemsInOrder = new ArrayList<FoodItem>();

						existingItemsInOrder.add(orderItem);

						ordersResponse.setItemsInOrder(existingItemsInOrder);

					}

				}
				
				finalOrdersList.remove(0);
				
				return finalOrdersList;
				
			} 
			else
				throw new Exception("No items no orders");

		} catch (Exception e) {

			e.printStackTrace();
			
			return new ArrayList<OrdersResponseModel>();
		}


	}

	public boolean createNewUserOrder(long userId, List<FoodboxOrders> list) {

		try {
			String orderUUID = CommonUtils.getUUID();

			list.forEach(orderItem -> {

				FoodboxOrders newOrderToAdd = new FoodboxOrders();

				newOrderToAdd.setOrderUuid(orderUUID);

				newOrderToAdd.setFoodBoxUserId(userId);

				newOrderToAdd.setOrderedOn(new Date().toString());

				newOrderToAdd.setTitle(orderItem.getTitle());

				newOrderToAdd.setDescription(orderItem.getDescription());

				newOrderToAdd.setCategory(orderItem.getCategory());

				newOrderToAdd.setDiscount(orderItem.getDiscount());

				newOrderToAdd.setImageUrl(orderItem.getImageUrl());

				newOrderToAdd.setPrice(orderItem.getPrice());

				newOrderToAdd.setRating(orderItem.getRating());

				newOrderToAdd.setPublicVisiblity(true);

				this.foodBoxOrdersDao.save(newOrderToAdd);

			});

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

}
