package database;

import framework.util.PrimaryKeyDAL;
import models.Interviews;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterviewsDAL extends PrimaryKeyDAL<Interviews, Long> {
    private static InterviewsDAL instance;

    public static InterviewsDAL instance() {
        if (instance == null) {
            instance = new InterviewsDAL();
        }
        return instance;
    }

    @Override
    protected Map<String, Object> filters(Interviews interview) {
        return new HashMap<>() {{
            put("Interview_Id", interview.interviewId());
        }};
    }

    protected Map<String, Object> filters(String interviewerId) {
        return new HashMap<>() {{
            put("Interviewer_Id", interviewerId);
        }};
    }

    @Override
    public Interviews findOne(Long interviewId) {
        return super.selectOne(filters
                (new Interviews().interviewId(interviewId))).orElse(null);
    }

    public Interviews findLastInterviewByInterviewerId(String interviewerId){
        List<Interviews> allInterviews = selectAll(filters(interviewerId));
        return allInterviews.get(allInterviews.size()-1);
    }

    public Interviews findOne(String interviewerId) {
        return super.selectOne(filters(interviewerId)).orElse(null);
    }

    public void deleteLastInterviewByInterviewer(String interviewerId)
    {
        super.delete(findLastInterviewByInterviewerId(interviewerId));
    }

}
