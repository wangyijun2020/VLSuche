package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import main.Vlmain;
import main.Vorlesung;

/*
 * Diese Klasse repräsentiert ein Fenster, dem VL anlegen verwalten suchen wird
 * @author yijun wang
 */
public class Vlview extends JFrame {

	// ContentPane und allgemein(创建容器)
	private Container contentPane;

	// allgemeine (设置字体)
	private Font font = new Font("Arial", 0, 14);

	// erstellen Label
	// class
	private JLabel vorlesungLabel;
	// teacher
	private JLabel profnameLabel;
	// period
	private JLabel semesterLabel;

	// erstellen Button
	// add class
	private JButton anlegenButton;
	// find class
	private JButton sucheButton;
	// export
	private JButton exportButton;

	// erstellen Panel
	// todo ???
	private JPanel topPanel;
	private JPanel midTopPanel;
	private JPanel midPanel;
	private JPanel midFloorPanel;
	private JPanel FloorPannel;

	// erstellen TextField
	public JTextField vorlesungTextField;
	public JTextField profnameTextField;
	public JTextField semesterTextField;

	// erstellen Inhalt
	// contant
	private JTextField inhaltField;

	// no limit length array
	private java.util.List<Vorlesung> vorlesungList;

	// Fenster-Layout
	public Vlview() {
		vorlesungList = new ArrayList<Vorlesung>();
	}

	public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		System.out.println("UIManager.getSystemLookAndFeelClassName() = " + UIManager.getSystemLookAndFeelClassName());

		contentPane = getContentPane();
		contentPane.setBackground(new Color(220, 220, 220));

		// Panels ineinander verschachteln, um Layout zu erzielen （将panel 放入 容器）

		topPanel = new JPanel(new BorderLayout());
		midTopPanel = new JPanel(new BorderLayout());
		midPanel = new JPanel(new BorderLayout());
		midFloorPanel = new JPanel(new BorderLayout());
		FloorPannel = new JPanel(new BorderLayout());

		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(midTopPanel, BorderLayout.SOUTH);
		midTopPanel.add(midPanel, BorderLayout.NORTH);
		midTopPanel.add(midFloorPanel, BorderLayout.CENTER);
		midTopPanel.add(FloorPannel, BorderLayout.SOUTH);

		// erstellen Label und zu Panel einfügen （设置 标签并放入panel）
		vorlesungLabel = new JLabel("Vorlesung");
		vorlesungLabel.setFont(font);

		profnameLabel = new JLabel("Prof-Name", JLabel.CENTER);
		profnameLabel.setFont(font);

		semesterLabel = new JLabel("Semester");
		semesterLabel.setFont(font);

		topPanel.add(vorlesungLabel, BorderLayout.WEST);
		topPanel.add(profnameLabel, BorderLayout.CENTER);
		topPanel.add(semesterLabel, BorderLayout.EAST);

		// erstellen Textfield und zu Panel einfügen （设置文字 此处输入 课程名称）
		vorlesungTextField = new JTextField("");
		vorlesungTextField.setColumns(15);
		vorlesungTextField.setVisible(true);
		vorlesungTextField.setBackground(Color.WHITE);
		vorlesungTextField.setHorizontalAlignment(JTextField.RIGHT);
		vorlesungTextField.setFont(font);

		// 设置 文字 输入 教授名称
		profnameTextField = new JTextField("");
		profnameTextField.setVisible(true);
		profnameTextField.setColumns(15);
		profnameTextField.setBackground(Color.WHITE);
		profnameTextField.setHorizontalAlignment(JTextField.RIGHT);
		profnameTextField.setFont(font);
		// 设置 文字 输入学期名称
		semesterTextField = new JTextField("");
		semesterTextField.setVisible(true);
		semesterTextField.setColumns(15);
		semesterTextField.setBackground(Color.WHITE);
		semesterTextField.setHorizontalAlignment(JTextField.RIGHT);
		semesterTextField.setFont(font);

