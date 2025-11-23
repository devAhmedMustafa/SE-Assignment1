package machine.states.manager;

import features.items.ItemUI;
import machine.AppMachine;
import machine.AppState;

import java.util.Scanner;

public class AddNewCategoryState implements AppState {

    private final ItemUI itemUI = new ItemUI();

    @Override
    public void onEnter(AppMachine machine) {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);

                System.out.println("=== Add New Category ===");
                System.out.print("Enter Category Name: ");
                String categoryName = sc.nextLine();

                System.out.println("(ex:- RootCategory->SubCategory1->ParentCategory)");
                System.out.print("Enter Item Category Parent (Leave Blank if it has no parent): ");
                String parentCategory = sc.nextLine();

                if (parentCategory.isBlank()) {
                    itemUI.addCategory(categoryName);
                }
                else {
                    itemUI.addCategory(categoryName, parentCategory);
                }

                System.out.println("Category added successfully!");

                break;
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        machine.back();
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
