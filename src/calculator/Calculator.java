package calculator;


/** Grade Calculator
 * This project is a simple grade calculator for a University Course.
 * The grade components are Assignments, Labs, and Exams. The user can input
 * the weights for each category as well as an overall curve. Tasks can be added and removed.
 * Additionally, the user can save to a .txt file in the working directory or chose to clear all
 * elements and start over from scratch.
 *
 * @author Tristan Bowler
 * @version 2.0 January 2019
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**Calculator
 * This class controls the GradeCalculator Application.
 */
public class Calculator implements Runnable, ActionListener
{
	/*------------------GUI Elements------------------*/
	private JComboBox<String> taskChoicesComboBox;

	private JButton calculateButton;
	private JButton clearButton;
	private JButton addButton;
	private JButton removeButton;
	private JButton saveButton;

	private JTextArea enteredTasks;

	private JTextField receivedPointsField;
	private JTextField totalPointsField;
	private JTextField fractionField;
	private JTextField taskTitleField;
	private JTextField curveWeightField;
	private JTextField assignmentsWeightField;
	private JTextField examsWeightField;
	private JTextField labsWeightField;
	private JTextField messageToUserField;
	private JTextField overallGradeField;
	private JTextField curvedGradeField;
	private JTextField examsGradeField;
	private JTextField assignmentsGradeField;
	private JTextField labsGradeField;
	private JTextField toBeRemovedField;

	private JLabel titleLabel;
	private JLabel curveLabel;
	private JLabel assignmentsLabel;
	private JLabel examsLabel;
	private JLabel labsLabel;
	private JLabel removeLabel;

	private String emptyString="";
	private String assignmentString="Assignment";
	private String taskString="Task";
	private String labString="Lab";
	private String examString="Exam";
	private String lastTask="The last task was: ";
	private String[] taskTypes={taskString, assignmentString, labString, examString};

	/*------------------Other Globals------------------*/
	public List <Task> tasksList= new ArrayList <Task>();

	private double overallgradeAsPercentage;
	private double curvedGradeAsPercentage;

	private boolean isCalculated=false;



