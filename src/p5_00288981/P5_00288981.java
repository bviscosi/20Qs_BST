package p5_00288981;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class P5_00288981 {

    public static void main(String[] args) {
        File file = new File("tree.txt");
        BTree twentyQs = new BTree(file);
        twentyQs.play();
        printToFile(twentyQs, file);

    }

    public static String preOrder(LinkedNode root) {
        String tree = "";

        if (root == null) {

        } else {
            tree += root.getData() + "\n";

            if (root.isLeaf()) {
                tree += "*" + "\n";
            } else {
                tree += preOrder(root.getLeft());
                tree += preOrder(root.getRight());
            }

        }
        return tree;
    }

    public static void printToFile(BTree q, File file) {
        PrintWriter sout;
        System.out.println("CURRENT TREE:");

        try {
            sout = new PrintWriter(file);

            sout.println(preOrder(q.root)); //writes current tree data to file
            sout.close();

            Scanner scan = new Scanner(file);

            System.out.println("");

            while (scan.hasNext()) {
                System.out.println(scan.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File failure");
        }

    }

}
