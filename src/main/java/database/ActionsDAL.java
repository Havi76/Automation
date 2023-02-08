package database;

import com.beust.ah.A;
import framwork.util.PrimaryKeyDAL;
import models.Actions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ActionsDAL extends PrimaryKeyDAL<Actions, Long> {
    private static ActionsDAL instance;

    public static ActionsDAL instance () {
        if (instance == null) {
            instance = new ActionsDAL();
        }
        return instance;
    }

    @Override
    protected Map<String, Object> filters(Actions actions) {
        return new HashMap<>() {{
            put("Id", actions.actionId());
        }};
    }

    @Override
    public Actions findOne(Long id) {
        return super.selectOne(filters(new Actions().actionId(id))).orElse(null);
    }

    public void deleteByUser(String createdBy){
        HashMap<String, Object> filter = new HashMap<>() {{
            put("Created_By", createdBy);
        }};
        super.delete(filter);
        System.out.println(super.selectOne(filter).orElse(null));
    }

    public void deleteActionByActionId(Integer actionId){
        HashMap<String, Object> filter = new HashMap<>() {{
            put("Action_Id", actionId);
        }};
        super.delete(filter);
        System.out.println(super.selectOne(filter).orElse(null));
    }
}
