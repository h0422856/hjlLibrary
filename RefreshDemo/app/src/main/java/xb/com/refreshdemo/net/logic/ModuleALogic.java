package xb.com.refreshdemo.net.logic;

import java.util.HashMap;
import java.util.Map;

import xb.com.refreshdemo.R;
import xb.com.refreshdemo.net.api.NetApi;
import xb.com.refreshdemo.net.base.DemoBaseLogic;
import xb.com.refreshdemo.net.config.AccountManager;


/**
 * @author zhuhf
 * @version [AndroidLibrary, 2018-03-07]
 */
public class ModuleALogic extends DemoBaseLogic {
    private NetApi api;

    /**
     * 构造函数
     *
     * @param subscriber 最终订阅者
     */
    public ModuleALogic(Object subscriber) {
        super(subscriber);
        api = create(NetApi.class);
    }


    public void categoryList(String phoneNum, String psw) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNum", phoneNum);
        map.put("password", psw);
        sendRequest(api.getLogin(map), R.id.login_id);
    }

    public void updatePassWord(String phoneNum, String oldPsw, String newPsw, int uId) {
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNum", phoneNum);
        map.put("uId", uId);
        map.put("oldPassword", oldPsw);
        map.put("password", newPsw);
        sendRequest(api.updatePassWord(uId, map), R.id.update_password_id);
    }

    public void getList() {
        Map<String, Object> map = new HashMap<>();
        map.put("tagId", "0");
        sendRequest(api.getList(map), R.id.update_password_id);
    }

    public void getCommonMessage(int uId) {
        Map<String, Object> map = new HashMap<>();
        map.put("uId", uId);
        map.put("pageNum", "1");
        map.put("pageTime", "0");
        sendRequest(api.getCommonMessage(uId, map), R.id.get_common_message_id);
    }


    public void bannerList(int uId) {
        Map<String, Object> map = new HashMap<>();
        map.put("uId", uId);
        map.put("position", "1");
        map.put("pageNum", "1");
        map.put("pageTime", "0");
        sendRequest(api.bannerList(map), R.id.get_banner_list_id);
    }

    public void deleteUnreadNum(int uId) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("uId", uId);
        sendRequest(api.deleteUnreadNum(uId), R.id.delete_unread_num_id);
    }

    public void patchUpdateAuth(int uId) {
        Map<String, Object> map = new HashMap<>();
        map.put("gender", "2");
        sendRequest(api.patchUpdateAuth(uId, map), R.id.patch_updateauth_id);
    }


}