	/**run
	 * Sets up the GUI of the GradeCalculator.
	 * Overrides the run() method of Runnable.
	 */
	@Override
	public void run()
	{
		Dimension size= new Dimension(700,750);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		Border loweredbevelBorder = BorderFactory.createLoweredBevelBorder();
		Color backgroundColor = UIManager.getColor ( "Panel.background" );

		JFrame frame=new JFrame("Grade Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(size);
    frame.setPreferredSize(size);
    frame.setMaximumSize(size);

    taskChoicesComboBox = new JComboBox<String>(taskTypes);
		taskChoicesComboBox.setBackground(Color.lightGray);
		taskChoicesComboBox.setSelectedIndex(0);

    titleLabel = new JLabel("Title: ");
		titleLabel.setBackground(backgroundColor);
		titleLabel.setBorder(emptyBorder);

		taskTitleField=new JTextField(emptyString);
		taskTitleField.setColumns(15);
		taskTitleField.setEditable(true);

		receivedPointsField = new JTextField(emptyString);
		receivedPointsField.setColumns(3);
		receivedPointsField.setEditable(true);

		fractionField = new JTextField("/");
		fractionField.setColumns(1);
		fractionField.setEditable(false);
		fractionField.setBackground(backgroundColor);
		fractionField.setBorder(emptyBorder);

		totalPointsField = new JTextField(emptyString);
		totalPointsField.setColumns(3);
		totalPointsField.setEditable(true);

		addButton= new JButton("Add");
		addButton.addActionListener(this);

		messageToUserField = new JTextField();
		messageToUserField.setColumns(40);
		messageToUserField.setEditable(false);
		messageToUserField.setBackground(backgroundColor);
		messageToUserField.setBorder(emptyBorder);
		messageToUserField.setHorizontalAlignment(JTextField.CENTER);
		messageToUserField.setText("Select the type, title, points received and possible, then press 'Add'");

    enteredTasks=new JTextArea(25,50);
    enteredTasks.setEditable(false);
    enteredTasks.setLineWrap(true);
    enteredTasks.setWrapStyleWord(true);
    enteredTasks.setBackground(Color.lightGray);
		enteredTasks.setBorder(loweredbevelBorder);

    overallGradeField = new JTextField();
		overallGradeField.setColumns(40);
		overallGradeField.setEditable(false);
		overallGradeField.setBackground(backgroundColor);
		overallGradeField.setBorder(emptyBorder);

		curvedGradeField = new JTextField();
		curvedGradeField.setColumns(40);
		curvedGradeField.setEditable(false);

		examsGradeField = new JTextField();
		examsGradeField.setColumns(40);
		examsGradeField.setEditable(false);

		assignmentsGradeField = new JTextField();
		assignmentsGradeField.setColumns(40);
		assignmentsGradeField.setEditable(false);

		labsGradeField = new JTextField();
		labsGradeField.setColumns(40);
		labsGradeField.setEditable(false);

		curveLabel = new JLabel("Curve Weight");
		curveWeightField = new JTextField("");
		curveWeightField.setColumns(3);
		curveWeightField.setEditable(true);
		curveWeightField.setText("100");
		curvedGradeField.setBackground(backgroundColor);
		curvedGradeField.setBorder(emptyBorder);

		assignmentsLabel = new JLabel("Assignments Weight:");
		assignmentsWeightField = new JTextField("");
		assignmentsWeightField.setColumns(3);
		assignmentsWeightField.setEditable(true);
		assignmentsGradeField.setBackground(backgroundColor);
		assignmentsGradeField.setBorder(emptyBorder);


		examsLabel = new JLabel("Exams Weight:");
		examsWeightField = new JTextField("");
		examsWeightField.setColumns(3);
		examsWeightField.setEditable(true);
		examsGradeField.setBackground(backgroundColor);
		examsGradeField.setBorder(emptyBorder);

		labsLabel = new JLabel("Labs Weight:");
		labsWeightField = new JTextField("");
		labsWeightField.setColumns(3);
		labsWeightField.setEditable(true);
		labsGradeField.setBackground(backgroundColor);
		labsGradeField.setBorder(emptyBorder);

		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(this);

		clearButton = new JButton("Clear All Entries");
		clearButton.addActionListener(this);

		saveButton = new JButton("Save to File");
		saveButton.addActionListener(this);

		removeLabel = new JLabel("Remove Element: ");
		removeLabel.setBackground(backgroundColor);
		removeLabel.setBorder(emptyBorder);

		toBeRemovedField = new JTextField();
		toBeRemovedField.setText(emptyString);
		toBeRemovedField.setColumns(2);
		toBeRemovedField.setEditable(true);

		removeButton = new JButton("Remove");
		removeButton.addActionListener(this);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());

		JPanel taskDetailsPanel = new JPanel();
		taskDetailsPanel.setLayout(new FlowLayout());
		taskDetailsPanel.add(taskChoicesComboBox , BorderLayout.WEST);
		contentPanel.add(taskDetailsPanel, BorderLayout.NORTH);

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleLabel);
		titlePanel.add(taskTitleField);
		taskDetailsPanel.add(titlePanel, BorderLayout.CENTER);

		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		scorePanel.add(receivedPointsField, BorderLayout.WEST);
		scorePanel.add(fractionField, BorderLayout.CENTER);
		scorePanel.add(totalPointsField, BorderLayout.EAST);
		taskDetailsPanel.add(scorePanel, BorderLayout.EAST);

		JPanel addButtonPanel = new JPanel();
		addButtonPanel.setLayout(new FlowLayout());
		addButtonPanel.add(addButton);
		taskDetailsPanel.add(addButtonPanel, BorderLayout.EAST);

