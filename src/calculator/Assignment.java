package calculator;

/**Assignment Class
 * Defines Assignment objects, which inherit from Task objects. 
 * 
 * @author Tristan Bowler
 * @version Jan 2019
 */
public class Assignment extends Task{

	/**Assignment constructor
	 * Calls the Task super constructor in Task.  
	 * 
	 * @param int listIndex
	 * @param title 
	 * @param receivedPoints
	 * @param totalPoints
	 */
	public Assignment(int listIndex, String title, double receivedPoints, double totalPoints) {
		super(listIndex, title, receivedPoints, totalPoints);
	}
	
	
	/**getTitle
	 * returns the Assignment title
	 * 
	 * @return a String
	 */
	public String getName(){
		return title; 
	}
	
	
	/**getPointsReceived
	 * Returns the points received on the assignment
	 * @return a double
	 */
	public double getPointReceived(){
		return receivedPoints; 
	}
	
	
	/**getTotalPoints
	 * Returns the total points available for an assignment
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
		return listIndex+") Assignment: "+title+", " +receivedPoints+"/"+totalPoints; 
	}

}
