package database;

import framework.util.PrimaryKeyDAL;
import models.Notifications;

import java.util.HashMap;
import java.util.Map;

public class NotificationsDAL extends PrimaryKeyDAL<Notifications, Integer> {
    private static NotificationsDAL instance;

    public static NotificationsDAL instance (){
        if (instance == null) {
            instance = new NotificationsDAL();
        }
        return instance;
    }

    @Override
    protected Map<String, Object> filters(Notifications notifications){
        return new HashMap<>() {{
            put("Id", notifications.id());
        }};
    }


    protected Map<String, Object> filters (String sourceId) {
        return new HashMap<>() {{
            put("Source_Id", sourceId);
        }};
    }

    public Notifications findOneBySourceId(String sourceId){
        return super.selectOne(filters(sourceId)).orElse(null);
    }

    @Override
    public Notifications findOne(Integer id) {
        return super.selectOne(filters(new Notifications().
                id(id))).orElse(null);
    }

    public void deleteByUser(String sourceId){
        HashMap<String, Object> filter = new HashMap<>() {{
            put("Source_Id", sourceId);
        }};
        super.delete(filter);
        System.out.println(super.selectOne(filter).orElse(null));
    }

    public void deleteNotificationByActionId(Integer actionId){
        HashMap<String, Object> filter = new HashMap<>() {{
            put("Action_Id", actionId);
        }};
        super.delete(filter);
        System.out.println(super.selectOne(filter).orElse(null));
    }
}
