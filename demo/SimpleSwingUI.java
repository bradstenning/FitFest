import javax.swing.*;
import javax.swing.table.*;
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
		textFieldLabel = new JLabel();
		textField = new JTextField();
		formattedTextFieldLabel = new JLabel();
		formattedTextField = new JFormattedTextField();
		comboBoxLabel = new JLabel();
		comboBox = new JComboBox();
		scrollPane1 = new JScrollPane();
		table = new JTable();
		button = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("this");
		setLayout(new FormLayout(
			"$ugap, 2*(default, $lcgap), default, $ugap",
			"$ugap, 5*(default, $lgap), default, $ugap"));
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{2, 4, 6}});

		//---- title ----
		title.setText("Title");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setName("title");
		add(title, cc.xywh(2, 2, 5, 1));

		//---- textFieldLabel ----
		textFieldLabel.setText("Text Field:");
		textFieldLabel.setName("textFieldLabel");
		add(textFieldLabel, cc.xy(2, 4));

		//---- textField ----
		textField.setName("textField");
		add(textField, cc.xywh(4, 4, 3, 1));

		//---- formattedTextFieldLabel ----
		formattedTextFieldLabel.setText("Formatted Text Field:");
		formattedTextFieldLabel.setName("formattedTextFieldLabel");
		add(formattedTextFieldLabel, cc.xy(2, 6));

		//---- formattedTextField ----
		formattedTextField.setName("formattedTextField");
		add(formattedTextField, cc.xywh(4, 6, 3, 1));

		//---- comboBoxLabel ----
		comboBoxLabel.setText("Combo Box");
		comboBoxLabel.setName("comboBoxLabel");
		add(comboBoxLabel, cc.xy(2, 8));

		//---- comboBox ----
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
			"first",
			"second",
			"third"
		}));
		comboBox.setName("comboBox");
		add(comboBox, cc.xywh(4, 8, 3, 1));

		//======== scrollPane1 ========
		{
			scrollPane1.setName("scrollPane1");

			//---- table ----
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, 1.0},
					{null, null},
					{"item2", 2.0},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"Column 1", "Column 2"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Double.class
				};
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			{
				TableColumnModel cm = table.getColumnModel();
				cm.getColumn(0).setCellEditor(new DefaultCellEditor(
					new JComboBox(new DefaultComboBoxModel(new String[] {
						"item1",
						"item2",
						"item3"
					}))));
			}
			table.setName("table");
			scrollPane1.setViewportView(table);
		}
		add(scrollPane1, cc.xywh(2, 10, 5, 1));

		//---- button ----
		button.setText("Button");
		button.setMnemonic('B');
		button.setName("button");
		add(button, cc.xywh(4, 12, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel title;
	private JLabel textFieldLabel;
	private JTextField textField;
	private JLabel formattedTextFieldLabel;
	private JFormattedTextField formattedTextField;
	private JLabel comboBoxLabel;
	private JComboBox comboBox;
	private JScrollPane scrollPane1;
	private JTable table;
	private JButton button;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
