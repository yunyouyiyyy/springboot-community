2024.11.28
这是我的第三次项目提交，这次提交涉及到了许多内容，于是决定写一个更新日志，方便查看。
1. 学习Listener后写了一个OnlineCntListener，用于统计在线人数。这个类是通过记录session的数量来统计的
2. 建立了一个MessageReviewListener类里面还没有东西，准备写一个留言审核功能，这个功能是通过监听器来实现的
3. 学习了spring事件机制，写了一个监测用户注册后发送邮件的功能，这个功能是通过事件机制来实现的。未来我想改为注册时通过邮件发送验证码注册
4. 修改了验证码的位置以及验证码的验证时机，现在验证码是在登录错误3次后要求使用验证码。但是目前有一个bug，
当验证码出错时验证码就不显示了并且因为无法填写验证码而无法登录
(新添文件
- OnlineCntListener
- MessageReviewListener
- 事件:RegEvent
- 事件监听器:EmailService
(改变代码
- 事件发布者:在UserService里添加的eventPublisher对象
- UserController里在登录页面添加了验证码逻辑，删除了以前注册页面的改变.
- login.html里添加了验证码的逻辑,删除了注册界面的验证码逻辑
- pom.xml里添加了spring-boot-starter-mail依赖
- application.properties里添加了邮件发送的配置

2024.11.29
修复了验证码无法显示的bug，之前无法显示验证码的原因是因为没有在登录失败时正确地控制 showCaptcha，
从而导致验证码输入框未能显示。在修改后的代码中，通过 model.addAttribute("showCaptcha", true) 
确保了在错误登录次数超过 3 次时，验证码输入框能够正确显示。
(改变代码
- UserController