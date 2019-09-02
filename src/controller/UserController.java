package controller;


import Utils.common.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import pojo.MLResult;
import pojo.MockUser;
import pojo.sql.UserInfo;
import service.UserService;

import java.util.List;


@Controller("usercontroller")
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

	@RequestMapping("/toLogin")
	public String toLogin()
	{
		return "login";
	}
//	@RequestMapping("/login")
//	@ResponseBody
//	public String login(@Validated UserInfo user, BindingResult result,ModelMap  model)
//	{
//		List<ObjectError> list_error = null;
//		if(result.hasErrors()){
//			list_error = result.getAllErrors();
//			for(ObjectError error:list_error){
//				System.out.println(error.getDefaultMessage());
//			}
//			model.addAttribute("allErrors",list_error);
//			return "error";
//		}
//		UserInfo info = service.queryOne(user.getName(), user.getPassword());
//		System.out.println(user.getName());
//		if(info!=null) {
//			model.addAttribute("username",user.getName());
//			return "show";
//		}
//		else
//			return "login";
//	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(UserInfo user)
	{
		MockUser mockUser = new MockUser();
		mockUser.setCode(200);
		mockUser.setMsg("ok");
		mockUser.setUser(user);
		return JsonUtils.objectToJson(mockUser);
	}


	@RequestMapping("/")
	public ModelAndView student() {
	      return new ModelAndView("login", "command", new UserInfo());
	 }
}
