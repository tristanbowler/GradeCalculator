package calculator;

/**Exam Class
 * Defines Task objects. 
 * Very bare-bones class intended to create inheritance relationships. 
 *  
 * @author Tristan Bowler
 * @version Jan 2019
 */
abstract public class Task {
	
	//fields all tasks should have
	protected double totalPoints;
	protected double receivedPoints;
	protected String title;
	protected int listIndex; 
	
	/**Super constructor for all task objects
	 * 
	 * @param int listIndex
	 * @param title
	 * @param received
	 * @param total
	 */
	public Task(int listIndex, String title, double received, double total){
		this.totalPoints=total;
		this.receivedPoints=received;
		this.title=title; 
		this.listIndex=listIndex; 
		
	}

}
