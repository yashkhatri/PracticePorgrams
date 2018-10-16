package test1;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TransactionsSampleTest {

	@Test
	public void shouldReturnEmptyListIfThereIsNoTransactions() {
		assertThat(Transactions.findRejectedTransactions(new ArrayList<>(), 0).size(), is(0));
	}

	@Test
	public void shouldReturnEmptyListIfThereIsATransactionWithinCreditLimit() {
		List<String> transactions = Arrays.asList("John,Doe,john@doe.com,200,TR0001");

		List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

		assertThat(rejectedTransactions.size(), is(0));
	}

	@Test
	public void shouldReturnTransationThatIsOverCreditLimit() {
		List<String> transactions = Arrays.asList("John,Doe,john@doe.com,201,TR0001");

		List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

		assertThat(rejectedTransactions, is(Arrays.asList("TR0001")));
	}

	@Test
	public void shouldReturnTransationCummulativeIsOverCreditLimit() {
		List<String> transactions = Arrays.asList("John,Doe,john@doe.com,199,TR0001", "John,Doe,john@doe.com,2,TR0002");

		List<String> rejectedTransactions = Transactions.findRejectedTransactions(transactions, 200);

		assertThat(rejectedTransactions, is(Arrays.asList("TR0002")));
	}
	
	

}