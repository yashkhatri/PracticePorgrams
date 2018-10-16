package test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>Smoothie</code>. contain methods related to processing the order.
 */
class Smoothie {

	static Map<String, List<String>> menu = new HashMap<String, List<String>>();

	/**
	 * <p>
	 * Populate menu map.
	 * This method is used for populating the menu items.
	 * </p>
	 */
	public static void populateMenuMap() {

		menu.put("Classic",
				new LinkedList<String>(Arrays.asList("strawberry", "banana", "pineapple", "mango", "peach", "honey")));
		menu.put("Freezie", new LinkedList<String>(
				Arrays.asList("blackberry", "blueberry", "black currant", "grape juice", "frozen yogurt")));
		menu.put("Greenie", new LinkedList<String>(
				Arrays.asList("green apple", "lime", "avocado", "spinach", "ice", "apple juice")));
		menu.put("Just Desserts",
				new LinkedList<String>(Arrays.asList("banana", "ice cream", "chocolate", "peanut", "cherry")));
	}

	/**
	 * <p>
	 * Ingredients.
	 * 
	 * This method is used for processing the orders of smoothies.
	 * </p>
	 *
	 * @param order 
	 * 			The order string contains a name of smoothie from the menu. It may additionally contain 
	 * some ingredients that should be removed from the final order details every item is comma separated. 
	 * 
	 * If the order string is null, empty, contains ingredients to add, smoothie not from the menu, an
	 * <code>IllegalArgumentException</code> exception is thrown.
	 * 
	 * @return finalOrder
	 * 		   final order that contains details of the ingredients that should be added in the smoothie.
	 */
	public static String ingredients(String order) {
		// Refreshing the Menu.
		populateMenuMap();

		if (order == null || order.isEmpty()) {
			throw new IllegalArgumentException();
		}

		// Order String is in CSV format. Splitting and saving it to a list.
		List<String> orderDetails = Arrays.asList(order.split(","));

		// If the item is not from the menu, throw and exception.
		if (!menu.containsKey(orderDetails.get(0))) {
			throw new IllegalArgumentException();
		} else {

			// First element is always the Smoothie name! order details should not be empty
			// here.
			// Hence exception safe.
			List<String> ingredientsList = menu.get(orderDetails.get(0));

			// Iterating on the ingredients of the order details (if any) to remove it from
			// the final order.
			for (String ingredient : orderDetails.subList(1, orderDetails.size())) {

				// The ingredients to be removed begins with '-'
				if (ingredient.startsWith("-")) {
					ingredient = ingredient.replace("-", "");
					ingredientsList.remove(ingredient);
				} else { // No adding of the ingredients is supported.
					throw new IllegalArgumentException();
				}
			}

			// Sorting the ingredient list alphabetically.
			java.util.Collections.sort(ingredientsList);
			String finalOrder = String.join(",", ingredientsList);

			return finalOrder;
		}
	}
}