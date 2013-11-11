package com.jinva.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jinva.bean.datamodel.Dish;
import com.jinva.bean.datamodel.Group;
import com.jinva.bean.datamodel.OrderProvider;
import com.jinva.bean.datamodel.Restaurant;
import com.jinva.bean.datamodel.UserGroup;
import com.jinva.consts.JinvaConsts;
import com.jinva.service.JinvaService;

public class AjaxanywhereAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8360403433504826681L;
	

	@Autowired
	private JinvaService jinvaService;
	
	public String execute() throws Exception {
		logger.debug("aa_execute");
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String groupList() throws InterruptedException{
		String userId = getUserId();
		List<Group> myGroupList = jinvaService.getMyGroupList(0, -1, userId);
		List<Group> joinedGroupList = jinvaService.getJoinedGroupList(0, -1, userId);
		List<Group> otherGroupList = jinvaService.getOtherGroupList(0, -1, userId);
		Map<String, String> cache = new HashMap<String, String>();
		jinvaService.parseGroupOwnerName(myGroupList, cache);
		jinvaService.parseGroupOwnerName(joinedGroupList, cache);
		jinvaService.parseGroupOwnerName(otherGroupList, cache);
		jinvaService.parseGroupMemberCount(myGroupList);
		jinvaService.parseGroupMemberCount(joinedGroupList);
		jinvaService.parseGroupMemberCount(otherGroupList);
		request.setAttribute("myGroupList", myGroupList);
		request.setAttribute("joinedGroupList", joinedGroupList);
		request.setAttribute("otherGroupList", otherGroupList);
		setPage("jsp/main/dining");
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String restaurantList() throws InterruptedException{
		String userId = getUserId();
		List<Restaurant> myRestaurantList = jinvaService.getMyRestaurantList(0, -1, userId);
		List<Restaurant> otherRestaurantList = jinvaService.getOtherRestaurantList(0, -1, userId);
		request.setAttribute("myRestaurantList", myRestaurantList);
		request.setAttribute("otherRestaurantList", otherRestaurantList);
		setPage("jsp/main/dining");
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String orderProviderList() throws InterruptedException{
		List<OrderProvider> orderProviderList = jinvaService.getOrderProviderList(0, -1, getUserId());
		jinvaService.parseOrderProviderName(orderProviderList, new HashMap<String, String>());
		jinvaService.parseOrderProviderGroup(orderProviderList);
		jinvaService.parseOrderProviderRestaurant(orderProviderList, new HashMap<String, String>());
		request.setAttribute("orderProviderList", orderProviderList);
		setPage("jsp/main/dining");
		return SUCCESS;
	}
	
	public String dishList() throws InterruptedException{
		String restaurantId = request.getParameter("restaurantId");
		return dishList(restaurantId);
	}
	
	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	private String dishList(String restaurantId) throws InterruptedException{
		Restaurant restaurant = jinvaService.get(Restaurant.class, restaurantId);
		List<Dish> dishList = jinvaService.getDishList(0, -1, restaurantId);
		Map<String, Dish> dishMap = jinvaService.parseDishChildren(dishList);
		request.setAttribute("dishList", dishList);
		request.setAttribute("dishMap", dishMap);
		request.setAttribute("restaurant", restaurant);
		setPage("jsp/main/dish");
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public String saveGroup() throws InterruptedException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		Map<String, Object> parameterMap = getValidParameterMap("group");
		String id = toString(parameterMap.get(JinvaConsts.ID));
		if (StringUtils.isEmpty(id)) {// insert
			Group group = Group.class.newInstance();
			BeanUtils.populate(group, parameterMap);
			group.setOwnerId(getUserId());
			String groupId = (String) jinvaService.save(group);
			UserGroup userGroup = new UserGroup();
			userGroup.setEnterDate(new Date());
			userGroup.setGroupId(groupId);
			userGroup.setUserId(getUserId());
			jinvaService.save(userGroup);
		} else {//update
			Group group = jinvaService.get(Group.class, id);
			BeanUtils.populate(group, parameterMap);
			jinvaService.update(group);
		}
		return groupList();
	}
	
	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public String saveRestaurant() throws InterruptedException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		Map<String, Object> parameterMap = getValidParameterMap("restaurant");
		String id = toString(parameterMap.get(JinvaConsts.ID));
		if (StringUtils.isEmpty(id)) {// insert
			Restaurant restaurant = Restaurant.class.newInstance();
			BeanUtils.populate(restaurant, parameterMap);
			restaurant.setOwnerId(getUserId());
			jinvaService.save(restaurant);
		} else {//update
			Restaurant restaurant = jinvaService.get(Restaurant.class, id);
			BeanUtils.populate(restaurant, parameterMap);
			jinvaService.update(restaurant);
		}
		return restaurantList();
	}
	
	public String saveDish() throws InstantiationException, IllegalAccessException, InvocationTargetException, InterruptedException{
		Map<String, Object> parameterMap = getValidParameterMap("dish");
		String id = toString(parameterMap.get(JinvaConsts.ID));
		Dish dish = null;
		if (StringUtils.isEmpty(id)) {// insert
			dish = Dish.class.newInstance();
			BeanUtils.populate(dish, parameterMap);
			jinvaService.save(dish);
		} else {//update
			dish = jinvaService.get(Dish.class, id);
			BeanUtils.populate(dish, parameterMap);
			jinvaService.update(dish);
		}
		return dishList(dish.getRestaurantId());
	}
	
	public String deleteDish() throws InterruptedException{
		String id = request.getParameter("id");
		Dish dish = jinvaService.get(Dish.class, id);
		String restaurantId = null;
		if(dish != null){
			restaurantId = dish.getRestaurantId();
			jinvaService.delete(dish);
		}
		return dishList(restaurantId);
	}
	
	public String joinGroup() throws InterruptedException{
		String groupId = request.getParameter("groupId");
		UserGroup userGroup = new UserGroup();
		userGroup.setEnterDate(new Date());
		userGroup.setGroupId(groupId);
		userGroup.setUserId(getUserId());
		jinvaService.save(userGroup);
		return groupList();
	}
	
	public String quitGroup() throws InterruptedException{
		String groupId = request.getParameter("groupId");
		jinvaService.deleteUserGroup(getUserId(), groupId);
		return groupList();
	}
	
	public String deleteGroup() throws InterruptedException{
		String groupId = request.getParameter("groupId");
		jinvaService.delete(Group.class, groupId);
		jinvaService.delete(UserGroup.class, new String[]{"groupId"}, new Object[]{groupId});
		return groupList();
	}
	
	public String main() throws Exception{
//		Thread.sleep(1000);
		request.setAttribute("test", session.getAttribute(JinvaConsts.USER_NAME));
		String result = "";
		result += DigestUtils.md5Hex("jinn");
		request.setAttribute("result", result);
		setPage("jsp/main/dining");
		return SUCCESS;
	}
	
	

}