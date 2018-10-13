package com.adu.springboot.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTaskApplicationTests {

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	/**
	 * 简单邮件
	 */
	@Test
	public void email01() {
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
	}

	/**
	 * 复杂邮件
	 * @throws MessagingException
	 */
	@Test
	public void email2() throws MessagingException {
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
	}

}
