package database;

import framework.util.PrimaryKeyDAL;
import models.CommentsToSoliders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsToSolidersDAL extends PrimaryKeyDAL<CommentsToSoliders, Long> {
    private static CommentsToSolidersDAL instance;

    public static CommentsToSolidersDAL instance (){
        if(instance == null) {
            instance = new CommentsToSolidersDAL();
        }
        return instance;
    }

    @Override
    protected Map<String, Object> filters(CommentsToSoliders commentsToSoliders) {
        return new HashMap<>() {{
            put("Comment_Id", commentsToSoliders.commentId());
        }};
    }

    protected  Map<String, Object> filters(String misparIshi, String writerId) {
        return new HashMap<>() {{
            put("Mispar_Ishi", misparIshi);
            put("Writer_Id", writerId);
        }};
    }


    public Long findLastCommentId(String misparIshi, String writerId){
        List<CommentsToSoliders> allComments = selectAll(filters(misparIshi, writerId));
        return allComments.get(allComments.size()-1).commentId();
    }

    protected Map<String, Object> filterByCommentId(Long commentId){
        return new HashMap<>() {{
            put("Comment_Id", commentId);
        }};
    }


    @Override
    public CommentsToSoliders findOne(Long commentId) {
        return selectOne(filters(new CommentsToSoliders().
                commentId(commentId))).orElse(null);
    }

    public CommentsToSoliders findOneByWriterId(String writerId) {
        return selectOne(filters(new CommentsToSoliders().
                writerId(writerId))).orElse(null);
    }


    public void deleteLast(String misparIshi, String writerId){
        super.delete(filterByCommentId(findLastCommentId(misparIshi, writerId)));
        System.out.println(super.selectOne(filterByCommentId
                (findLastCommentId(misparIshi, writerId))).orElse(null));
    }


}
