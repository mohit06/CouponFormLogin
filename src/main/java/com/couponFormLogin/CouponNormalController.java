package com.couponFormLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class CouponNormalController {

	
	@Autowired
	private CouponRepository cr;
	

	@Autowired
	private CouponService cs;
		
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@GetMapping("/showCreateCoupon")
	public String showCreateCoupon() {
		
		return "createCoupon";
	}
	
	@GetMapping("/showGetCouponDetails")
    public String showGetCouponDetails() {
		
		return "getCoupon";
	}
	
	@PostMapping("/saveCoupon")
	public String saveCoupon(CouponDetails coupon) {
		cr.save(coupon);
		return "CreateResponse";
	}
	
	@PostMapping("/getCouponDetails")
	public ModelAndView getCouponDetails(CouponDetails coupon) {
		System.out.println(coupon);
		ModelAndView mav = new ModelAndView("coupondetails");
		
		CouponDetails c = cs.getCouponDetails(coupon.getCouponCode());
		mav.addObject("couponcode", c.getCouponCode());
		mav.addObject("discount", c.getDiscount());
		return mav;
	}
	
}
