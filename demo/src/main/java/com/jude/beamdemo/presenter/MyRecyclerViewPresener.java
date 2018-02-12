package com.jude.beamdemo.presenter;

import com.alibaba.fastjson.TypeReference;
import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.beamdemo.model.MyModel;
import com.jude.beamdemo.ui.MyRecyclerViewActivity;
import com.jude.beamdemo.utils.FastJSONHelper;
import com.jude.beamdome.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class MyRecyclerViewPresener extends BeamBasePresenter<MyRecyclerViewActivity>{
    public void setAdapterData(){
        /*List<MyModel> myModels = FastJSONHelper.deserializeList(getView().getResources().getString(R.string.json_data),
                MyModel.class);
        getView().myAdapter.addAll(myModels);*/
        MyModel model=FastJSONHelper.deserializeAny("{\n" +
                "\t\"message\": \"查询成功\",\n" +
                "\t\"response\": [{\n" +
                "\t\t\"anlianOrder\": \"19366\",\n" +
                "\t\t\"applyTime\": \"2018-01-26 22:57:12\",\n" +
                "\t\t\"cardNo\": \"310107194504285013\",\n" +
                "\t\t\"companyId\": 11004,\n" +
                "\t\t\"companyName\": \"中智关爱通（上海）科技股份有限公司_3\",\n" +
                "\t\t\"createTime\": \"2018-01-26 22:57:12\",\n" +
                "\t\t\"firstPhone\": \"15821329668\",\n" +
                "\t\t\"healthProductName\": \"钻石体检套餐\",\n" +
                "\t\t\"healthProductNo\": \"203\",\n" +
                "\t\t\"healthType\": \"体检\",\n" +
                "\t\t\"healthTypeCode\": \"001\",\n" +
                "\t\t\"id\": 492,\n" +
                "\t\t\"ifMatch\": \"未结\",\n" +
                "\t\t\"ifUserself\": \"本人\",\n" +
                "\t\t\"jfwOrder\": \"f8f994371d4c4dfc9a3015be8db80748\",\n" +
                "\t\t\"matchTime\": null,\n" +
                "\t\t\"orderBatch\": \"2018-01-01\",\n" +
                "\t\t\"orderCardno\": \"310107194504285013\",\n" +
                "\t\t\"orderCity\": \"长春市\",\n" +
                "\t\t\"orderNumId\": 169,\n" +
                "\t\t\"orderOrganization\": \"爱康国宾长春亚泰鸿城西域体检分院\",\n" +
                "\t\t\"orderPhone\": \"18817351766\",\n" +
                "\t\t\"orderStatus\": \"2\",\n" +
                "\t\t\"orderStatusName\": \"成功\",\n" +
                "\t\t\"orderSwitch\": \"on\",\n" +
                "\t\t\"orderTime\": \"2018-02-03 00:00:00\",\n" +
                "\t\t\"orderUser\": \"任才华\",\n" +
                "\t\t\"phone\": \"15821329668\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"sex\": \"男\",\n" +
                "\t\t\"successTime\": \"2018-01-26 22:57:39\",\n" +
                "\t\t\"supplier\": \"234\",\n" +
                "\t\t\"updateTime\": \"2018-01-26 22:57:39\",\n" +
                "\t\t\"userId\": 11069,\n" +
                "\t\t\"userName\": \"任才华\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anlianOrder\": \"19363\",\n" +
                "\t\t\"applyTime\": \"2018-01-25 14:55:37\",\n" +
                "\t\t\"cardNo\": \"310107194504285013\",\n" +
                "\t\t\"companyId\": 11004,\n" +
                "\t\t\"companyName\": \"中智关爱通（上海）科技股份有限公司_3\",\n" +
                "\t\t\"createTime\": \"2018-01-25 14:55:37\",\n" +
                "\t\t\"firstPhone\": \"15821329668\",\n" +
                "\t\t\"healthProductName\": \"钻石体检套餐\",\n" +
                "\t\t\"healthProductNo\": \"203\",\n" +
                "\t\t\"healthType\": \"体检\",\n" +
                "\t\t\"healthTypeCode\": \"001\",\n" +
                "\t\t\"id\": 488,\n" +
                "\t\t\"ifMatch\": \"未结\",\n" +
                "\t\t\"ifUserself\": \"本人\",\n" +
                "\t\t\"jfwOrder\": \"13d3fc36d76d4878848b9c373aa00e22\",\n" +
                "\t\t\"matchTime\": null,\n" +
                "\t\t\"orderBatch\": \"2018-01-01\",\n" +
                "\t\t\"orderCardno\": \"310107194504285013\",\n" +
                "\t\t\"orderCity\": \"长春市\",\n" +
                "\t\t\"orderNumId\": 169,\n" +
                "\t\t\"orderOrganization\": \"爱康国宾长春亚泰鸿城西域体检分院\",\n" +
                "\t\t\"orderPhone\": \"18817351766\",\n" +
                "\t\t\"orderStatus\": \"2\",\n" +
                "\t\t\"orderStatusName\": \"成功\",\n" +
                "\t\t\"orderSwitch\": \"on\",\n" +
                "\t\t\"orderTime\": \"2018-02-09 00:00:00\",\n" +
                "\t\t\"orderUser\": \"任才华\",\n" +
                "\t\t\"phone\": \"15821329668\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"sex\": \"男\",\n" +
                "\t\t\"successTime\": \"2018-01-25 14:56:00\",\n" +
                "\t\t\"supplier\": \"234\",\n" +
                "\t\t\"updateTime\": \"2018-01-25 14:56:00\",\n" +
                "\t\t\"userId\": 11069,\n" +
                "\t\t\"userName\": \"任才华\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anlianOrder\": \"19362\",\n" +
                "\t\t\"applyTime\": \"2018-01-25 14:51:38\",\n" +
                "\t\t\"cardNo\": \"310107194504285013\",\n" +
                "\t\t\"companyId\": 11004,\n" +
                "\t\t\"companyName\": \"中智关爱通（上海）科技股份有限公司_3\",\n" +
                "\t\t\"createTime\": \"2018-01-25 14:51:38\",\n" +
                "\t\t\"firstPhone\": \"15821329668\",\n" +
                "\t\t\"healthProductName\": \"钻石体检套餐\",\n" +
                "\t\t\"healthProductNo\": \"203\",\n" +
                "\t\t\"healthType\": \"体检\",\n" +
                "\t\t\"healthTypeCode\": \"001\",\n" +
                "\t\t\"id\": 487,\n" +
                "\t\t\"ifMatch\": \"未结\",\n" +
                "\t\t\"ifUserself\": \"本人\",\n" +
                "\t\t\"jfwOrder\": \"41025b90b2574acfb32740b30ba0ee18\",\n" +
                "\t\t\"matchTime\": null,\n" +
                "\t\t\"orderBatch\": \"2018-01-01\",\n" +
                "\t\t\"orderCardno\": \"310107194504285013\",\n" +
                "\t\t\"orderCity\": \"长春市\",\n" +
                "\t\t\"orderNumId\": 169,\n" +
                "\t\t\"orderOrganization\": \"爱康国宾长春亚泰鸿城西域体检分院\",\n" +
                "\t\t\"orderPhone\": \"18817351766\",\n" +
                "\t\t\"orderStatus\": \"2\",\n" +
                "\t\t\"orderStatusName\": \"成功\",\n" +
                "\t\t\"orderSwitch\": \"on\",\n" +
                "\t\t\"orderTime\": \"2018-02-01 00:00:00\",\n" +
                "\t\t\"orderUser\": \"刘洋\",\n" +
                "\t\t\"phone\": \"15821329668\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"sex\": \"男\",\n" +
                "\t\t\"successTime\": \"2018-01-23 20:32:12\",\n" +
                "\t\t\"supplier\": \"2334\",\n" +
                "\t\t\"updateTime\": \"2018-01-23 20:32:12\",\n" +
                "\t\t\"userId\": 11069,\n" +
                "\t\t\"userName\": \"任才华\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anlianOrder\": \"19201\",\n" +
                "\t\t\"applyTime\": \"2018-01-23 19:56:50\",\n" +
                "\t\t\"cardNo\": \"310107194504285013\",\n" +
                "\t\t\"companyId\": 11004,\n" +
                "\t\t\"companyName\": \"中智关爱通（上海）科技股份有限公司_3\",\n" +
                "\t\t\"createTime\": \"2018-01-23 19:56:50\",\n" +
                "\t\t\"firstPhone\": \"15821329668\",\n" +
                "\t\t\"healthProductName\": \"钻石体检套餐\",\n" +
                "\t\t\"healthProductNo\": \"203\",\n" +
                "\t\t\"healthType\": \"体检\",\n" +
                "\t\t\"healthTypeCode\": \"001\",\n" +
                "\t\t\"id\": 427,\n" +
                "\t\t\"ifMatch\": \"未结\",\n" +
                "\t\t\"ifUserself\": \"非本人\",\n" +
                "\t\t\"jfwOrder\": \"aba52ea3c55144df86f3d9af5d18273e\",\n" +
                "\t\t\"matchTime\": null,\n" +
                "\t\t\"orderBatch\": \"2018-01-01\",\n" +
                "\t\t\"orderCardno\": \"310107194504285013\",\n" +
                "\t\t\"orderCity\": \"长春市\",\n" +
                "\t\t\"orderNumId\": 169,\n" +
                "\t\t\"orderOrganization\": \"爱康国宾长春亚泰鸿城西域体检分院\",\n" +
                "\t\t\"orderPhone\": \"18817351766\",\n" +
                "\t\t\"orderStatus\": \"2\",\n" +
                "\t\t\"orderStatusName\": \"成功\",\n" +
                "\t\t\"orderSwitch\": \"on\",\n" +
                "\t\t\"orderTime\": \"2018-01-23 20:22:38\",\n" +
                "\t\t\"orderUser\": \"刘洋\",\n" +
                "\t\t\"phone\": \"15821329668\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"sex\": \"男\",\n" +
                "\t\t\"successTime\": \"2018-01-23 19:57:40\",\n" +
                "\t\t\"supplier\": \"234\",\n" +
                "\t\t\"updateTime\": \"2018-01-23 19:57:40\",\n" +
                "\t\t\"userId\": 11069,\n" +
                "\t\t\"userName\": \"任才华\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anlianOrder\": \"19191\",\n" +
                "\t\t\"applyTime\": \"2018-01-23 18:58:22\",\n" +
                "\t\t\"cardNo\": \"310107194504285013\",\n" +
                "\t\t\"companyId\": 11004,\n" +
                "\t\t\"companyName\": \"中智关爱通（上海）科技股份有限公司_3\",\n" +
                "\t\t\"createTime\": \"2018-01-23 18:58:22\",\n" +
                "\t\t\"firstPhone\": \"15821329668\",\n" +
                "\t\t\"healthProductName\": \"洁牙服务\",\n" +
                "\t\t\"healthProductNo\": \"206\",\n" +
                "\t\t\"healthType\": \"牙科\",\n" +
                "\t\t\"healthTypeCode\": \"002\",\n" +
                "\t\t\"id\": 419,\n" +
                "\t\t\"ifMatch\": \"未结\",\n" +
                "\t\t\"ifUserself\": \"本人\",\n" +
                "\t\t\"jfwOrder\": \"c5a6fc7d828e4c11b42cf7ee41d44247\",\n" +
                "\t\t\"matchTime\": null,\n" +
                "\t\t\"orderBatch\": \"2018-01-01\",\n" +
                "\t\t\"orderCardno\": \"310107194504285013\",\n" +
                "\t\t\"orderCity\": \"长��市\",\n" +
                "\t\t\"orderNumId\": 171,\n" +
                "\t\t\"orderOrganization\": \"拜博口腔长春总院\",\n" +
                "\t\t\"orderPhone\": \"13565895672\",\n" +
                "\t\t\"orderStatus\": \"2\",\n" +
                "\t\t\"orderStatusName\": \"成功\",\n" +
                "\t\t\"orderSwitch\": \"on\",\n" +
                "\t\t\"orderTime\": \"2018-01-23 19:23:35\",\n" +
                "\t\t\"orderUser\": \"任才华\",\n" +
                "\t\t\"phone\": \"15821329668\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"sex\": \"男\",\n" +
                "\t\t\"successTime\": \"2018-01-23 15:59:28\",\n" +
                "\t\t\"supplier\": \"234\",\n" +
                "\t\t\"updateTime\": \"2018-01-23 18:24:44\",\n" +
                "\t\t\"userId\": 11069,\n" +
                "\t\t\"userName\": \"任才华\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anlianOrder\": \"19189\",\n" +
                "\t\t\"applyTime\": \"2018-01-23 17:43:56\",\n" +
                "\t\t\"cardNo\": \"310107194504285013\",\n" +
                "\t\t\"companyId\": 11004,\n" +
                "\t\t\"companyName\": \"中智关爱通（上海）科技股份有限公司_3\",\n" +
                "\t\t\"createTime\": \"2018-01-23 17:43:56\",\n" +
                "\t\t\"firstPhone\": \"15821329668\",\n" +
                "\t\t\"healthProductName\": \"钻石体检套餐\",\n" +
                "\t\t\"healthProductNo\": \"203\",\n" +
                "\t\t\"healthType\": \"体检\",\n" +
                "\t\t\"healthTypeCode\": \"001\",\n" +
                "\t\t\"id\": 412,\n" +
                "\t\t\"ifMatch\": \"未结\",\n" +
                "\t\t\"ifUserself\": \"本人\",\n" +
                "\t\t\"jfwOrder\": \"b826f72f8b884c1dbc61f26163a5fe8e\",\n" +
                "\t\t\"matchTime\": null,\n" +
                "\t\t\"orderBatch\": \"2018-01-01\",\n" +
                "\t\t\"orderCardno\": \"310107194504285013\",\n" +
                "\t\t\"orderCity\": \"长春市\",\n" +
                "\t\t\"orderNumId\": 169,\n" +
                "\t\t\"orderOrganization\": \"爱康国宾长春亚泰鸿城西域体检分院\",\n" +
                "\t\t\"orderPhone\": \"13565895672\",\n" +
                "\t\t\"orderStatus\": \"2\",\n" +
                "\t\t\"orderStatusName\": \"成功\",\n" +
                "\t\t\"orderSwitch\": \"on\",\n" +
                "\t\t\"orderTime\": \"2018-01-23 18:23:15\",\n" +
                "\t\t\"orderUser\": \"任才华\",\n" +
                "\t\t\"phone\": \"15821329668\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"sex\": \"男\",\n" +
                "\t\t\"successTime\": \"2018-01-23 17:58:18\",\n" +
                "\t\t\"supplier\": \"234\",\n" +
                "\t\t\"updateTime\": \"2018-01-23 17:58:18\",\n" +
                "\t\t\"userId\": 11069,\n" +
                "\t\t\"userName\": \"任才华\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anlianOrder\": \"19183\",\n" +
                "\t\t\"applyTime\": \"2018-01-23 17:36:49\",\n" +
                "\t\t\"cardNo\": \"310107194504285013\",\n" +
                "\t\t\"companyId\": 11004,\n" +
                "\t\t\"companyName\": \"中智关爱通（上海）科技股份有限公司_3\",\n" +
                "\t\t\"createTime\": \"2018-01-23 17:36:49\",\n" +
                "\t\t\"firstPhone\": \"15821329668\",\n" +
                "\t\t\"healthProductName\": \"钻石体检套餐\",\n" +
                "\t\t\"healthProductNo\": \"203\",\n" +
                "\t\t\"healthType\": \"体检\",\n" +
                "\t\t\"healthTypeCode\": \"001\",\n" +
                "\t\t\"id\": 409,\n" +
                "\t\t\"ifMatch\": \"未结\",\n" +
                "\t\t\"ifUserself\": \"本人\",\n" +
                "\t\t\"jfwOrder\": \"30ce911f5337467ca289ef718380dfb7\",\n" +
                "\t\t\"matchTime\": null,\n" +
                "\t\t\"orderBatch\": \"2018-01-01\",\n" +
                "\t\t\"orderCardno\": \"310107194504285013\",\n" +
                "\t\t\"orderCity\": \"长春市\",\n" +
                "\t\t\"orderNumId\": 169,\n" +
                "\t\t\"orderOrganization\": \"爱康国宾长春亚泰鸿城西域体检分院\",\n" +
                "\t\t\"orderPhone\": \"18115123912\",\n" +
                "\t\t\"orderStatus\": \"2\",\n" +
                "\t\t\"orderStatusName\": \"成功\",\n" +
                "\t\t\"orderSwitch\": \"on\",\n" +
                "\t\t\"orderTime\": \"2018-01-23 18:02:21\",\n" +
                "\t\t\"orderUser\": \"任才华\",\n" +
                "\t\t\"phone\": \"15821329668\",\n" +
                "\t\t\"price\": 0,\n" +
                "\t\t\"sex\": \"男\",\n" +
                "\t\t\"successTime\": \"2018-01-23 17:37:24\",\n" +
                "\t\t\"supplier\": \"234\",\n" +
                "\t\t\"updateTime\": \"2018-01-23 17:37:24\",\n" +
                "\t\t\"userId\": 11069,\n" +
                "\t\t\"userName\": \"任才华\"\n" +
                "\t}],\n" +
                "\t\"status\": \"1\"\n" +
                "}",new TypeReference<MyModel>(){});
        getView().myAdapter.addAll(model.response);
    }
}
