package machine.states.manager;

import features.customizationAddons.CustomizationAddonUI;
import features.items.ItemUI;
import machine.AppMachine;
import machine.AppState;
import utils.io.InputQueueHandler;

import java.util.Objects;
import java.util.Scanner;

public class AddNewItemState implements AppState {

    private final ItemUI itemUI = new ItemUI();
    private final CustomizationAddonUI customizationAddonUI = new CustomizationAddonUI();

    private final String[] predefinedQuestions = {
            "(ex:- RootCategory->SubCategory1->...->TargetCategory) \nEnter Item Category Name: ",
            "Enter Item Name: ",
            "Enter Item Base Price: ",
            "Do you want to add customization addons to this item? (yes/no): "
    };

    private final String[] addonsPredefinedQuestions = {
            "What's this addon for?",
            "Is it mandatory? (yes/no): ",
            "Ex:- S:0, M:50, L:100 \nEnter the options available for the addon (Title:How much it changes the base price) (comma separated): "
    };

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void onEnter(AppMachine machine) {
        System.out.println("=== Add new Item ===");

        while (true) {
            try {
                InputQueueHandler iQ1 = new InputQueueHandler(predefinedQuestions);
                iQ1.takeInputs(sc);

                String categoryName = iQ1.poll();
                String itemName = iQ1.poll();
                float basePrice = Float.parseFloat(Objects.requireNonNull(iQ1.poll()));

                itemUI.addItem(itemName, basePrice, categoryName);

                String addAddonsChoice = iQ1.poll();


                if (addAddonsChoice.equals("yes")) {
                    InputQueueHandler iQ2 = new InputQueueHandler(addonsPredefinedQuestions);
                    iQ2.takeInputs(sc);
                    String addonName = iQ2.poll();
                    String isMandatoryInput = iQ2.poll();
                    boolean isMandatory = isMandatoryInput.equalsIgnoreCase("yes");
                    String addonOptions = iQ2.poll();
                    customizationAddonUI.addCustomizationAddon(addonName, itemName, isMandatory, addonOptions);
                }

                System.out.println("Item added successfully!");
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
