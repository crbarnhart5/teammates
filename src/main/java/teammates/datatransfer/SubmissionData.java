package teammates.datatransfer;

import teammates.api.Common;
import teammates.persistent.Submission;

import com.google.appengine.api.datastore.Text;

public class SubmissionData {
	/** course ID */
	public String course;
	
	/** evaluation name */
	public String evaluation;
	
	/** team name */
	public String team;
	
	/** reviewer email */
	public String reviewer;
	
	public transient String reviewerName = null;
	
	/** reviewee email */
	public String reviewee;
	
	public transient String revieweeName = null;
	
	public int points;
	
	public Text justification;
	
	public Text p2pFeedback;
	
	public transient int normalized = Common.UNINITIALIZED_INT;

	public SubmissionData() {

	}

	public SubmissionData(Submission s) {
		this.course = s.getCourseID();
		this.evaluation = s.getEvaluationName();
		this.reviewer = s.getFromStudent();
		this.reviewee = s.getToStudent();
		this.team = s.getTeamName();
		this.points = s.getPoints();
		this.justification = s.getJustification();
		this.p2pFeedback = s.getCommentsToStudent();
	}

	public Submission toSubmission() {
		return new Submission(reviewer, reviewee, course, evaluation, team,
				points, justification, p2pFeedback);
	}
	
	
	/** using a simple copy method instead of clone().
	 * Reason: seems it is overly complicated and not well thought out
	 *   see http://stackoverflow.com/questions/2326758/how-to-properly-override-clone-method
	 * @return a copy of the object
	 */
	public SubmissionData getCopy(){
		SubmissionData copy = new SubmissionData();
		copy.course = this.course;
		copy.evaluation = this.evaluation;
		copy.team = this.team;
		copy.reviewer = this.reviewer;
		copy.reviewerName = this.reviewerName;
		copy.reviewee = this.reviewee;
		copy.revieweeName = this.revieweeName;
		copy.points = this.points;
		copy.justification = new Text(justification==null?null:justification.getValue());
		copy.p2pFeedback = new Text(p2pFeedback==null?null:p2pFeedback.getValue());
		copy.normalized = this.normalized;
		return copy;
	}

}
