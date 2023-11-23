package com.example.FinanceTracker;

import com.example.FinanceTracker.Model.Category;
import com.example.FinanceTracker.Model.Company;
import com.example.FinanceTracker.Model.Transaction;
import com.example.FinanceTracker.Model.User;
import com.example.FinanceTracker.Repository.CompanyRepository;
import com.example.FinanceTracker.Service.CategoryService;
import com.example.FinanceTracker.Service.TransactionService;
import com.example.FinanceTracker.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FinanceTrackerApplicationTests {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TransactionService transactionService;

	@Test
	void contextLoads() {
	}
	@Test
	@Transactional
	public void createTestCompany() {
		// Create a sample Company object
		Company company = new Company();
		company.setName("Test Company");

		// Save the Company to the database
		companyRepository.save(company);

	}

	@Test
	public void savingCompany() {
		assertNotNull(companyRepository);
	}

	// Test for creating and saving a transaction
	@Test
	public void savingTransaction() {
		Transaction transaction = new Transaction();
		transaction.setAmount(BigDecimal.valueOf(10.0));
		transaction.setDescription("Electricity bill");
		transaction.setDate(LocalDate.of(2023, 11, 17));  // Fix the date format

		// Assuming you have a user with ID 1 and a category with ID 1 in your database
		Optional<User> optionalUser = userService.getUserById(1L);
		Category category = categoryService.getCategoryById(1L);

		// Check if the user and category exist
		if (optionalUser.isPresent() && category != null) {
			// Unwrap the Optional<User> to get the actual User
			User user = optionalUser.get();

			// Set the user and category for the transaction
			transaction.setUser(user);
			transaction.setCategory(category);

			// Save the transaction
			Transaction savedTransaction = transactionService.createTransaction(transaction);

			// Add assertions or checks as needed for your test
			assertNotNull(savedTransaction);
			assertNotNull(savedTransaction.getId());
		} else {
			// Handle the case where the user or category is not found
			fail("User or category not found");
		}
	}

	@Test
	public void testGetAllTransactions() {
		// Assuming you have some sample transactions in the test database
		List<Transaction> transactions = transactionService.getAllTransactions();

		// Assert that the list is not null and not empty
		assert transactions != null;
		assert !transactions.isEmpty();
	}

	@Test
	public void testDeleteTransaction() {
		// Get all transactions
		List<Transaction> transactions = transactionService.getAllTransactions();

		// Get the first transaction
		Transaction transactionToDelete = transactions.get(0);

		// Delete the transaction
		transactionService.deleteTransaction(transactionToDelete.getId());

		// Verify that the transaction has been deleted
		// You can check if the transaction is no longer present in the list or the database
		List<Transaction> updatedTransactions = transactionService.getAllTransactions();
		assert !updatedTransactions.contains(transactionToDelete);


	}


	}

