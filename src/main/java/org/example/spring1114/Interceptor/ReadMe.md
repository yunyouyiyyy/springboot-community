### **Interceptor（拦截器）介绍**

**Interceptor（拦截器）** 是 Spring MVC 和其他框架中的一个重要组件，用于对请求处理过程中的特定阶段进行拦截和干预。拦截器的主要作用是拦截客户端发往服务器的请求或服务器返回给客户端的响应，并在这些请求/响应的处理链中加入额外的逻辑。

---

### **Interceptor 的核心功能**

1. **请求预处理**：在请求到达控制器（`Controller`）之前执行某些操作，例如验证用户权限、记录日志等。
2. **请求后处理**：在控制器处理完请求后，渲染视图之前执行操作，例如修改返回的 `ModelAndView`。
3. **请求完成后处理**：在整个请求完成后执行清理操作，例如记录执行时间或释放资源。

---

### **Interceptor 的常见应用场景**

1. **权限验证**：
    - 验证用户是否已登录或是否有权限访问某个资源。
2. **日志记录**：
    - 记录请求的 URL、参数、执行时间等信息。
3. **性能监控**：
    - 统计请求的处理时间。
4. **全局功能实现**：
    - 添加公共数据到模型中，应用于多个控制器的逻辑。

---

### **Interceptor 的工作原理**

Spring MVC 的拦截器基于 `HandlerInterceptor` 接口实现，通过注册到 `InterceptorRegistry` 来拦截特定的请求路径。

#### **`HandlerInterceptor` 的三个核心方法**
1. **`preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)`**：
    - 在请求被处理之前调用。
    - 返回值：
        - `true`：继续执行后续拦截器或目标处理器（Controller）。
        - `false`：中止请求，通常直接向客户端返回响应。
    - 适合用于权限验证或预处理逻辑。

2. **`postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)`**：
    - 在处理器（Controller）执行之后，视图渲染之前调用。
    - 适合修改模型数据或根据处理结果执行额外逻辑。

3. **`afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)`**：
    - 在整个请求完成后调用（包括视图渲染）。
    - 适合用于资源清理或异常处理。

---

### **Interceptor 的实现与配置**

#### **1. 自定义拦截器**
实现 `HandlerInterceptor` 接口：

```java
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PreHandle: 请求处理前");
        // 示例：权限验证
        String token = request.getHeader("Authorization");
        if (token == null || !token.equals("valid-token")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false; // 拦截请求，直接返回 401
        }
        return true; // 继续请求处理
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle: 请求处理后但视图渲染前");
        if (modelAndView != null) {
            modelAndView.addObject("commonData", "This is common data");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AfterCompletion: 请求完成后");
        if (ex != null) {
            System.err.println("异常：" + ex.getMessage());
        }
    }
}
```

---

#### **2. 注册拦截器**
在 Spring Boot 中，通过实现 `WebMvcConfigurer` 来注册拦截器。

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")         // 拦截所有请求路径
                .excludePathPatterns("/login"); // 排除特定路径
    }
}
```

---

### **与 Filter 的区别**

| 特性             | **Interceptor**                              | **Filter**                                      |
|------------------|---------------------------------------------|------------------------------------------------|
| **范围**         | Spring MVC 的 Controller 请求处理。          | Servlet 层，适用于整个 Web 应用。              |
| **执行时机**     | Controller 前后（与业务逻辑结合紧密）。       | Servlet 容器请求处理的最早阶段。               |
| **应用场景**     | 权限验证、日志记录、模型数据修改等。          | 全局跨请求的处理，例如编码转换、静态资源过滤。 |
| **访问能力**     | 可访问 Spring 的上下文对象（如 Bean）。        | 无法直接访问 Spring 容器中的 Bean。            |
| **实现接口**     | `HandlerInterceptor`。                       | `javax.servlet.Filter`。                       |

---

### **完整工作流程示例**

假设有一个 API `/api/data`，以下是拦截器在整个请求生命周期中的执行顺序：
1. **客户端请求 `/api/data`**：
    - `preHandle()` 执行，验证权限。如果验证失败，拦截请求返回错误状态。
2. **Controller 执行后**：
    - `postHandle()` 执行，可以修改返回的 `ModelAndView` 数据。
3. **请求完成**：
    - `afterCompletion()` 执行，记录日志或清理资源。

---

### **总结**

`Interceptor` 是 Spring MVC 中的重要工具，提供了对请求生命周期中多个阶段的干预能力。它与 `Filter` 的功能类似但更专注于 Spring MVC 的控制器层处理，更适合实现与业务逻辑相关的功能，如权限验证、日志记录和模型修改。通过合理使用拦截器，可以提高代码的可维护性和模块化水平。