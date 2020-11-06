package http;

import com.alibaba.fastjson.JSONObject;
import com.meituan.toolchain.mario.model.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import utils.api.http.CscPacificKnowledgeConfigWebHttp;

import java.util.Collections;

/**
 * Created by sunyingfeng on 2019/6/24.
 */
@Slf4j
public class Api {

    private CscPacificKnowledgeConfigWebHttp configWebHttp = new CscPacificKnowledgeConfigWebHttp();

    //删除知识
    public void deleteKnowledge(JSONObject request, String comments) throws Exception {
        Long id = getKnowledgeObject(request, comments).getLong("id");
        ResponseMap deleteResponseMap = configWebHttp.deleteKnowledge(id);
        log.info("\n{} 数据清理，删除知识 :\n" + deleteResponseMap, comments);
    }

    //查询知识
    public JSONObject getKnowledgeObject(JSONObject request, String comments){
        ResponseMap responseMap = configWebHttp.pageKnowledgeVOByQueryParam(request.getString("title"), Collections.singletonList(request.getLong("typeId")));
        log.info("\n{} pageKnowledgeVOByQueryParam Response:\n" + responseMap, comments);
        JSONObject knowledgeObject  = responseMap.getJSONObjectByJsonPath("data").getJSONArray("items").getJSONObject(0);
        return knowledgeObject ;
    }

}
