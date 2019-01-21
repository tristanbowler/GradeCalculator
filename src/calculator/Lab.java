package calculator;

/**Lab Class
 * Defines Lab objects, which inherit from Task objects. 
 * 
 * @author Tristan Bowler
 * @version Jan 2019
 */
public class Lab extends Task{

	/**Lab Constructor
	 * Calls the Task super constructor and that's it. 
	 * 
	 * @param int listIndex
	 * @param title
	 * @param receivedPoints
	 * @param totalPoints
	 */
	public Lab(int listIndex, String title, double receivedPoints, double totalPoints) {
		super(listIndex, title, receivedPoints, totalPoints);
	}
	
	/**getTitle
	 * Returns the Lab title
	 * 
	 * @return a String
	 */
	public String getTitle(){
		return title; 
	}
	
	/**getPointsReceived
	 * returns the points received on the lab
	 * 
	 * @return a double
	 */
	public double getPointsReceived(){
		return receivedPoints; 
	}
	
	/**getTotalPoints
	 * Returns the total points available for a lab
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
		return listIndex+") Lab: "+title+", " +receivedPoints+"/"+totalPoints; 
	}
}