		midPanel.add(vorlesungTextField, BorderLayout.WEST);
		midPanel.add(profnameTextField, BorderLayout.CENTER);
		midPanel.add(semesterTextField, BorderLayout.EAST);

		// erstellen array for vorlesung （设置 课程类的 array 之前 设置过 课程的constructor 包括 课程名 教授名
		// 学期名 都是 string variable）

		// erstellen die Button und in Panel einfügen
		anlegenButton = new JButton("Anlegen");
		anlegenButton.setForeground(Color.black);
		anlegenButton.setFont(font);

		// erstellen die Funktion für anlegen ( button 的作用是 把 在 textfield 输入的 课程名，教授名，
		// 学期名 放进 课程的array)
		anlegenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Vorlesung tmp = new Vorlesung(vorlesungTextField.getText(), profnameTextField.getText(),
							semesterTextField.getText());
					System.out.println(tmp.info());
					vorlesungList.add(tmp);
					System.out.println(vorlesungList);
					inhaltField.setText("die Vorlesung wird erfolgreich angelegt");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		sucheButton = new JButton("Suche");
		sucheButton.setForeground(Color.black);
		sucheButton.setFont(font);
		// 创建 actionlistener ( 这个按钮的作用是： 在已经存入的课程中， 查找 相同课程名 或者相同教授名 或者 相同 学期名的 所有课程，
		// 把找到的课程写在 inhalt 这个文字框内)
		/*
		 * ？？？？！！！！！！！！
		 */
		sucheButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vorlesungStr = vorlesungTextField.getText();
				String profnameStr = profnameTextField.getText();
				String semesterStr = semesterTextField.getText();

				for (int j = 0; j < vorlesungList.size(); j++) {
					Vorlesung tmp = (Vorlesung) vorlesungList.get(j);
					System.out.println(tmp.info());
					// first find vor
					if ((!Vlview.isEmpty(vorlesungStr)) && (tmp.getVorlesung().equalsIgnoreCase(vorlesungStr))) {
						inhaltField.setText(tmp.info());
						break;
					}
					// sec find pro
					else if ((!Vlview.isEmpty(profnameStr)) && profnameStr.equalsIgnoreCase(tmp.getProfName())) {
						inhaltField.setText(tmp.info());
						break;
					} else if ((!Vlview.isEmpty(semesterStr)) && semesterStr.equalsIgnoreCase(tmp.getSemester())) {
						inhaltField.setText(tmp.info());
						break;
					} else {
						inhaltField.setText("keine");
					}
				}

			}

		});

		exportButton = new JButton("Leer");
		exportButton.setForeground(Color.black);
		exportButton.setFont(font);

		// 把 找到的课程 写进 一个文件夹 并清空文本框， 我选了本地文件夹。
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 把inhalte 内容导出到文件

				try {
					FileOutputStream out = new FileOutputStream("C:\\Users\\wang yijun\\Desktop\\VL_Suche.txt");
					// 将内容转换成字节

					byte[] a1 = inhaltField.getText().getBytes();

					// 把内容写进 文件里
					out.write(a1);
					out.close();
					// 清理 textfield 内容
					vorlesungTextField.setText(null);
					profnameTextField.setText(null);
					semesterTextField.setText(null);
					inhaltField.setText(null);

				} catch (IOException e2) {

					e2.printStackTrace();
				}

			}
		});

		midFloorPanel.add(anlegenButton, BorderLayout.WEST);
		midFloorPanel.add(sucheButton, BorderLayout.CENTER);
		midFloorPanel.add(exportButton, BorderLayout.EAST);

		// inhalt zeigen
		inhaltField = new JTextField("inhalt");
		inhaltField.setVisible(true);
		inhaltField.setBackground(Color.WHITE);
		inhaltField.setHorizontalAlignment(JTextField.RIGHT);
		inhaltField.setFont(font);
		FloorPannel.add(inhaltField);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}

}
