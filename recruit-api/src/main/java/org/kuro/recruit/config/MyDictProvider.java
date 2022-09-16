package org.kuro.recruit.config;

import com.houkunlin.system.dict.starter.bean.DictTypeVo;
import com.houkunlin.system.dict.starter.provider.DictProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class MyDictProvider implements DictProvider {

    @Override
    public boolean isStoreDictType() {
        return true;
    }

    @Override
    public Iterator<DictTypeVo> dictTypeIterator() {
        // todo 使用数据库的字典数据
        List<DictTypeVo> list = new ArrayList<>();
        DictTypeVo t1 = DictTypeVo.newBuilder("gender", "性别")
                .add("1", "男").add("2", "女").build();

        DictTypeVo t2 = DictTypeVo.newBuilder("user_state", "用户状态")
                .add("1", "正常").add("2", "冻结").build();

        DictTypeVo t3 = DictTypeVo.newBuilder("status", "数据状态")
                .add("0", "启用").add("1", "禁用").build();

        DictTypeVo t5 = DictTypeVo.newBuilder("del_flag", "删除状态")
                .add("1", "删除").add("0", "正常").build();

        DictTypeVo t6 = DictTypeVo.newBuilder("emp_state", "员工状态")
                .add("1", "在职").add("2", "离职").build();

        DictTypeVo t7 = DictTypeVo.newBuilder("order_type", "订单类型")
                .add("1", "采购单").add("2", "运输单")
                .add("3", "入库单").add("4", "销售单").build();

        DictTypeVo t8 = DictTypeVo.newBuilder("order_state", "订单状态")
                .add("1", "未审核").add("2", "审核未通过")
                .add("3", "审核通过").add("4", "待采购")
                .add("5", "待指派").add("6", "采购中")
                .add("7", "采购完成").add("8", "待入库")
                .add("9", "入库中").add("10", "入库完成").build();

        DictTypeVo t9 = DictTypeVo.newBuilder("meeting_type", "会议类型")
                .add("1", "在线会议").add("2", "线下会议").build();

        DictTypeVo t10 = DictTypeVo.newBuilder("meeting_status", "会议状态")
                .add("1", "申请中").add("2", "审批未通过")
                .add("3", "审批通过").add("4", "会议进行中")
                .add("5", "会议结束").build();

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        list.add(t8);
        list.add(t9);
        list.add(t10);
        return list.stream().iterator();
    }
}
