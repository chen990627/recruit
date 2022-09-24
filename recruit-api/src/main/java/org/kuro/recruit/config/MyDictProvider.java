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

        DictTypeVo t3 = DictTypeVo.newBuilder("enabled", "数据状态")
                .add("1", "删除").add("2", "正常").build();

        list.add(t1);
        list.add(t2);
        list.add(t3);
        return list.stream().iterator();
    }
}
