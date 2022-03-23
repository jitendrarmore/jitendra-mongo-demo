package com.jaikalubai.jaikalubai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class JaikalubaiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JaikalubaiApplication.class, args);
	}

	@Autowired
	ItemRepository contactItemRepo;

	@Autowired
	CustomItemRepository customRepo;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");

		createContactItems();

		System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");

		showAllContactInfo();

		System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");

		getGroceryItemByName("Whole Wheat Biscuit");

		System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");

		getItemsByCategory("millets");

		System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");

		updateCategoryName("snacks");

		System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");

		deleteGroceryItem("Kodo Millet");

		System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");

		findCountOfGroceryItems();

		System.out.println("\n-------------------THANK YOU---------------------------");

	}


	List<ContactItem> itemList = new ArrayList<ContactItem>();

	// CRUD operations



	void createContactItems(){
		System.out.println("Data creation started...");

		contactItemRepo.save(new ContactItem(1, "Jitendra", "More", "9833550438",
				"PMKISAN", "13/01/2022"));
		contactItemRepo.save(new ContactItem(1, "Jayshree", "More", "8369298230",
				"PMKISAN", "13/01/2022"));
		contactItemRepo.save(new ContactItem(1, "Jatin", "More", "9833550438",
				"PMKISAN", "13/01/2022"));
		contactItemRepo.save(new ContactItem(1, "Jahnavi", "More", "9833550438",
				"PMKISAN", "13/01/2022"));


		System.out.println("Data creation complete...");

	}


	// READ
	// 1. Show all the data

	public void showAllContactInfo(){

		itemList = contactItemRepo.findAll();

		itemList.forEach(item -> System.out.println(getItemDetails(item)));
	}

	// 2. Get item by name
	public void getGroceryItemByName(String firstName) {
		System.out.println("Getting item by name: " + firstName);
		ContactItem item = contactItemRepo.findContactItemBy(firstName);
		System.out.println(getItemDetails(item));
	}


	// 3. Get name and items of a all items of a particular category
	public void getItemsByCategory(String category) {
		System.out.println("Getting items for the category " + category);
		List<ContactItem> list = contactItemRepo.findAll(category);

		list.forEach(item -> System.out.println("Name: " + item.getFirstName() + ", Quantity: " + item.getMobileNumber()));
	}


	// 4. Get count of documents in the collection
	public void findCountOfGroceryItems() {
		long count = contactItemRepo.count();
		System.out.println("Number of documents in the collection = " + count);
	}


	// UPDATE APPROACH 1: Using MongoRepository
	public void updateCategoryName(String category) {

		// Change to this new value
		String newCategory = "PMKISAN";

		// Find all the items with the category
		List<ContactItem> list = contactItemRepo.findAll(category);

		list.forEach(item -> {
			// Update the category in each document
			item.setCatagoty(newCategory);
		});

		// Save all the items in database
		List<ContactItem> itemsUpdated = contactItemRepo.saveAll(list);

		if(itemsUpdated != null)
			System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
	}


	// UPDATE APPROACH 2: Using MongoTemplate
	public void updateItemQuantity(String name, float newQuantity) {
		System.out.println("Updating quantity for " + name);
		customRepo.updateItemQuantity(name, newQuantity);
	}

	// DELETE
	public void deleteGroceryItem(String id) {
		contactItemRepo.deleteById(id);
		System.out.println("Item with id " + id + " deleted...");
	}
	// Print details in readable form



	// Print details in readable form
	public String getItemDetails(ContactItem item) {

		System.out.println(
				"Item First Name: " + item.getFirstName() +
						"Item Last Name: " + item.getLastName() +
						"Item date: " + item.getDate() +
						"Item Serial Name: " + item.getSerialNumber() +
						", \nItem MobileNumber: " + item.getMobileNumber() +
						", \nItem Category: " + item.getCatagoty()
		);

		return "";
	}
}
