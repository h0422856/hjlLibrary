package xb.com.refreshdemo.refresh;

import java.util.HashMap;
import java.util.Map;

import xb.com.refreshdemo.R;
import xb.com.refreshdemo.net.api.NetApi;
import xb.com.refreshdemo.net.base.DemoBaseLogic;


/**
 * @author zhuhf
 * @version [AndroidLibrary, 2018-03-07]
 */
public class RefreshLogic extends DemoBaseLogic {
    private NetApi api;

    /**
     * 构造函数
     *
     * @param subscriber 最终订阅者
     */
    public RefreshLogic(Object subscriber) {
        super(subscriber);
        api = create(NetApi.class);
    }


    public void getLawyerList(int pageNum, long pageTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderBy", "1");
        map.put("pageNum", pageNum);
        map.put("pageTime", pageTime);
        sendRequest(api.lawyerList(map), R.id.get_lawyer_list_id);
    }


}
