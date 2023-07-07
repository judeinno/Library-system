package librarysystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import business.ControllerInterface;
import business.SystemController;


public class LibrarySystem extends JFrame implements LibWindow {
	ControllerInterface ci = new SystemController();
	public final static LibrarySystem INSTANCE =new LibrarySystem();
	private final String LIBRARIAN = "Librarian";
	private final String ADMINISTRATOR = "Administrator";
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu menuLibrarian, menuAdministrator;
    JMenu options;
    JMenuItem login, allBookIds, allMemberIds, checkoutBooks, addBooks, createNewMember, editMember; 
    String pathToImage;
    private boolean isInitialized = false;
    
    private static LibWindow[] allWindows = { 
    	LibrarySystem.INSTANCE,
		LoginWindow.INSTANCE,
		AllMemberIdsWindow.INSTANCE,	
		AllBookIdsWindow.INSTANCE
	};
    	
	public static void hideAllWindows() {
		
		for(LibWindow frame: allWindows) {
			frame.setVisible(false);
			
		}
	}
    
    
    private LibrarySystem() {}
    
    public void init() {
    	formatContentPane();
    	setPathToImage();
    	insertSplashImage();
		
		createMenus();
		pack();
		setSize(660,500);
		isInitialized = true;
    }
    
    private void formatContentPane() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,1));
		getContentPane().add(mainPanel);	
	}
    
    private void setPathToImage() {
    	String currDirectory = System.getProperty("user.dir");
    	pathToImage = currDirectory+"//src//librarysystem//library.jpg";
    }
    
    private void insertSplashImage() {
        ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));	
    }
    private void createMenus() {
    	menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createSoftBevelBorder(NORMAL, Util.LINK_AVAILABLE, Util.DARK_BLUE));
		addMenuItems(menuBar);
		setJMenuBar(menuBar);		
    }
    
    private void addMenuItems(JMenuBar menuBar) {
    	// Create and add the menus 
       login = new JMenuItem("Login");
       menuLibrarian = new JMenu(LIBRARIAN); 
       checkoutBooks = new JMenuItem("Checkout Books");
     
	   menuAdministrator = new JMenu(ADMINISTRATOR); 
	   addBooks = new JMenuItem("Add Books");
       createNewMember = new JMenuItem("Create New Member");
       editMember = new JMenuItem("Edit Member");
       allBookIds = new JMenuItem("All Book Ids");
       allMemberIds = new JMenuItem("All Member Ids");
       
       menuBar.add(login);
       menuBar.add(menuLibrarian);
       menuLibrarian.add(checkoutBooks);
       
	   menuBar.add(menuAdministrator);
	   menuAdministrator.add(addBooks);
	   menuAdministrator.add(createNewMember);
	   menuAdministrator.add(editMember);
       menuAdministrator.add(allBookIds);
       menuAdministrator.add(allMemberIds);
 	   
 	   
 	   login.addActionListener(new LoginListener());
 	   
 	   // Admin events
 	   addBooks.addActionListener(new AddBooksListener());
 	   createNewMember.addActionListener(new CreateNewMemberListener());
 	   editMember.addActionListener(new EditMemberListener());
 	   allBookIds.addActionListener(new AllBookIdsListener());
 	   allMemberIds.addActionListener(new AllMemberIdsListener());
 	  // Librarian events
 	   checkoutBooks.addActionListener(new CheckoutBooksListener());
    }
    
    class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			LoginWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
			LoginWindow.INSTANCE.setVisible(true);
			
		}
    	
    }
    class AllBookIdsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			AllBookIdsWindow.INSTANCE.init();
			
			List<String> ids = ci.allBookIds();
			Collections.sort(ids);
			StringBuilder sb = new StringBuilder();
			for(String s: ids) {
				sb.append(s + "\n");
			}
			System.out.println(sb.toString());
			AllBookIdsWindow.INSTANCE.setData(sb.toString());
			AllBookIdsWindow.INSTANCE.pack();
			AllBookIdsWindow.INSTANCE.setSize(660,500);
			Util.centerFrameOnDesktop(AllBookIdsWindow.INSTANCE);
			AllBookIdsWindow.INSTANCE.setVisible(true);
			
		}
    	
    }
    
    class AllMemberIdsListener implements ActionListener {

    	@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			AllMemberIdsWindow.INSTANCE.init();
			AllMemberIdsWindow.INSTANCE.pack();
			AllMemberIdsWindow.INSTANCE.setVisible(true);
			
			
			LibrarySystem.hideAllWindows();
			AllBookIdsWindow.INSTANCE.init();
			
			List<String> ids = ci.allMemberIds();
			Collections.sort(ids);
			StringBuilder sb = new StringBuilder();
			for(String s: ids) {
				sb.append(s + "\n");
			}
			System.out.println(sb.toString());
			AllMemberIdsWindow.INSTANCE.setData(sb.toString());
			AllMemberIdsWindow.INSTANCE.pack();
			AllMemberIdsWindow.INSTANCE.setSize(660,500);
			Util.centerFrameOnDesktop(AllMemberIdsWindow.INSTANCE);
			AllMemberIdsWindow.INSTANCE.setVisible(true);
			
			
		}
    	
    }
    
    class AddBooksListener implements ActionListener {

    	@Override
		public void actionPerformed(ActionEvent e) {
    		System.out.println("Add Books - Add listener ");
			
		}
    	
    }
    class CreateNewMemberListener implements ActionListener {
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Create New Member - Add listener ");
    		
    	}
    	
    }
    class EditMemberListener implements ActionListener {
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Edit Member - Add listener ");
    		
    	}
    	
    }
    class CheckoutBooksListener implements ActionListener {
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Check out Books - Add listener");
    		
    	}
    	
    }

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void isInitialized(boolean val) {
		isInitialized =val;
		
	}
    
}