		JPanel weightsPanel = new JPanel();
		weightsPanel.setBackground(backgroundColor);
		weightsPanel.setLayout(new FlowLayout());
		weightsPanel.add(assignmentsLabel);
		weightsPanel.add(assignmentsWeightField);
		weightsPanel.add(labsLabel);
		weightsPanel.add(labsWeightField);
		weightsPanel.add(examsLabel);
		weightsPanel.add(examsWeightField);
		weightsPanel.add(curveLabel);
		weightsPanel.add(curveWeightField);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout());
		infoPanel.add(messageToUserField);
		infoPanel.add(enteredTasks);
		infoPanel.add(weightsPanel);
		infoPanel.add(overallGradeField);
		infoPanel.add(curvedGradeField);
		infoPanel.add(assignmentsGradeField);
		infoPanel.add(examsGradeField);
		infoPanel.add(labsGradeField);
		contentPanel.add(infoPanel);

		JPanel removePanel = new JPanel();
		removePanel.setBorder(loweredbevelBorder);
		removePanel.add(clearButton);
		removePanel.add(removeLabel);
		removePanel.add(toBeRemovedField);
		removePanel.add(removeButton);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(backgroundColor);
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(calculateButton);
		buttonsPanel.add(saveButton);
		buttonsPanel.add(removePanel);

		contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

		frame.setContentPane(contentPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	/**actionPerformed
	 * This method senses for all actions in the program and responds appropriately.
	 *
	 * @param event : the action performed by the user
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{

		Object source = event.getSource();

		if(source == addButton)
		{
			addButtonPressed();
		}
		else if (source==calculateButton)
		{
			calculateButtonPressed();
		}
		else if (source==clearButton)
		{
			clearButtonPressed();
		}
		else if (source == removeButton)
		{
			removeButtonPressed();
		}
		else if(source==saveButton)
		{
			saveButtonPressed();
		}
	}


	/**addButtonPressed
	 *
	 * This method handles what must happen when the 'Add' button is pressed by the user.
	 */
	private void addButtonPressed() {
		String type = (String)taskChoicesComboBox.getSelectedItem();

		String receivedString=receivedPointsField.getText();
		double received;
		if (tryParseDouble(receivedString))
		{
			received = Double.parseDouble(receivedString);
		}
		else
		{
			messageToUserField.setText("Please ensure your point values only contain numbers.");
			messageToUserField.setBackground(Color.RED);
			return;
		}


		String totalString=totalPointsField.getText();
		double total;
		if(tryParseDouble(totalString))
		{
			total = Double.parseDouble(totalString);
		}
		else
		{
			messageToUserField.setText("Please ensure your point values only contain numbers.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		//taskString is the default Task in the drop down
		if(type.equals(taskString))
		{
			messageToUserField.setText("Please select a type.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		String title= taskTitleField.getText();
		int listIndex = tasksList.size()+1;

		if(title.equals(emptyString))
		{
			messageToUserField.setText("Please select a title.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		if(type.equals(assignmentString))
		{
			Assignment a= new Assignment(listIndex, title, received, total);
			tasksList.add(a);
			messageToUserField.setText(lastTask+a);
			messageToUserField.setBackground(Color.YELLOW);
			enteredTasks.append(a+"\n");

		}
		else if(type.equals(labString))
		{
			Lab l= new Lab(listIndex, title, received, total);
			tasksList.add(l);
			messageToUserField.setText(lastTask+l);
			messageToUserField.setBackground(Color.YELLOW);
			enteredTasks.append(l+"\n");

		}
		else if (type.equals(examString))
		{
			Exam q= new Exam(listIndex, title, received, total);
			tasksList.add(q);
			messageToUserField.setText(lastTask+q);
			messageToUserField.setBackground(Color.YELLOW);
			enteredTasks.append(q+"\n");
		}

		receivedPointsField.setText(emptyString);
		totalPointsField.setText(emptyString);
		taskTitleField.setText(emptyString);
		taskChoicesComboBox.setSelectedIndex(0);
		isCalculated = false;
	}

	/**calculateButtonPressed
	 *
	 * This method handles what must happen when the 'Calculate' Button is pressed by the user.
	 */
	private void calculateButtonPressed() {
		double assignmentTotalPoints = 0;
		double assignmentReceivedPoints = 0;
		double assignmentScore = 0;
		double assignmentsWeight = 0;
		double labTotalPoints = 0;
		double labReceivedPoints = 0;
		double labScore = 0;
		double labsWeight = 0;
		double examReceivedPoints = 0;
		double examTotalPoints = 0;
		double examScore = 0;
		double examsWeight = 0;
		double curveWeight = 0;

		String curveWeightString=curveWeightField.getText();

		if (tryParseDouble(curveWeightString))
		{
			curveWeight= Double.parseDouble(curveWeightString);
		}
		else
		{
			messageToUserField.setText("Please enter a curve weight. If there is no curve, enter 100.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		String assignmentWeightString = assignmentsWeightField.getText();

		if (tryParseDouble(assignmentWeightString))
		{
			assignmentsWeight= Double.parseDouble(assignmentWeightString);
		}
		else
		{
			messageToUserField.setText("Please enter an assignments weight.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		String examsWeightString = examsWeightField.getText();

		if (tryParseDouble(examsWeightString))
		{
			examsWeight = Double.parseDouble(examsWeightString);
		}
		else
		{
			messageToUserField.setText("Please enter an exams weight.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		String labsWeightString = labsWeightField.getText();

		if (tryParseDouble(labsWeightString))
		{
			labsWeight = Double.parseDouble(labsWeightString);
		}
		else
		{
			messageToUserField.setText("Please enter a labs weight.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		if((labsWeight+examsWeight+assignmentsWeight) != 100) {
			messageToUserField.setText("Weights for Assignments, Labs and Exams must total to 100%");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		for(Task task:tasksList)
		{
			if(task instanceof Assignment)
			{
				Assignment assignment = (Assignment) task;
				assignmentReceivedPoints += assignment.getPointReceived();
				assignmentTotalPoints += assignment.getTotalPoints();
			}
			else if(task instanceof Lab)
			{
				Lab lab=(Lab) task;
				labReceivedPoints += lab.getPointsReceived();
				labTotalPoints += lab.getTotalPoints();
			}
			else if(task instanceof Exam)
			{
				Exam exam = (Exam) task;
				examReceivedPoints += exam.getPointsReceived();
				examTotalPoints += exam.getTotalPoints();
			}
		}

		if(assignmentTotalPoints != 0)
		{
			assignmentScore = (assignmentReceivedPoints/assignmentTotalPoints)*assignmentsWeight;
		}
		else
		{
			assignmentScore = 0;
		}

		if(labTotalPoints != 0)
		{
			labScore=(labReceivedPoints/labTotalPoints )*labsWeight;
		}
		else
		{
			labScore=0;
		}

		if(examTotalPoints != 0)
		{
			examScore = (examReceivedPoints/examTotalPoints)*examsWeight;
		}
		else
		{
			examScore = 0;
		}


		overallgradeAsPercentage = assignmentScore+labScore+examScore;
		overallGradeField.setText("Your grade without a curve is: "
									+ overallgradeAsPercentage
									+ " ("
									+ getLetterGrade(overallgradeAsPercentage)
									+ ")");

		curvedGradeAsPercentage = (overallgradeAsPercentage/curveWeight)*100;
		curvedGradeField.setText("Your curved grade is: "
									+ curvedGradeAsPercentage
									+ " ("
									+ getLetterGrade(curvedGradeAsPercentage)
									+ ")");

		assignmentsGradeField.setText("Assignment score: " + assignmentScore);
		labsGradeField.setText("Lab score: " + labScore);
		examsGradeField.setText("Exam score: " + examScore);
		isCalculated=true;
		return;
	}

	/**clearButtonPressed
	 *
	 * This method handles what must happened when the 'Clear All Entries' Button
	 * is pressed by the user.
	 */
	private void clearButtonPressed() {
		tasksList.clear();
		enteredTasks.setText(emptyString);
		overallGradeField.setText(emptyString);
		curvedGradeField.setText(emptyString);
		assignmentsGradeField.setText(emptyString);
		labsGradeField.setText(emptyString);
		examsGradeField.setText(emptyString);
		messageToUserField.setText(emptyString);
		receivedPointsField.setText(emptyString);
		totalPointsField.setText(emptyString);
		taskTitleField.setText(emptyString);
		toBeRemovedField.setText(emptyString);
		assignmentsWeightField.setText(emptyString);
		labsWeightField.setText(emptyString);
		examsWeightField.setText(emptyString);
		curveWeightField.setText("100");
		taskChoicesComboBox.setSelectedIndex(0);
		messageToUserField.setBackground(Color.lightGray);
		isCalculated=false;
		return;
	}

	/**removeButtonPressed
	 *
	 * This method handles what must happen then the 'Remove' button is pressed by the user.
	 */
	private void removeButtonPressed() {
		if(tasksList.size() == 0)
		{
			messageToUserField.setText("There are no tasks to remove");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		int indexToRemove = 0;
		String removeString = toBeRemovedField.getText();
		if(tryParseDouble(removeString))
		{
			indexToRemove = (int) Double.parseDouble(removeString)-1;
		}
		else
		{
			messageToUserField.setText("Please make sure your Remove only conatins numbers.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		if(indexToRemove < 0)
		{
			messageToUserField.setText("Please only enter positive indecies.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		if(indexToRemove >= tasksList.size())
		{
			messageToUserField.setText("Plese enter a smaller index.");
			messageToUserField.setBackground(Color.RED);
			return;
		}

		enteredTasks.append("Removed: "+tasksList.get(indexToRemove)+"\n");
		tasksList.remove(indexToRemove);

		//if that clears the whole list, clear the info field
		if(indexToRemove-1<=0)
		{
			messageToUserField.setText(emptyString);
			messageToUserField.setBackground(Color.YELLOW);
			return;
		}
		else
		{
			messageToUserField.setText(lastTask+tasksList.get(indexToRemove-1));
			messageToUserField.setBackground(Color.YELLOW);
		}
		toBeRemovedField.setText(emptyString);

	}

	/**saveButtonPressed
	 *
	 * This method handles what must happen when the 'Save' Button was pressed by the user.
	 */
	private void saveButtonPressed() {
		if(isCalculated==false)
		{
			messageToUserField.setText("You must calculate first.");
			messageToUserField.setBackground(Color.RED);
			return;
		}
		try
		{
			FileWriter writer= new FileWriter("GradeSummaryHistory.txt",true);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime currentDateAndTime = LocalDateTime.now();

			writer.write("Grade Summary for "+ dtf.format(currentDateAndTime) +":\n\n");
			for(Task task: tasksList)
			{
				writer.write(task.toString());
				writer.write("\r\n");
			}
			writer.write("Your grade without a curve is: "+overallgradeAsPercentage+" ("+getLetterGrade(overallgradeAsPercentage)+")");
			writer.write("\r\n");
			writer.write("Your curved grade is: "+curvedGradeAsPercentage+" ("+getLetterGrade(curvedGradeAsPercentage)+")");
			writer.write("\r\n\n");
			writer.close();
			messageToUserField.setText("Saved to GradeSummaryHistory.txt");
			messageToUserField.setBackground(Color.green);
		}
		catch(IOException ioe)
		{
			messageToUserField.setText("Could not create file");
			return;
		}
	}

	/**tryParseDouble
	 * Method returns a true value if a double can be parsed and false if it cannot.
	 * Useful for limiting the number of try/catch blocks and null checks in the
	 * code above.
	 * @param stringToParse
	 * @return
	 */
	private boolean tryParseDouble(String stringToParse)
	{
		try
		{
			Double.parseDouble(stringToParse);
			return true;
		}
		catch(NumberFormatException e){}
		return false;
	}


	/**getLetterGrade
	 *
	 * This method takes a double as input and returns a String that
	 * represents the letter grade for a given percentage.
	 * Assumes a standard grade scale.
	 *
	 * @param gradePercentage : a double representing a grade percentage
	 * @return String : a letter grade representation of gradePercentage
	 */
	private String getLetterGrade(double gradePercentage)
	{
		if(gradePercentage>=93.0)
			return "A";
		else if(gradePercentage<93.0&&gradePercentage>=90.0)
			return "A-";
		else if(gradePercentage<90.0&&gradePercentage>=88.0)
			return "B+";
		else if(gradePercentage<88.0&&gradePercentage>=83.0)
			return "B";
		else if(gradePercentage<83.0&&gradePercentage>=80.0)
			return "B-";
		else if(gradePercentage<80.0&&gradePercentage>=78.0)
			return "C+";
		else if(gradePercentage<78.0&&gradePercentage>=73.0)
			return "C";
		else if(gradePercentage<73.0&&gradePercentage>=70.0)
			return "C-";
		else if(gradePercentage<70.0&&gradePercentage>=68.0)
			return "D+";
		else if(gradePercentage<68.0&&gradePercentage>=60.0)
			return "D";
		else if(gradePercentage<60.0&&gradePercentage>=50.0)
			return "D-";
		else if(gradePercentage<50.0)
			return "E";

		return "Error";

	}



	/**main
	 * The main method just calls the GUI thread, run(), to do all the work.
	 * @param args
	 */
	public static void main (String[] args)
	{
		SwingUtilities.invokeLater(new Calculator());
	}

}
