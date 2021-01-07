package rbtree;

import javafx.application.Application;
import javafx.stage.Stage;
import rbtree.RedBlackTree;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// launch(args);

		
		//TEST1
		Integer[] myArray1 = { 53,90,92,62,75,38,70,36,52,32 };
		RedBlackTree<Integer> one = new RedBlackTree<>(myArray1);
		System.out.println("Tree One : ");
		System.out.println();
		one.printTree();
		System.out.println();
			
		/*
		//TEST2
		System.out.println();
		Integer[] myArray2 = { 10, 20, -10, 15, 17, 40,50,60 };
		RedBlackTree<Integer> two = new RedBlackTree<>(myArray2);
		System.out.println("Tree Two : ");
		System.out.println();
		two.printTree();
		System.out.println();
		
		//TEST3
		System.out.println();
		Integer[] myArray3 = { 4, 5, 1,3,2 };
		RedBlackTree<Integer> three = new RedBlackTree<>(myArray3);
		System.out.println("Tree Three : ");
		System.out.println();
		three.printTree();
		System.out.println();
		
		
		//TEST4
		System.out.println();
		Integer[] myArray4 = { 1,2,3,4,5};
		RedBlackTree<Integer> four = new RedBlackTree<>(myArray4);
		System.out.println("Tree Four : ");
		System.out.println();
		four.printTree();
		System.out.println();*/
	}
}
