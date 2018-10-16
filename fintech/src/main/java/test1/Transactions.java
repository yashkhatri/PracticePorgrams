package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Transactions class contain methods related to transaction.
 * 
 * @author Yash Khatri
 */
public class Transactions {

	/**
	 * <p>
	 * This method takes list of transactions and creditLimit as parameters, checks
	 * whether a transaction is possible based on the creditLimit of the user.
	 * </p>
	 * 
	 * @param transactions List of transaction, containing details about
	 *                     transaction. .first name, last name, email, transaction
	 *                     amount, transaction id.
	 * @param creditLimit  The credit limit of an unique user.
	 * @return listOfRejectedTransactions List of Rejected Transactions -If the list
	 *         of transactions is empty, an empty list is returned. -If the
	 *         transaction is rejected, the transaction id of all the rejected
	 *         transactions are added in the list and returned. -If no transaction
	 *         is rejected, an empty list is returned.
	 */
	public static List<String> findRejectedTransactions(List<String> transactions, int creditLimit) {

		int availableLimit = creditLimit;
		Map<String, Integer> consumersMap = new HashMap<String, Integer>();
		List<String> listOfRejectedTransactions = new ArrayList<String>();

		// If no transactions are there, return an empty list.
		if (transactions.size() == 0) {
			return listOfRejectedTransactions;
		} else {
			for (String transaction : transactions) {
				List<String> transacitionDetails = Arrays.asList(transaction.split(","));
				// Transactions Details MUST have 5 parameters. Else, ignore this transaction.
				if (transacitionDetails.size() == 5) {
					String consumerID = transacitionDetails.get(0) + "_" + transacitionDetails.get(1) + "_"
							+ transacitionDetails.get(2);
					Integer transactionAmount = Integer.parseInt(transacitionDetails.get(3));
					String transactionID = transacitionDetails.get(4);

					if (consumersMap.containsKey(consumerID)) {
						availableLimit = consumersMap.get(consumerID);
					} else {
						availableLimit =  creditLimit;
					}
						if (availableLimit >= transactionAmount) {
							availableLimit = availableLimit - transactionAmount;
						} else {
							listOfRejectedTransactions.add(transactionID);
						}

					consumersMap.put(consumerID, availableLimit);
				}
			}
		}

		return listOfRejectedTransactions;
	}
}