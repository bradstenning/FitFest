import javax.swing.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Sat Feb 20 13:19:32 MST 2010
 */



/**
 * @author Brad Stenning
 */
public class SimpleSwingUI extends JPanel {
	public SimpleSwingUI() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		title = new JLabel();
		label2 = new JLabel();
		textField = new JTextField();
		label3 = new JLabel();
		formattedTextField = new JFormattedTextField();
		label1 = new JLabel();
		comboBox = new JComboBox();
		button = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"$ugap, 2*(default, $lcgap), default, $ugap",
			"$ugap, 4*(default, $lgap), default, $ugap"));
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{2, 4, 6}});

		//---- title ----
		title.setText("Title");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, cc.xywh(2, 2, 5, 1));

		//---- label2 ----
		label2.setText("Text Field:");
		add(label2, cc.xy(2, 4));
		add(textField, cc.xywh(4, 4, 3, 1));

		//---- label3 ----
		label3.setText("Formatted Text Field:");
		add(label3, cc.xy(2, 6));
		add(formattedTextField, cc.xywh(4, 6, 3, 1));

		//---- label1 ----
		label1.setText("Combo Box");
		add(label1, cc.xy(2, 8));
		add(comboBox, cc.xywh(4, 8, 3, 1));

		//---- button ----
		button.setText("Button");
		button.setMnemonic('B');
		add(button, cc.xywh(4, 10, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel title;
	private JLabel label2;
	private JTextField textField;
	private JLabel label3;
	private JFormattedTextField formattedTextField;
	private JLabel label1;
	private JComboBox comboBox;
	private JButton button;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
