package de.reko_solutions.soapUIHelper;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JFrame;

import com.eviware.soapui.support.action.support.AbstractSoapUIAction;
import com.eviware.soapui.model.ModelItem;



public class CopyAssertionAction extends AbstractSoapUIAction {

	public CopyAssertionAction()
	{
	    super( "Copy Assertions", "Copy Assertions from selected Teststep to others" );
	}
	  
	public void perform( ModelItem target, Object param )
	{
				
			JFrame frame = new JFrame("CopyAssertion");    
			frame.addWindowListener(new WindowAdapter()       
			{        
				public void windowClosing(WindowEvent e) 
				{
					JFrame theFrame = (JFrame)e.getSource();  
					theFrame.dispose();
				}      
			});
				
			frame.setContentPane(new CopyAssertion(target));  
			Toolkit tk = Toolkit.getDefaultToolkit();
			URL url = getClass().getResource("reko.png");
			Image icon = tk.createImage(url);
			
		    frame.setIconImage(icon);			
			frame.pack();    
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		    frame.setLocation(x, y);
		    
			frame.setVisible(true);
							
	}
}
