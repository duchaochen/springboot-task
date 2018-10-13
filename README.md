### 异步调用@EnableAsync，@Async

    1.使用异步注解代码：
        /**
         * 异步调用
         */
        @Async
        public void hello() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("处理任务重。。。");
        }
        
    2.开始异步注解
        //开启异步注解
        @EnableAsync
        @SpringBootApplication
        public class SpringbootTaskApplication {
        
        	public static void main(String[] args) {
        		SpringApplication.run(SpringbootTaskApplication.class, args);
        	}
        }
        
    3.然后在controller层注解调用service总的hello方法
        @RestController
        public class AsyncContrller {
        
            @Autowired
            private AsyncService asyncService;
        
            @GetMapping("/hello")
            public String hello() {
                asyncService.hello();
                return "success";
            }
        }
        
     
     
### 定时任务@Scheduled，@EnableScheduling
     
    代码：
        //开启定时注解
        @EnableScheduling
        @SpringBootApplication
        public class SpringbootTaskApplication {
        
        	public static void main(String[] args) {
        		SpringApplication.run(SpringbootTaskApplication.class, args);
        	}
        }
     
        /**
          * 表达式
          *  second(秒) minute（分）, hour（时）, day of month（日）, month（月） day of week（周）
          *  示例：{@code "0 * * * * MON-FRI"}
          * 表示从星期1到星6，每分钟的1，2，3，4秒就启动
          * @Scheduled(cron = "1,2,3,4 * * * * MON-SAT")
          */
         @Scheduled(cron = "0-59 9 13 13 * *")
         public void show() {
             System.out.println("hello.....");
         }
         
         @Scheduled(cron = "0-59 9 13 13 * *")表达式：
         
         0-59       9       13          13          *         *
        0秒-59秒   9分   下午1点     13号      1月-12月    周1-周日
         
         
### 邮件发送
    
    1.设置qq邮箱的邮件服务器
        进入qq邮箱 ==> 设置 ==> 账户 ==> 开启服务（POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务
                                               1.POP3/SMTP服务 (如何使用 Foxmail 等软件收发邮件？) 已开启 |  关闭
                                               2.IMAP/SMTP服务 (什么是 IMAP，它又是如何设置？) 已开启 |  关闭
                                               ）
        ==>生成授权码（如果已经获取授权码了就忽略）
        
    2.全局配置文件
        #这个是个人qq邮箱用户名
        spring.mail.username=939705214@qq.com
        #这个不是邮箱登录密码，而是邮件设置的授权码,就是第一步骤获取的授权码
        spring.mail.password=ehruzkrfcuoxbdjj
        #配置qq邮件服务器地址
        spring.mail.host=smtp.qq.com

    3.上面都设置好了之后，普通邮件的发送代码
    
        @Autowired
    	private JavaMailSenderImpl javaMailSender;
    
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //设置邮箱标题
        simpleMailMessage.setSubject("今晚7点开会");
        //邮箱内容
        simpleMailMessage.setText("有多少人没有发工资!!!");
        //邮箱目标地址
        simpleMailMessage.setTo("113214862@qq.com");
        //邮箱发送地址
        simpleMailMessage.setFrom("939705214@qq.com");
        //发送
        javaMailSender.send(simpleMailMessage);
        
    4.复杂(附件，html)邮件发送代码
    
        //需要使用MimeMessage对象设置复杂邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //使用此类操作文档
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        //设置邮箱标题
        mimeMessageHelper.setSubject("今晚8点开会");
        //邮箱内容,如果为true表示为html
        mimeMessageHelper.setText("<a href=\"http://www.w3school.com.cn\">W3School</a>",true);
        //邮箱目标地址
        mimeMessageHelper.setTo("113214862@qq.com");
        //邮箱发送地址
        mimeMessageHelper.setFrom("939705214@qq.com");
        //发送文件(文件名称只能为10个汉字以内)
        mimeMessageHelper.addAttachment("开发文档需求.doc",new File("C:\\Users\\Administrator\\Desktop\\开发文档需求(1).doc"));
        mimeMessageHelper.addAttachment("海思特文件管理系统文.docx",new File("C:\\Users\\Administrator\\Desktop\\海思特文件管理系统需求.docx"));

        //发送
        javaMailSender.send(mimeMessage);