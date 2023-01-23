package database;

import framwork.util.PrimaryKeyDAL;
import models.Notifications;

import java.util.HashMap;
import java.util.Map;

public class NotificationsDAL extends PrimaryKeyDAL<Notifications, Long> {
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
            put("id", notifications.id());
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
    public Notifications findOne(Long id) {
        return super.selectOne(filters(new Notifications().id(id))).orElse(null);
    }

    public void deleteByUser(String sourceId){
        HashMap<String, Object> filter = new HashMap<>() {{
            put("Source_Id", sourceId);
        }};
        super.delete(filter);
        System.out.println(super.selectOne(filter).orElse(null));
    }
}
