import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(
				new Runnable() 
				{
					public void run() 
					{
						createAndShowUI();
					}
				});

	}

	private static void createAndShowUI() 
	{
		JFrame mainFrame = new JFrame("Simple UI");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(new SimpleSwingUI());
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}
