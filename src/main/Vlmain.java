package main;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import view.Vlview;

/**
 * method main
 * @author wang yijun
 *
 * @param <E>
 */
public class Vlmain<E> {

	public static <E> void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		// Erzeuge die Frame
		Vlview myFrame = new Vlview();
		myFrame.init();
		myFrame.setTitle("VL_Suche");
		myFrame.setSize(700, 300);
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);

	}
}