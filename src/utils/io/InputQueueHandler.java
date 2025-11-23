package utils.io;

import java.util.LinkedList;
import java.util.Queue;

public class InputQueueHandler {

    private String undoCommand = ":undo";

    private final Queue<String> inputQueue = new LinkedList<>();
    private int questionIndex = 0;
    private final String[] predefinedQuestions;

    public InputQueueHandler(String[] predefinedQuestions) {
        this.predefinedQuestions = predefinedQuestions;
        System.out.println("You can type '" + undoCommand + "' to go back to the previous question.");
    }

    public InputQueueHandler() {
        this.predefinedQuestions = new String[0];
    }

    public void takeInputs(java.util.Scanner sc) {
        while (questionIndex < predefinedQuestions.length) {
            System.out.print(predefinedQuestions[questionIndex]);
            String answer = sc.nextLine().trim();

            if (answer.equalsIgnoreCase(undoCommand)) {
                if (!inputQueue.isEmpty()) {
                    inputQueue.poll();
                    questionIndex = Math.max(0, questionIndex - 1);
                } else {
                    System.out.println("No previous input to redo.");
                    questionIndex = 0;
                }
                continue;
            }

            inputQueue.add(answer);
            questionIndex++;
        }
    }

    public String poll() {
        return inputQueue.poll();
    }

    public void reset() {
        inputQueue.clear();
        questionIndex = 0;
    }

}
