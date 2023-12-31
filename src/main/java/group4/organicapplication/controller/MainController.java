package group4.organicapplication.controller;

import group4.organicapplication.model.Category;
import group4.organicapplication.model.User;
import group4.organicapplication.service.CategoryService;
import group4.organicapplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService service;

    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }
    public User getSessionUser(HttpServletRequest httpServletRequest){
        return (User) httpServletRequest.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/")
    public String showHomePage(Model model){
        return "client/home";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        return "client/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             HttpSession httpSession){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse,auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/productInfo")
    public String showproductInfoPage(){
        return "productInfo";
    }

    @GetMapping("/sale")
    public String showSalePage(){
        return "sale";
    }

    @GetMapping("/order")
    public String showOrderPage(){
        return "order";
    }

    @GetMapping("/supplier")
    public String showSupplierPage(){
        return "supplier";
    }

    @GetMapping("/import")
    public String showImportPage(){
        return "import";
    }

    //Method này đã có ở lớp ClientAccountController
//    @GetMapping("/account")
//    public String showAccountPage(){
//        return "account";
//    }
}
