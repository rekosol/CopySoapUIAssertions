package de.reko_solutions.soapUIHelper;

/*
 * MIT License

Copyright (c) 2018 Reiner Köhler, www.reko-solutions.de

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */


import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;


import com.eviware.soapui.model.ModelItem;
import com.eviware.soapui.model.workspace.Workspace;
import com.eviware.soapui.model.project.Project;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequestStep;
 


class ProjectModel extends DefaultComboBoxModel<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8722704969124032994L;


	public ProjectModel() {
		addElement( "Element One" );    		
						
	} 
		
	public ProjectModel(List<? extends Project> projectList) {
		removeAllElements();				      
		for( int i = 0; i < projectList.size(); ++i )    
		{    
			if (projectList.get(i).isOpen())
				addElement( projectList.get(i).getName());    
		}				
	}
	
	 
} 


class TestsuiteModel extends DefaultComboBoxModel<String>{  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1451427881953901060L;

	public TestsuiteModel( String project, Workspace ws )  {    
		loadModel( project, ws );  
	}  
 
	public void loadModel( String project, Workspace ws )  {    
		if( getSize() > 0 )    
		{      
			removeAllElements();    
		} 
		
		Project theProject = ws.getProjectByName(project);
		for( int i = 0; i < theProject.getTestSuiteList().size(); ++i )    
		{   
			
			addElement( theProject.getTestSuiteList().get(i).getName() );    
		} 
	} 
} 

class TestcaseModel extends DefaultComboBoxModel<String>{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 7583568374640378646L;

	public TestcaseModel( String project, String testsuite, Workspace ws )  {    
		loadModel( project, testsuite, ws );  
	}  
 
	public void loadModel( String project, String testsuite, Workspace ws )  {    
		if( getSize() > 0 )    
		{      
			removeAllElements();    
		} 
		
		TestSuite theTestSuite = ws.getProjectByName(project).getTestSuiteByName(testsuite);
		if( testsuite != null)
		{
			for( int i = 0; i < theTestSuite.getTestCaseList().size(); ++i )    
			{   			
				addElement( theTestSuite.getTestCaseList().get(i).getName() );    
			} 
		}		
	} 
} 


class TeststepModel extends DefaultComboBoxModel<String>{  
	/**
	 * 
	 */
	private static final long serialVersionUID = -5836960328325111150L;
	
	public TeststepModel( String project, String testsuite, String testcase, Workspace ws )  {    
		loadModel( project, testsuite, testcase, ws );  
	}  
 
	public void loadModel( String project, String testsuite, String testcase, Workspace ws )  {    
		if( getSize() > 0 )    
		{      
			removeAllElements();    
		} 
		
		TestCase theTestCase = ws.getProjectByName(project).getTestSuiteByName(testsuite).getTestCaseByName(testcase);
		if( theTestCase != null)
		{
			for( int i = 0; i < theTestCase.getTestStepList().size(); ++i )    
			{   			
				if (theTestCase.getTestStepList().get(i) instanceof WsdlTestRequestStep)
					addElement( theTestCase.getTestStepList().get(i).getName() ); 				
			} 
		}		
	} 
} 


class AssertionModel extends DefaultComboBoxModel<String>{  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6282942240120517217L;

	public AssertionModel( String project, String testsuite, String testcase, String teststep, Workspace ws )  {    
		loadModel( project, testsuite, testcase, teststep, ws );  
	}  
 
	public void loadModel( String project, String testsuite, String testcase, String teststep, Workspace ws )  {    
		if( getSize() > 0 )    
		{      
			removeAllElements();    
		} 
		
		TestStep theTeststep = ws.getProjectByName(project).getTestSuiteByName(testsuite).getTestCaseByName(testcase).getTestStepByName(teststep);
		if( theTeststep != null)
		{
			for( int i = 0; i < theTeststep.getChildren().size(); ++i )    
			{   
					addElement( theTeststep.getChildren().get(i).getName() );    
			} 
		}		
	} 
} 






public class CopyAssertion extends JPanel {  
	/**
	 * 
	 */
	private static final long serialVersionUID = 6863571030464223823L;
	private JComboBox<String> projectcb;  
	private JComboBox<String> testsuitecb; 	
	private JComboBox<String> testcasecb; 	
	private JComboBox<String> teststepcb; 	
	
