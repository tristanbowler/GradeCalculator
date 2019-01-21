package calculator;

/**Exam Class
 * Defines Exam objects, which inherit from Task objects. 
 * 
 * @author Tristan Bowler
 * @version Jan 2019
 */
public class Exam extends Task {

	/**Exam Constructor 
	 * Calls the super constructor and that's it. 
	 * 
	 * @param int listIndex
	 * @param String title
	 * @param double receivedPoints
	 * @param double totalPoints
	 */
	public Exam(int listIndex, String title, double receivedPoints, double totalPoints) {
		super(listIndex, title, receivedPoints, totalPoints);
	}
	
	
	/**getTitle
	 * Returns the Exam title
	 * 
	 * @return a String
	 */
	public String getTitle(){
		return title; 
	}
	
	
	/**getPointsRecieved
	 * Returns the points received on the quiz
	 * 
	 * @return a double
	 */
	public double getPointsReceived(){
		return receivedPoints; 
	}
	
	
	/**getTotalPoints
	 * Returns the total points available for an exam
	 * 
	 * @return a double
	 */
	public double getTotalPoints(){
		return totalPoints; 
	}
	
	/**getListIndex
	 * Returns the listIndex of the Task in the tasksList
	 * @return listIndex
	 */
	public int getListIndex() {
		return listIndex; 
	}
	
	/**toString
	 * Custom toString
	 * @return a string of info about Task
	 */
	public String toString(){
		return listIndex+") Exam: "+title+", " +receivedPoints+"/"+totalPoints; 
	}

}
