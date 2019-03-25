package p5_00288981;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BTree {

    protected LinkedNode root = new LinkedNode();
    protected LinkedNode curr = root;
    protected Scanner scan = new Scanner(System.in);
    protected String guesses = "";
    protected boolean gameOver = false;
    protected int size = 0;

    public BTree(File tree) {
        buildFromFile(tree);
    }

    public void clear() {
        root = new LinkedNode();
        curr = root;
        size = 0;
        guesses = "";
        gameOver = false;
    }

    public void play() {

        System.out.println("Welcome to 20 Questions...");
        System.out.println("Think of something - anything -  and answer the following yes or no questions...");
        System.out.println("enter y for yes or n for no...");

        int i = 0;
        while (gameOver == false && i < 20) {
            ask();
            i++;
        }
        if (gameOver == false) {
            System.out.println("You win!!!");
        }

    }

    public void ask() {

        System.out.println(curr.getData());
        guesses = guesses + "\n" + curr.getData();
        String yesOrNo = scan.nextLine();

        if ((yesOrNo.equals("y") || yesOrNo.equals("Y")) && curr.getRight() != null) {
            
            curr = curr.getRight();

        } else if ((yesOrNo.equals("n") || yesOrNo.equals("N")) && curr.getLeft() != null) {
            
            curr = curr.getLeft();

        } else if ((yesOrNo.equals("y") || yesOrNo.equals("Y")) && curr.getRight() == null) {
            
            gameOver = true;
            System.out.println("My final guess is: " + curr.getData());
            System.out.println("Was this what you were thinking of?");
            yesOrNo = scan.nextLine();
            
            if (yesOrNo.equals("n") || yesOrNo.equals("N")) {
                stumpedMe();   
            } else {

                System.out.println("I win!!!");
                System.out.println("\n" + "THIS GAME'S GUESSES:");
                System.out.println(guesses + "\n");
            }

        } else if ((yesOrNo.equals("n") || yesOrNo.equals("N")) && curr.getLeft() == null) {
            
            stumpedMe();

        }

    }

    public void stumpedMe() {
        
        String answer;
        gameOver = true;
        System.out.println("You've stumped me..." + "\n" + "What were you thinking of?");
        answer = scan.nextLine();
        System.out.println("Enter a yes/no question that differentiates your answer from mine. (yes should lead to your answer)");
        String newQuestion = scan.nextLine();
        guesses += "\n" + newQuestion;
        curr.setLeft(new LinkedNode(curr.getData(), curr));
        size++;
        curr.setData(newQuestion);
        curr.setRight(new LinkedNode("Is it " + answer + "?", curr));
        size++;

        System.out.println(guesses + "\n" + "ANSWER: " + answer);
    }

    public int length() {
        return size;
    }

    public void buildFromFile(File file) {
        clear();

        try {

            Scanner scnr = new Scanner(file);
            String data = "";

            if (scnr.hasNext()) {
                data = scnr.nextLine();
                curr.setData(data);
                size++;
                data = scnr.nextLine();
            }

            while (scnr.hasNext()) {

                if (curr.getLeft() == null && !data.equals("*")) {
                    curr.setLeft(new LinkedNode(data, curr));
                    curr = curr.getLeft();
                    size++;
                    data = scnr.nextLine();
                } else if (curr.getLeft() != null && curr.getRight() == null && !data.equals("*")) {
                    curr.setRight(new LinkedNode(data, curr));
                    curr = curr.getRight();
                    size++;
                    data = scnr.nextLine();
                } else if (curr.getLeft() != null && curr.getRight() != null && !data.equals("*")) {
                    curr = curr.getParent();
                } else if (data.equals("*")) {
                    curr = curr.getParent();
                    data = scnr.nextLine();
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BTree.class.getName()).log(Level.SEVERE, null, ex);
        }

        curr = root;

    }
}