	private JComboBox<String> sourceAssertioncb; 	
	
	private Workspace theWorkspace; 
	private String targetProject;
	private String targetTestsuite;
	private String targetTestcase;
	private String targetTeststep;
	
	private String sourceProject;
	private String sourceTestsuite;
	private String sourceTestcase;
	private String sourceTeststep;	
	private String sourceAssertion;
	
	private TextField ergebnis = new TextField("-");
	
	public CopyAssertion(ModelItem mi)   {    
		
		if ( !(mi instanceof WsdlTestRequestStep)) {
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;		c.gridy = 0;		add(new Label("Information:"),c);		
			c.gridx = 1;		c.gridy = 0;		add(new Label("Copy only works with WSDLRequests!"),c);
			return;
		}
			
		
		theWorkspace = mi.getProject().getWorkspace();
		
		sourceProject =  mi.getProject().getName();
		targetProject = sourceProject;
		sourceTestsuite = mi.getParent().getParent().getName();
		targetTestsuite = sourceTestsuite;
		sourceTestcase = mi.getParent().getName();
		targetTestcase = sourceTestcase;
		sourceTeststep = mi.getName();
		targetTeststep = sourceTeststep;
		sourceAssertioncb = new JComboBox<String>( new AssertionModel(sourceProject, sourceTestsuite, sourceTestcase, sourceTeststep, theWorkspace ) );
				
		DefaultComboBoxModel<String> cbmProject = new ProjectModel(theWorkspace.getProjectList() );    		
		
		projectcb = new JComboBox<String>( cbmProject);
		projectcb.setSelectedItem(sourceProject);
		testsuitecb = new JComboBox<String>( new TestsuiteModel(sourceProject, theWorkspace ) );
		testsuitecb.setSelectedItem(sourceTestsuite);
		testcasecb = new JComboBox<String>( new TestcaseModel(sourceProject, sourceTestsuite, theWorkspace ) );
		testcasecb.setSelectedItem(sourceTestcase);
		teststepcb = new JComboBox<String>( new TeststepModel(sourceProject, sourceTestsuite, sourceTestcase, theWorkspace ) );
		
		
		
		
		projectcb.addActionListener( new ActionListener()       
		{        
			public void actionPerformed(ActionEvent e)         
			{          
				JComboBox<String> cb = (JComboBox<String>)e.getSource();          
				String project = (String)cb.getSelectedItem();
				targetProject = project;
				TestsuiteModel tsModel = (TestsuiteModel)testsuitecb.getModel();          
				tsModel.loadModel( project, theWorkspace );  
								
			}
		}); 
		
		testsuitecb.addActionListener( new ActionListener()       
		{        
			public void actionPerformed(ActionEvent e)         
			{          
				JComboBox<String> cb = (JComboBox<String>)e.getSource();          
				targetTestsuite = (String)cb.getSelectedItem();
				if( targetTestsuite != null)
				{
					TestcaseModel tcModel = (TestcaseModel)testcasecb.getModel();				
					tcModel.loadModel( targetProject, targetTestsuite, theWorkspace );
				}
				else
				{
					targetTestsuite = (String) testsuitecb.getModel().getElementAt(0);
					TestcaseModel tcModel = (TestcaseModel)testcasecb.getModel();
					if( targetTestsuite != null)
						tcModel.loadModel( targetProject, targetTestsuite, theWorkspace );
				}
			}
		}); 

		
		testcasecb.addActionListener( new ActionListener()       
		{        
			public void actionPerformed(ActionEvent e)         
			{          
				JComboBox<String> cb = (JComboBox<String>)e.getSource();          
				targetTestcase = (String)cb.getSelectedItem();
				if( targetTestcase != null)
				{
					TeststepModel tcModel = (TeststepModel)teststepcb.getModel();				
					tcModel.loadModel( targetProject, targetTestsuite, targetTestcase, theWorkspace );
				}
				else
				{
					targetTestcase = (String) testcasecb.getModel().getElementAt(0);
					TeststepModel tcModel = (TeststepModel)teststepcb.getModel();
					if( targetTestcase != null)
						tcModel.loadModel( targetProject, targetTestsuite, targetTestcase, theWorkspace );
				}
			}
		}); 


		teststepcb.addActionListener( new ActionListener()       
		{        
			public void actionPerformed(ActionEvent e)         
			{          
				JComboBox<String> cb = (JComboBox<String>)e.getSource();          
				targetTeststep = (String)cb.getSelectedItem();
				
			}
		}); 

		
		
		
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;		c.gridy = 0;		add(new Label("Source"),c);			
		c.gridx = 0;		c.gridy = 1;		add(new Label("Project"),c);		c.gridx = 1;		c.gridy = 1;		add(new Label(sourceProject),c);
		c.gridx = 0;		c.gridy = 2;		add(new Label("TestSuite"),c);		c.gridx = 1;		c.gridy = 2;		add(new Label(sourceTestsuite),c);
		c.gridx = 0;		c.gridy = 3;		add(new Label("TestCase"),c);		c.gridx = 1;		c.gridy = 3;		add(new Label(sourceTestcase),c); 
		c.gridx = 0;		c.gridy = 4;		add(new Label("TestStep"),c);		c.gridx = 1;		c.gridy = 4;		add(new Label(sourceTeststep),c); 
		c.gridx = 0;		c.gridy = 5;		add(new Label("Assertion"),c);		c.gridx = 1;		c.gridy = 5;		add(sourceAssertioncb,c); 
		c.gridx = 0;		c.gridy = 6;		add(new Label(" "),c);				c.gridx = 1;		c.gridy = 6;		add(new Label(" "),c); 

		c.gridx = 0;		c.gridy = 7;		add(new Label("Target"),c);					
		c.gridx = 0;		c.gridy = 8;		add(new Label("Project"),c);		c.gridx = 1;		c.gridy = 8;		add(projectcb,c);
		c.gridx = 0;		c.gridy = 9;		add(new Label("TestSuite"),c);		c.gridx = 1;		c.gridy = 9;		add(testsuitecb,c);
		c.gridx = 0;		c.gridy = 10;		add(new Label("TestCase"),c);		c.gridx = 1;		c.gridy = 10;		add(testcasecb,c); 
		c.gridx = 0;		c.gridy = 11;		add(new Label("TestStep"),c);		c.gridx = 1;		c.gridy = 11;		add(teststepcb,c); 
		
		c.gridx = 0;		c.gridy = 12;		add(new Label(" "),c);				c.gridx = 1;		c.gridy = 12;		add(new Label(" "),c); 

		Button button = new Button("Copy");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)         
			{
				WsdlTestRequestStep testStepSrc = (WsdlTestRequestStep) theWorkspace.getProjectByName(sourceProject).getTestSuiteByName(sourceTestsuite).getTestCaseByName(sourceTestcase).getTestStepByName(sourceTeststep); 				
				WsdlTestRequestStep testStepTrgt =(WsdlTestRequestStep) theWorkspace.getProjectByName(targetProject).getTestSuiteByName(targetTestsuite).getTestCaseByName(targetTestcase).getTestStepByName(targetTeststep); 
				
				int counter = testStepSrc.getAssertionList().size();
				for (int count = 0; count < counter; count++) {
					String assertion = testStepSrc.getAssertionAt(count).getName();
					if ( assertion.equals( (String)sourceAssertioncb.getSelectedItem()))
					{					
						if (!exist(assertion, testStepTrgt))
						{
							testStepTrgt.cloneAssertion(testStepSrc.getAssertionAt(count), testStepSrc.getAssertionAt(count).getName());					
							ergebnis.setText("Ready!");
						}else {
							ergebnis.setText("Assertion exists. Not copied!");
						}
					}	
				}
			}
			
		});
		c.gridx = 1;		c.gridy = 13;		add(button,c);	 
		
		c.gridx = 0;		c.gridy = 14;		add(new Label("Ergebnis"),c);				c.gridx = 1;		c.gridy = 14;		add(ergebnis,c); 

		
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));  
		
		
	} 
	
	
	private boolean exist(String assertion, WsdlTestRequestStep testStepTrgt) 
	{
		int counter = testStepTrgt.getAssertionList().size();
		for (int count = 0; count < counter; count++) {
			String listAssertion = testStepTrgt.getAssertionAt(count).getName();
			if (listAssertion.equals(assertion))
			{
				return true;
			}				
		}
		return false;
	}
	
	
} 