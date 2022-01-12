package com.testfan.spring;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	HttpServletRequest request;

	// 页面跳转和传参数 ModelAndView 方式
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView("home"); // home.jsp
		modelAndView.addObject("name", "test12222");
		return modelAndView;
	}

	// GetMapping == @RequestMapping(value = "/test2", method = RequestMethod.GET)
	// 也可以不采用ModelAndView 用request 对象
	@GetMapping("/test2")
	public String test2() {
		request.setAttribute("name", "test2");
		// home.jsp
		return "home";
	}

	// 动态参数 reqeust 第二种用法
	@GetMapping(value = "/test/{type}/{name}")
	public String test3(@PathVariable("type") String type, @PathVariable("name") String name) {
		request.setAttribute("name", name);
		return "home";
	}

	// Object...
	// 参数传递
	@GetMapping("/login")
	public ModelAndView login(@RequestParam(required = true) String name, String pass) {
		System.out.println("name " + name);
		System.out.println("pass " + pass);
		UserBean user = new UserBean();
		user.setName(name);
		user.setPass(pass);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		return new ModelAndView("home", data);
	}

	// 参数传递
	@GetMapping("/login2")
	public String login2(UserBean userBean) {
		System.out.println("name " + userBean.getName());
		System.out.println("pass " + userBean.getPass());
		request.setAttribute("user", userBean);
		return "home";
	}

	// json支持
	@RequestMapping("/testjson")
	@ResponseBody // 返回结果自动转json
	public String testjson() {
		return "home";
	}

	@GetMapping(value = "/mapjson")
	@ResponseBody
	public Map<String, String> map() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		return map;
	}

	// 参数问题
	// form 表单参数
	@RequestMapping("/testjson2")
	@ResponseBody
	public Object login3(UserBean userBean) {
		return userBean;
	}

	// post 请求
	// http://localhost:8087/loginpost?name=123&pass=123 换成post请求
	@PostMapping("/loginpost")
	@ResponseBody
	public String loginpost(UserBean userBean) {
		System.out.println("name " + userBean.getName());
		System.out.println("pass " + userBean.getPass());
		request.setAttribute("user", userBean);
		return "home";
	}

	// @RequestBody 请求参数是JSON
	@RequestMapping(value = "/testjson3")
	@ResponseBody
	public Object jsonparam(@RequestBody UserBean userBean) {
		return userBean;
	}

	// 不需要处理的json
	// produces= {"application/json;charset=utf-8"} 可以控制返回数据头部信息
	// @RequestMapping(value = "/testjson4", produces = {
	// "application/json;charset=utf-8" })
	@RequestMapping(value = "/testjson4")
	@ResponseBody
	public String jsonFile(@RequestBody String param) {
		String path = "E:\\20201129\\20210307\\";
		try {
			//
			return FileUtils.readFileToString(new File(path + "jsonFile1.json"), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 动态化
	@RequestMapping(value = "/testjson5/{type}")
	@ResponseBody
	public String jsonFile(@RequestBody String param, @PathVariable("type") String type) {
		String path = "E:\\20201129\\20210307\\";
		try {
			return FileUtils.readFileToString(new File(path + "jsonFile" + type + ".json"), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	// header案例
	@RequestMapping(value = "/getUserList")
	@ResponseBody
	public Object getUser(@RequestHeader(value = "token") String mytoken,
			@RequestParam(value = "count", required = false, defaultValue = "10") int reqcount) {
		if (!"61b3590090982a0185dda9d3bd793b46".equals(mytoken)) {
			return "请校验token";
		}
		List<UserBean> userList = new ArrayList<UserBean>();
		for (int i = 0; i < reqcount; i++) {
			userList.add(new UserBean("test" + i, "pass" + i));
		}
		return userList;
	}

}
