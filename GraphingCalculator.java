
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Date: October 4-2022 This is a graphing calculator that graphs the functions:
 * x, x^2, x^3, Sqrt(x), Log(x)
 * 
 * @author Brandon_Pacheco
 * @author Raghavndersinh_Solanki
 * @version 1.0
 * 
 */
public class GraphingCalculator extends JFrame {

	/**
	 * A JPanel object that displays components.
	 */
	private JPanel contentPane;
	/**
	 * A JTextField object that allows a user to type a value for m with the UI.
	 */
	private JTextField Scalar_textfield;
	/**
	 * A JTextField object that allows a user to type a value for b with the UI.
	 */
	private JTextField Offset_b;

	/**
	 * Launches the application.
	 * 
	 * @param args Array of string arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphingCalculator frame = new GraphingCalculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the UI with many Components and displays the information to the user.
	 */
	public GraphingCalculator() {
		setTitle("Graphing Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Scalar (m)");
		lblNewLabel.setBounds(10, 11, 83, 14);
		contentPane.add(lblNewLabel);

		Scalar_textfield = new JTextField("1.0");
		Scalar_textfield.setBounds(103, 8, 86, 20);
		contentPane.add(Scalar_textfield);
		Scalar_textfield.setColumns(10);

		JRadioButton LogX = new JRadioButton("Log(x)");
		LogX.setBounds(10, 79, 109, 23);
		contentPane.add(LogX);

		JRadioButton XSquared = new JRadioButton("X^2");
		XSquared.setBounds(10, 55, 109, 23);
		contentPane.add(XSquared);

		JRadioButton X = new JRadioButton("X");
		X.setBounds(10, 32, 109, 23);
		contentPane.add(X);

		JRadioButton XCubed = new JRadioButton("X^3");
		XCubed.setBounds(121, 55, 109, 23);
		contentPane.add(XCubed);

		JRadioButton SqrtX = new JRadioButton("Sqrt(x)");
		SqrtX.setBounds(121, 79, 109, 23);
		contentPane.add(SqrtX);

		ButtonGroup group = new ButtonGroup();
		{
			group.add(XSquared);
			group.add(X);
			group.add(XCubed);
			group.add(SqrtX);
			group.add(LogX);
		}

		JLabel lblYMfx = new JLabel("Y = m*f(x) + b");
		lblYMfx.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYMfx.setForeground(new Color(0, 0, 0));
		lblYMfx.setHorizontalAlignment(SwingConstants.CENTER);
		lblYMfx.setBounds(278, 54, 117, 14);
		contentPane.add(lblYMfx);

		Offset_b = new JTextField("0");
		Offset_b.setBounds(298, 8, 109, 20);
		contentPane.add(Offset_b);
		Offset_b.setColumns(10);

		JLabel lblOffsetb = new JLabel("Offset  (b)");
		lblOffsetb.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOffsetb.setBounds(229, 11, 59, 14);
		contentPane.add(lblOffsetb);

		JPanel Canvas = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGrid(g);
				g.setColor(Color.RED);
				if (X.isSelected()) {
					drawX(g);
				} else if (XSquared.isSelected()) {
					drawXSquared(g);
				} else if (XCubed.isSelected()) {
					drawXCubed(g);
				} else if (SqrtX.isSelected()) {
					drawXSqrt(g);
				} else if (LogX.isSelected()) {
					drawXLog(g);
				}

			}
		};

		Canvas.setBackground(Color.WHITE);
		Canvas.setBounds(10, 124, 400, 400);
		contentPane.add(Canvas);
		getContentPane().add(Canvas);

		JButton btnGraph = new JButton("Graph");
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Canvas.repaint();

			}
		});
		btnGraph.setBounds(278, 79, 129, 23);
		contentPane.add(btnGraph);

	}

	/**
	 * This method draws out and displays the grid on the canvas.
	 * 
	 * @param g A graphics object passed in from paintComponent.
	 */
	private void drawGrid(Graphics g) {

		g.setColor(Color.LIGHT_GRAY);
		g.drawString("X", 20, 190);
		g.drawString("Y", 180, 20);
		g.drawString("1.0", 210, 105);
		g.drawLine(200, 0, 200, 400);
		g.drawLine(0, 200, 400, 200);
		g.drawLine(195, 300, 205, 300);
		g.drawLine(195, 100, 205, 100);
		g.drawLine(100, 195, 100, 205);
		g.drawLine(300, 195, 300, 205);

	}

	/**
	 * This method calculates the formula y=m(x)+b, then plots the values of x and y
	 * on the graph.
	 * 
	 * @param g A graphics object passed in from paintComponent.
	 */
	private void drawX(Graphics g) {

		String strB = Offset_b.getText();
		String strM = Scalar_textfield.getText();
		double b = 0.0;
		double m = 0.0;

		double dx = 0.01;

		if (isNumeric(strB) && isNumeric(strM)) {
			b = Double.parseDouble(strB);
			m = Double.parseDouble(strM);
			for (double x = -2; x <= 2; x += dx) {
				double y = m * x + b;
				if ((y <= 2 && y >= -2) && (x <= 2 && x >= -2)) {
					int cX = (int) (x * 100) + 200;
					int cY = Math.abs((int) (y * 100) - 200);
					Graphics2D g2 = (Graphics2D) g;
					g2.setStroke(new BasicStroke(4));
					g2.drawLine(cX, cY, cX, cY);
				}
			}
		}
	}

	/**
	 * This method calculates the formula y=m(x^2)+b, then plots the values of x and
	 * y on the graph.
	 * 
	 * @param g A graphics object passed in from paintComponent.
	 */
	private void drawXSquared(Graphics g) {

		String strB = Offset_b.getText();
		String strM = Scalar_textfield.getText();
		double b = 0.0;
		double m = 0.0;
		double dx = 0.01;

		if (isNumeric(strB) && isNumeric(strM)) {
			b = Double.parseDouble(strB);
			m = Double.parseDouble(strM);
			for (double x = -2; x <= 2; x += dx) {
				double y = m * (Math.pow(x, 2)) + b;
				if ((y <= 2 && y >= -2) && (x <= 2 && x >= -2)) {

					int cX = (int) (x * 100) + 200;
					int cY = Math.abs((int) (y * 100) - 200);
					Graphics2D g2 = (Graphics2D) g;
					g2.setStroke(new BasicStroke(4));
					g2.drawLine(cX, cY, cX, cY);
				}
			}
		}
	}

	/**
	 * This method calculates the formula y=m(x^3)+b, then plots the values of x and
	 * y on the graph.
	 * 
	 * @param g A graphics object passed in from paintComponent.
	 */
	private void drawXCubed(Graphics g) {

		String strB = Offset_b.getText();
		String strM = Scalar_textfield.getText();
		double b = 0.0;
		double m = 0.0;
		double dx = 0.01;

		if (isNumeric(strB) && isNumeric(strM)) {
			b = Double.parseDouble(strB);
			m = Double.parseDouble(strM);
			for (double x = -2; x <= 2; x += dx) {

				double y = m * (Math.pow(x, 3)) + b;
				if ((y <= 2 && y >= -2) && (x <= 2 && x >= -2)) {
					int cX = (int) (x * 100) + 200;
					int cY = Math.abs((int) (y * 100) - 200);
					Graphics2D g2 = (Graphics2D) g;
					g2.setStroke(new BasicStroke(4));
					g2.drawLine(cX, cY, cX, cY);

				}
			}
		}
	}

	/**
	 * This method calculates the formula y=m(Sqrt(x))+b, then plots the values of x
	 * and y on the graph.
	 * 
	 * @param g A graphics object passed in from paintComponent.
	 */
	private void drawXSqrt(Graphics g) {

		String strB = Offset_b.getText();
		String strM = Scalar_textfield.getText();
		double b = 0.0;
		double m = 0.0;
		double dx = 0.01;

		if (isNumeric(strB) && isNumeric(strM)) {
			b = Double.parseDouble(strB);
			m = Double.parseDouble(strM);
			for (double x = -2; x <= 2; x += dx) {
				double y = m * (Math.sqrt(x)) + b;
				if ((y <= 2 && y >= -2) && (x <= 2 && x >= -2)) {
					int cX = (int) (x * 100) + 200;
					int cY = Math.abs((int) (y * 100) - 200);
					Graphics2D g2 = (Graphics2D) g;
					g2.setStroke(new BasicStroke(4));
					g2.drawLine(cX, cY, cX, cY);
				}
			}
		}
	}

	/**
	 * This method calculates the formula y=m(Log(x))+b, then plots the values of x
	 * and y on the graph.
	 * 
	 * @param g A graphics object passed in from paintComponent.
	 */
	private void drawXLog(Graphics g) {

		String strB = Offset_b.getText();
		String strM = Scalar_textfield.getText();
		double b = 0.0;
		double m = 0.0;
		double dx = 0.01;

		if (isNumeric(strB) && isNumeric(strM)) {
			b = Double.parseDouble(strB);
			m = Double.parseDouble(strM);
			for (double x = -2; x <= 2; x += dx) {
				double y = m * (Math.log(x)) + b;
				if ((y <= 2 && y >= -2) && (x <= 2 && x >= -2)) {
					int cX = (int) (x * 100) + 200;
					int cY = Math.abs((int) (y * 100) - 200);
					Graphics2D g2 = (Graphics2D) g;
					g2.setStroke(new BasicStroke(4));
					g2.drawLine(cX, cY, cX, cY);
				}
			}
		}
	}

	/**
	 * This method determines if the input from the keyboard is a valid number.
	 * 
	 * @param string A String value passed in.
	 * @return returning True or False depending on the string's contents.
	 */
	private boolean isNumeric(String string) {
		if (string == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(string);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
